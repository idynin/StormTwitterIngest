package edu.umbc.idynin1.storm.twitterIngest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.FileUtils;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class TweetJsonFileSpout extends BaseRichSpout {

	private static final long serialVersionUID = 1L;

	private LinkedBlockingQueue<String> queue = null;
	private SpoutOutputCollector collector;

	Collection<File> files;

	Thread fileReader;

	public TweetJsonFileSpout(File rootDirectory) {
		String[] extensions = { "gz" };
		files = FileUtils.listFiles(rootDirectory, extensions, true);
		System.out.println("CREATED SPOUT WITH NUM FILES: " + files.size());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		queue = new LinkedBlockingQueue<String>(1000);
		this.collector = collector;

		fileReader = new Thread() {
			public void run() {
				Iterator<File> fileIterator = files.iterator();
				BufferedReader br;
				String line;
				while (fileIterator.hasNext()) {
					File f = fileIterator.next();
					if (!f.canRead()) {
						System.err.println("Can't read file: " + f.getAbsolutePath());
						continue;
					}
					File processingFile = new File(f.getParentFile(), f.getName() + "_PROCESSING");
					try {
						FileUtils.moveFile(f, processingFile);
						br = new BufferedReader(new InputStreamReader(new GZIPInputStream(
								new FileInputStream(processingFile))));
						while ((line = br.readLine()) != null) {
							try {
								queue.put(line);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						br.close();
						File completedFile = new File(processingFile.getParentFile(), f.getName()
								+ "_COMPLETED");
						FileUtils.moveFile(processingFile, completedFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};

		fileReader.start();

	}

	@Override
	public void nextTuple() {
		String ret = queue.poll();
		if (ret == null) {
			Utils.sleep(50);
		} else {
			collector.emit(new Values(ret));
		}

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("tweet"));
	}

}
