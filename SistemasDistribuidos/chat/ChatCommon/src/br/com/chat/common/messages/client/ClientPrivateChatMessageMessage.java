package br.com.chat.common.messages.client;

import br.com.chat.common.User;
import br.com.chat.common.messages.Message;

public class ClientPrivateChatMessageMessage implements Message {

	private User target;
	private String textMessage;

	public ClientPrivateChatMessageMessage(User target, String textMessage) {
		this.target = target;
		this.textMessage = textMessage;
	}

	public User getTarget() {
		return target;
	}

	public String getTextMessage() {
		return textMessage;
	}
}
