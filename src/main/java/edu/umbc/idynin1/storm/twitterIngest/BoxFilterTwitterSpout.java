package edu.umbc.idynin1.storm.twitterIngest;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.StatusListener;
import twitter4j.TwitterObjectFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import backtype.storm.Config;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class BoxFilterTwitterSpout extends BaseRichSpout {
	private static final String AUTH_CREDENTIALS_FILE = "internal.properties";

	private LinkedBlockingQueue<String> queue = null;
	private TwitterStream twitterStream;
	private SpoutOutputCollector collector;

	private String[] boundingBoxes;

	private Configuration conf;

	// @formatter:off
	//		String[] boundingBoxes = {
	//				"-76.943842,39.026387,-76.450807,39.450861" // Baltimore + UMBC + Suburbs
	//				};
	// @formatter:on

	public BoxFilterTwitterSpout(String... boundingBoxes) {
		if (boundingBoxes != null && boundingBoxes.length > 0) {
			this.boundingBoxes = boundingBoxes;
		}
		try {
			Properties props = new Properties();
			props.load(BoxFilterTwitterSpout.class.getResourceAsStream("/" + AUTH_CREDENTIALS_FILE));

			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey(props.getProperty("oauth.consumerKey"))
					.setOAuthConsumerSecret(props.getProperty("oauth.consumerSecret"))
					.setOAuthAccessToken(props.getProperty("oauth.accessToken"))
					.setOAuthAccessTokenSecret(props.getProperty("oauth.accessTokenSecret"))
					.setJSONStoreEnabled(true);
			conf = cb.build();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		queue = new LinkedBlockingQueue<String>(1000);
		this.collector = collector;

		String boxen = "";
		for (String boxString : boundingBoxes) {
			if (boxString != null && validateBoundingBox(boxString)) {
				boxen += boxString + ",";
			}
		}
		if (boxen.length() > 0) {
			boxen = boxen.substring(0, boxen.length() - 1);
		}

		StatusListener listener = new NonConformingStatusListener() {

			@Override
			public void onStatus(Status status) {
				queue.offer(TwitterObjectFactory.getRawJSON(status));
				// queue.offer(DataObjectFactory.getRawJSON(status));
			}

		};

		twitterStream = new TwitterStreamFactory(this.conf).getInstance();
		twitterStream.addListener(listener);

		double[][] boundingBox = boundingBoxStringToBoundingBox(boxen);
		if (boundingBox.length >= 2) {
			twitterStream.filter(new FilterQuery().locations(boundingBox));
		} else {
			twitterStream.sample();
		}

	}

	private static boolean validateBoundingBox(String s) {
		String[] parts = s.trim().replaceAll("\\s+", "").split(",");
		try {
			if (parts.length == 4) {
				Double.parseDouble(parts[0]);
				Double.parseDouble(parts[1]);
				Double.parseDouble(parts[2]);
				Double.parseDouble(parts[3]);

				return true;
			}
		} catch (NumberFormatException nfe) {
			Logger.getGlobal().warning("Bad bounding box: " + s);
		}
		return false;
	}

	private static double[][] boundingBoxStringToBoundingBox(String in) {
		String[] parts = in.trim().replaceAll("\\s+", "").split(",");
		double[][] out = new double[parts.length / 2][2];
		if (parts.length >= 4 && parts.length % 2 == 0) {
			for (int i = 0; i < parts.length; i += 2) {
				out[i / 2][0] = Double.parseDouble(parts[i]);
				out[i / 2][1] = Double.parseDouble(parts[i + 1]);
			}
		}
		return out;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	public void close() {
		twitterStream.shutdown();
		twitterStream.cleanUp();
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		Config ret = new Config();
		ret.setMaxTaskParallelism(1);
		return ret;
	}

	@Override
	public void ack(Object id) {
	}

	@Override
	public void fail(Object id) {
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("tweet"));
	}

}
