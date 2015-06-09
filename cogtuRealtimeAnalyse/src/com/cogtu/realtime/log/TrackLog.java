package com.cogtu.realtime.log;

/**
 * 追踪用户行为的Log
 * @author knightli
 *
 */
public class TrackLog extends CogtuLog {
	// 行为
	private String behavior;

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
}
