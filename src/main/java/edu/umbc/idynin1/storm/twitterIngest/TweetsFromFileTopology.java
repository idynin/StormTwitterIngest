package edu.umbc.idynin1.storm.twitterIngest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.format.FileNameFormat;
import org.apache.storm.hdfs.bolt.format.RecordFormat;
import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.hdfs.bolt.sync.SyncPolicy;

import edu.umbc.idynin1.storm.hdfs.bolt.AvroTweetFileBolt;
import edu.umbc.idynin1.storm.hdfs.bolt.AvroTweetFormat;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

public class TweetsFromFileTopology {
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		TopologyBuilder builder = new TopologyBuilder();

		TweetJsonFileSpout tweetSpout = new TweetJsonFileSpout(new File("testRoot"));

		TweetBoundingBoxFilterBolt tbbfb = new TweetBoundingBoxFilterBolt(
				"-76.943842,39.026387,-76.450807,39.450861");

		// RecordFormat format = new DelimitedRecordFormat().withFieldDelimiter("\t");

		// RecordFormat avroTweetFormat = new AvroTweetRecordFormat();

		// sync the filesystem after every 100 tuples
		SyncPolicy syncPolicy = new CountSyncPolicy(100);

		// rotate files every minute
		FileRotationPolicy rotationPolicy = new TimeRotationPolicy(1, TimeUnit.MINUTES);

		// FileNameFormat fileNameFormat = new
		// DefaultFileNameFormat().withPath("/twitterStream/");

		FileNameFormat fileNameFormat = new TimeBasedFileNameFormat("/twitterStreamAvro/",
				new SimpleDateFormat("yyyy/MM/dd/HH/mm")).withExtension("_fromfiles.avro");

		AvroTweetFormat avroTweetFormat = new AvroTweetFormat();

		// HdfsBolt hdfsBolt = new HdfsBolt().withFsUrl("hdfs://localhost:9000")
		// .withFileNameFormat(fileNameFormat).withRecordFormat(avroTweetFormat)
		// .withRotationPolicy(rotationPolicy).withSyncPolicy(syncPolicy);

		AvroTweetFileBolt atfBolt = new AvroTweetFileBolt().withFsUrl("hdfs://localhost:9000")
				.withFileNameFormat(fileNameFormat).withTweetFormat(avroTweetFormat)
				.withRotationPolicy(rotationPolicy).withSyncPolicy(syncPolicy);

		builder.setSpout("tweet", tweetSpout, 1);
		builder.setBolt("filterBolt", tbbfb, 1).shuffleGrouping("tweet");
		builder.setBolt("avroBolt", atfBolt).shuffleGrouping("filterBolt");

		Config conf = new Config();
		conf.setDebug(true);

		if (args != null && args.length > 0) {
			conf.setNumWorkers(2);

			StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
		} else {

			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("twitterIngestTopology", conf, builder.createTopology());

		}
	}
}
