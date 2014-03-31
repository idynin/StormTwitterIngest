package edu.umbc.idynin1.storm.twitterIngest;

import java.io.File;
import java.text.DateFormat;
import java.util.Map;

import org.apache.storm.hdfs.bolt.format.FileNameFormat;

import backtype.storm.task.TopologyContext;

public class TimeBasedFileNameFormat implements FileNameFormat {

	private static final long serialVersionUID = 1L;

	private String componentId;
	private int taskId;
	private String path = "/storm";
	private String prefix = "";
	private String extension = ".txt";

	private DateFormat dateFormat;

	public TimeBasedFileNameFormat(String basePath, DateFormat dateFormat) {
		this.path = basePath;
		this.dateFormat = dateFormat;
	}

	@Override
	public String getName(long rotation, long timeStamp) {
		return generateSubPath(timeStamp) + File.separator + this.prefix + this.componentId + "-"
				+ this.taskId + "-" + rotation + "-" + timeStamp + this.extension;
	}

	private String generateSubPath(long timeStamp) {
		synchronized (dateFormat) {
			return dateFormat.format(timeStamp);
		}
	}

	/**
	 * Overrides the default prefix.
	 * 
	 * @param prefix
	 * @return
	 */
	public TimeBasedFileNameFormat withPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}

	/**
	 * Overrides the default file extension.
	 * 
	 * @param extension
	 * @return
	 */
	public TimeBasedFileNameFormat withExtension(String extension) {
		this.extension = extension;
		return this;
	}

	public TimeBasedFileNameFormat withPath(String path) {
		this.path = path;
		return this;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map conf, TopologyContext topologyContext) {
		this.componentId = topologyContext.getThisComponentId();
		this.taskId = topologyContext.getThisTaskId();
	}

	@Override
	public String getPath() {
		return this.path;
	}

}
