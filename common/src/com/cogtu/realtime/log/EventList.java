package com.cogtu.realtime.log;

import scala.Serializable;

import java.util.ArrayList;

public class EventList implements Serializable {

	private String eventName;
	private String eventTime;
	private String pageURL;
	private String device;

	private ArrayList<String> visitorIDList;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public ArrayList<String> getVisitorIDList() {
		return visitorIDList;
	}

	public void setVisitorIDList(ArrayList<String> visitorIDList) {
		this.visitorIDList = visitorIDList;
	}

}
