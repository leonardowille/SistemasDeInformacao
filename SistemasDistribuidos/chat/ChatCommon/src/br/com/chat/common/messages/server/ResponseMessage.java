package br.com.chat.common.messages.server;

import br.com.chat.common.messages.Message;

public abstract class ResponseMessage implements Message {

	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
