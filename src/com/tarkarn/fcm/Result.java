package com.tarkarn.fcm;

import java.io.Serializable;

public class Result implements Serializable {

	private static final long serialVersionUID = 6653914549314354288L;

	private String message_id;
	private String error;

	public String getMessageId() {
		return message_id;
	}

	public void setMessageId(String messageId) {
		this.message_id = messageId;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
