package edu.umbc.idynin1.storm.twitterIngest;

import java.util.Map;

import twitter4j.FilterQuery;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Status;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class TweetBoundingBoxFilterBolt extends BaseRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	OutputCollector collector;

	double latSW, longSW, latNE, longNE;

	public TweetBoundingBoxFilterBolt(String... boundingBoxes) {
		String boxen = "";
		for (String boxString : boundingBoxes) {
			if (boxString != null && BoxFilterTwitterSpout.validateBoundingBox(boxString)) {
				boxen += boxString + ",";
			}
		}
		if (boxen.length() > 0) {
			boxen = boxen.substring(0, boxen.length() - 1);
		}
		double[][] boundingBox = BoxFilterTwitterSpout.boundingBoxStringToBoundingBox(boxen);

		longSW = boundingBox[0][0];
		latSW = boundingBox[0][1];
		longNE = boundingBox[1][0];
		latNE = boundingBox[1][1];
	}

	public TweetBoundingBoxFilterBolt(double latitudeSouthWest, double longitudeSouthWest,
			double latitudeNorthEast, double longitudeNorthEast) {
		this.latSW = latitudeSouthWest;
		this.longSW = longitudeSouthWest;
		this.latNE = latitudeNorthEast;
		this.longNE = longitudeNorthEast;
	}

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;

	}

	@Override
	public void execute(Tuple input) {
		String tweetJSON = input.getString(0);
		try {
			JSONObject tweet = new JSONObject(tweetJSON);
			JSONObject geo = tweet.getJSONObject("coordinates");
			JSONArray point = geo.getJSONArray("coordinates");
			double longitude = point.getDouble(0);
			double latitude = point.getDouble(1);

			// "-76.943842,39.026387,-76.450807,39.450861"

			if (latitude > latSW && latitude < latNE && longitude < longNE && longitude > longSW) {
				collector.emit(input, new Values(tweetJSON));
			} else {
				System.err.println("TWEET OUTSIDE BOUNDING BOX: " + tweetJSON);
				System.err.println("Point: " + latitude + ", " + longitude);
				System.err.println("Box: " + latSW + ", " + latNE + ", " + longNE + ", " + longSW);
			}

		} catch (JSONException e) {
			System.err.println("TWEET WITHOUT COORDINATES: " + tweetJSON);
		}

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("tweet"));

	}

}
