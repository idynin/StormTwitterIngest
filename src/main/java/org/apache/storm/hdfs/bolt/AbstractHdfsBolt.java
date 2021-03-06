/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.storm.hdfs.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.storm.hdfs.bolt.format.FileNameFormat;
import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;
import org.apache.storm.hdfs.bolt.sync.SyncPolicy;
import org.apache.storm.hdfs.common.rotation.RotationAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractHdfsBolt extends BaseRichBolt {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractHdfsBolt.class);

	protected ArrayList<RotationAction> rotationActions = new ArrayList<RotationAction>();
	private Path currentFile;
	protected OutputCollector collector;
	protected FileSystem fs;
	protected SyncPolicy syncPolicy;
	protected FileRotationPolicy rotationPolicy;
	protected FileNameFormat fileNameFormat;
	protected int rotation = 0;
	protected String fsUrl;
	// protected String path;

	protected Configuration hdfsConfig;

	protected void rotateOutputFile() throws IOException {
		LOG.info("Rotating output file...");
		long start = System.currentTimeMillis();
		closeOutputFile();
		this.rotation++;

		Path newFile = createOutputFile();
		LOG.info("Performing {} file rotation actions.", this.rotationActions.size());
		for (RotationAction action : this.rotationActions) {
			action.execute(this.fs, this.currentFile);
		}
		this.currentFile = newFile;
		long time = System.currentTimeMillis() - start;
		LOG.info("File rotation took {} ms.", time);
	}

	public void prepare(Map conf, TopologyContext topologyContext, OutputCollector collector) {
		if (this.syncPolicy == null)
			throw new IllegalStateException("SyncPolicy must be specified.");
		if (this.rotationPolicy == null)
			throw new IllegalStateException("RotationPolicy must be specified.");
		if (this.fsUrl == null) {
			throw new IllegalStateException("File system URL must be specified.");
		}

		this.collector = collector;
		this.fileNameFormat.prepare(conf, topologyContext);
		this.hdfsConfig = new Configuration();

		try {
			doPrepare(conf, topologyContext, collector);
			this.currentFile = createOutputFile();

		} catch (Exception e) {
			throw new RuntimeException("Error preparing HdfsBolt: " + e.getMessage(), e);
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
	}

	public abstract void closeOutputFile() throws IOException;

	public abstract Path createOutputFile() throws IOException;

	public abstract void doPrepare(Map conf, TopologyContext topologyContext,
			OutputCollector collector) throws IOException;

}
