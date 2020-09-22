package br.com.chat.common.messages.client;

import br.com.chat.common.messages.Message;

public class ClientPublicChatMessageMessage implements Message {

	private String textMessage;

	public ClientPublicChatMessageMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public String getTextMessage() {
		return textMessage;
	}
}
