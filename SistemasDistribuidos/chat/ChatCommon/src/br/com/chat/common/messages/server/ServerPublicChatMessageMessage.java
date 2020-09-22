package br.com.chat.common.messages.server;

import br.com.chat.common.User;
import br.com.chat.common.messages.Message;

public class ServerPublicChatMessageMessage implements Message {

	private User user;
	private String textMessage;

	public ServerPublicChatMessageMessage(User user, String textMessage) {
		this.user = user;
		this.textMessage = textMessage;
	}

	public User getUser() {
		return user;
	}

	public String getTextMessage() {
		return textMessage;
	}
}
