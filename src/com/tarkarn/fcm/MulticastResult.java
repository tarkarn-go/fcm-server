package com.tarkarn.fcm;

import java.io.Serializable;
import java.util.ArrayList;

public class MulticastResult implements Serializable {

	private static final long serialVersionUID = 12167386693125713L;
	
	private long multicastId;
	private int success;
	private int failure;
	private int canonicalIds;
	private ArrayList<Result> results;

	public long getMulticastId() {
		return multicastId;
	}

	public void setMulticastId(long multicastId) {
		this.multicastId = multicastId;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getFailure() {
		return failure;
	}

	public void setFailure(int failure) {
		this.failure = failure;
	}

	public int getCanonicalIds() {
		return canonicalIds;
	}

	public void setCanonicalIds(int canonicalIds) {
		this.canonicalIds = canonicalIds;
	}

	public ArrayList<Result> getResults() {
		return results;
	}

	public void setResults(ArrayList<Result> results) {
		this.results = results;
	}
}
