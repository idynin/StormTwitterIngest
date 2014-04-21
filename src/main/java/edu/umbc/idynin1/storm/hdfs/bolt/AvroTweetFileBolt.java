package edu.umbc.idynin1.storm.hdfs.bolt;

import java.io.IOException;
import java.net.URI;
import java.util.EnumSet;
import java.util.Map;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.client.HdfsDataOutputStream;
import org.apache.hadoop.hdfs.client.HdfsDataOutputStream.SyncFlag;
import org.apache.storm.hdfs.bolt.AbstractHdfsBolt;
import org.apache.storm.hdfs.bolt.format.FileNameFormat;
import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;
import org.apache.storm.hdfs.bolt.sync.SyncPolicy;
import org.apache.storm.hdfs.common.rotation.RotationAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Tuple;
import edu.umbc.idynin1.avro.Tweet;

public class AvroTweetFileBolt extends AbstractHdfsBolt {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AvroTweetFileBolt.class);

	private AvroTweetFormat format;

	private DatumWriter<Tweet> tweetWriter;
	private DataFileWriter<Tweet> tweetDFW;

	private FSDataOutputStream out;
	private long offset = 0;

	public AvroTweetFileBolt withFsUrl(String fsUrl) {
		this.fsUrl = fsUrl;
		return this;
	}

	public AvroTweetFileBolt withFileNameFormat(FileNameFormat fileNameFormat) {
		this.fileNameFormat = fileNameFormat;
		return this;
	}

	public AvroTweetFileBolt withTweetFormat(AvroTweetFormat format) {
		this.format = format;
		return this;
	}

	public AvroTweetFileBolt withSyncPolicy(SyncPolicy syncPolicy) {
		this.syncPolicy = syncPolicy;
		return this;
	}

	public AvroTweetFileBolt withRotationPolicy(FileRotationPolicy rotationPolicy) {
		this.rotationPolicy = rotationPolicy;
		return this;
	}

	public AvroTweetFileBolt addRotationAction(RotationAction action) {
		this.rotationActions.add(action);
		return this;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void doPrepare(Map conf, TopologyContext topologyContext, OutputCollector collector)
			throws IOException {
		LOG.info("Preparing HDFS Bolt...");
		this.fs = FileSystem.get(URI.create(this.fsUrl), hdfsConfig);
	}

	@Override
	public void execute(Tuple tuple) {
		try {
			Tweet avroTweet = this.format.format(tuple);
			if (avroTweet == null) {
				return;
			}

			tweetDFW.append(avroTweet);

			this.offset++;

			this.collector.ack(tuple);

			if (this.syncPolicy.mark(tuple, this.offset)) {
				if (this.out instanceof HdfsDataOutputStream) {
					((HdfsDataOutputStream) this.out).hsync(EnumSet.of(SyncFlag.UPDATE_LENGTH));
				} else {
					this.out.hsync();
				}
				this.syncPolicy.reset();
			}
			if (this.rotationPolicy.mark(tuple, this.offset)) {
				rotateOutputFile();
				this.offset = 0;
				this.rotationPolicy.reset();
			}
		} catch (IOException e) {
			LOG.warn("write/sync failed.", e);
			this.collector.fail(tuple);
		}
	}

	@Override
	public void closeOutputFile() throws IOException {
		tweetDFW.close();
		this.out.hsync();
		this.out.close();
	}

	@Override
	public Path createOutputFile() throws IOException {
		Path path = new Path(this.fileNameFormat.getPath(), this.fileNameFormat.getName(
				this.rotation, System.currentTimeMillis()));
		this.out = this.fs.create(path);

		tweetWriter = new SpecificDatumWriter<Tweet>(Tweet.class);
		tweetDFW = new DataFileWriter<Tweet>(tweetWriter);
		tweetDFW.create(Tweet.getClassSchema(), out);

		return path;
	}

}
