package edu.umbc.idynin1.storm.twitterIngest;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public abstract class NonConformingStatusListener implements StatusListener {

	@Override
	public void onException(Exception ex) {
	}

	@Override
	public void onStatus(Status status) {
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	}

	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	}

	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
	}

	@Override
	public void onStallWarning(StallWarning warning) {
	}

}
