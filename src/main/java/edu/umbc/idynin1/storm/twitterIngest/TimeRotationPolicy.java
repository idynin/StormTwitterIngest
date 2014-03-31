package edu.umbc.idynin1.storm.twitterIngest;

import java.util.concurrent.TimeUnit;

import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;

import backtype.storm.tuple.Tuple;

public class TimeRotationPolicy implements FileRotationPolicy {

	private static final long serialVersionUID = 1L;

	private long lastrotation = 0;

	private TimeUnit timeUnit;
	private long rotationTime;

	public TimeRotationPolicy(long rotationTime, TimeUnit timeUnit) {
		this.rotationTime = rotationTime;
		this.timeUnit = timeUnit;
	}

	@Override
	public boolean mark(Tuple tuple, long offset) {
		if (lastrotation == 0) {
			lastrotation = System.currentTimeMillis();
			return false;
		}

		long curtime = System.currentTimeMillis();
		if (timeUnit.convert(curtime - lastrotation, TimeUnit.MILLISECONDS) >= rotationTime) {
			lastrotation = curtime;
			return true;
		}

		return false;
	}

	@Override
	public void reset() {
		lastrotation = System.currentTimeMillis();
	}

}
