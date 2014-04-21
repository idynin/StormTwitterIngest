package edu.umbc.idynin1.storm.twitterIngest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.commons.lang.StringUtils;
import org.apache.storm.hdfs.bolt.format.RecordFormat;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import edu.umbc.idynin1.Tweet;
import backtype.storm.tuple.Tuple;

public class AvroTweetRecordFormat implements RecordFormat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public byte[] format(Tuple tuple) {
		String tweetJSON = tuple.getString(0);
		try {
			JSONObject tweet = new JSONObject(tweetJSON);
			JSONObject geo = tweet.getJSONObject("coordinates");
			JSONArray point = geo.getJSONArray("coordinates");

			float longitude = (float) point.getDouble(0);
			float latitude = (float) point.getDouble(1);

			JSONObject user = tweet.getJSONObject("user");
			String username = user.getString("screen_name");
			long userid = user.getLong("id");

			String text = tweet.getString("text");

			JSONObject entities = tweet.getJSONObject("entities");
			JSONArray symbolsJSON = entities.getJSONArray("symbols");
			JSONArray urlsJSON = entities.getJSONArray("urls");
			JSONArray hashtagsJSON = entities.getJSONArray("hashtags");
			JSONArray userMentionsJSON = entities.getJSONArray("user_mentions");

			String symbols = StringUtils.join(jsonArrayToStringArray(symbolsJSON), '\t');
			String urls = StringUtils.join(jsonArrayToStringArray(urlsJSON), '\t');
			String hashtags = StringUtils.join(jsonArrayToStringArray(hashtagsJSON), '\t');
			String userMentions = StringUtils.join(jsonArrayToStringArray(userMentionsJSON), '\t');

			long tweetID = Long.parseLong(tweet.getString("id_str"));

			String createdAtString = tweet.getString("created_at");

			final String TWITTER = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
			SimpleDateFormat sf = new SimpleDateFormat(TWITTER, Locale.ENGLISH);
			sf.setLenient(true);

			long createdAt = sf.parse(createdAtString).getTime();

			Tweet avroTweet = Tweet.newBuilder()
					.setText(text)
					.setId(tweetID)
					.setSymbols(symbols)
					.setUrls(urls)
					.setHashtags(hashtags)
					.setUserMentions(userMentions)
					.setLongitude(longitude)
					.setLatitude(latitude)
					.setUsername(username)
					.setUserid(userid)
					.setCreatedAt(createdAt).build();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			DatumWriter<Tweet> tweetWriter = new SpecificDatumWriter<Tweet>(Tweet.class);
			DataFileWriter<Tweet> tweetDFW = new DataFileWriter<Tweet>(tweetWriter);
			tweetDFW.create(Tweet.getClassSchema(), baos);
			tweetDFW.append(avroTweet);
			tweetDFW.close();

			return baos.toByteArray();

		} catch (JSONException e) {
			System.err.println("TWEET WITHOUT COORDINATES: " + tweetJSON);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String[] jsonArrayToStringArray(JSONArray array) {
		return array.toString().substring(1, array.toString().length() - 1).replaceAll("\"", "")
				.split(",");
	}

}
