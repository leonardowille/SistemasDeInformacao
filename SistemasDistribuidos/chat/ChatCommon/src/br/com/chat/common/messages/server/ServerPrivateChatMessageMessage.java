package br.com.chat.common.messages.server;

import br.com.chat.common.User;
import br.com.chat.common.messages.Message;

public class ServerPrivateChatMessageMessage implements Message {

	private User source;
	private String textMessage;

	public ServerPrivateChatMessageMessage(User source, String textMessage) {
		this.source = source;
		this.textMessage = textMessage;
	}

	public User getSource() {
		return source;
	}

	public String getTextMessage() {
		return textMessage;
	}
}
