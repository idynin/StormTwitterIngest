package edu.umbc.idynin1.storm.twitterIngest;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.format.FileNameFormat;
import org.apache.storm.hdfs.bolt.format.RecordFormat;
import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.hdfs.bolt.sync.SyncPolicy;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

public class TwitterIngestTopology {

	public static void main(String[] args) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();

		RecordFormat format = new DelimitedRecordFormat().withFieldDelimiter("\t");

		// sync the filesystem after every 100 tuples
		SyncPolicy syncPolicy = new CountSyncPolicy(100);

		// rotate files every minute
		FileRotationPolicy rotationPolicy = new TimeRotationPolicy(1, TimeUnit.MINUTES);

		// FileNameFormat fileNameFormat = new DefaultFileNameFormat().withPath("/twitterStream/");

		FileNameFormat fileNameFormat = new TimeBasedFileNameFormat("/twitterStream/",
				new SimpleDateFormat("yyyy/MM/dd/HH/mm"));

		HdfsBolt hdfsBolt = new HdfsBolt().withFsUrl("hdfs://localhost:9000")
				.withFileNameFormat(fileNameFormat).withRecordFormat(format)
				.withRotationPolicy(rotationPolicy).withSyncPolicy(syncPolicy);

		BoxFilterTwitterSpout tweetSpout = new BoxFilterTwitterSpout(
				"-76.943842,39.026387,-76.450807,39.450861");

		builder.setSpout("tweet", tweetSpout, 1);
		builder.setBolt("hdfsBolt", hdfsBolt, 3).shuffleGrouping("tweet");

		Config conf = new Config();
		conf.setDebug(true);

		if (args != null && args.length > 0) {
			conf.setNumWorkers(2);

			StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
		} else {

			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("twitterIngestTopology", conf, builder.createTopology());
			Utils.sleep(5 * 60 * 1000);
			cluster.killTopology("twitterIngestTopology");
			cluster.shutdown();
		}
	}
}
