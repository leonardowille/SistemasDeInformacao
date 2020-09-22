package br.com.chat.common.messages.server;

import br.com.chat.common.User;

public class ServerIdentifyResponseMessage extends ResponseMessage {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
