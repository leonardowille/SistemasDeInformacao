package br.com.chat.client.messageProcessor.chain;

import br.com.chat.client.Client;
import br.com.chat.client.messageProcessor.ClientProcessor;
import br.com.chat.common.messages.Message;
import br.com.chat.common.messages.server.ServerPublicChatMessageMessage;

public class ServerPublicChatMessageMessageProcessor implements ClientProcessor {

	private ClientProcessor next;

	public void process(Message message, Client client) {
		if (message instanceof ServerPublicChatMessageMessage) {
			ServerPublicChatMessageMessage serverPublicChatMessageMessage = (ServerPublicChatMessageMessage) message;
			client.receiveTextMessage(serverPublicChatMessageMessage.getUser(), serverPublicChatMessageMessage.getTextMessage());
		} else {
			this.next.process(message, client);
		}
	}

	@Override
	public void setNext(ClientProcessor next) {
		this.next = next;
	}
}
