package br.com.chat.common.messages.client;

import br.com.chat.common.User;
import br.com.chat.common.messages.Message;

public class ClientIdentityMessage implements Message {

	private User user;

	public ClientIdentityMessage(User user) {
		this.user = user;
	}

	public User getClient() {
		return user;
	}
}
