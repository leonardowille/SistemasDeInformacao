package br.com.chat.server.messageProcessor.chain;

import br.com.chat.common.messages.Message;
import br.com.chat.common.messages.client.ClientPublicChatMessageMessage;
import br.com.chat.server.ConnectedClient;
import br.com.chat.server.messageProcessor.ServerProcessor;

public class PublicChatMessageProcessor implements ServerProcessor {

	private ServerProcessor next;

	@Override
	public void process(Message message, ConnectedClient connectedClient) {
		if (message instanceof ClientPublicChatMessageMessage) {
			ClientPublicChatMessageMessage clientPublicChatMessageMessage = (ClientPublicChatMessageMessage) message;
			connectedClient.sendPublicChatMessage(clientPublicChatMessageMessage.getTextMessage());
		} else {
			this.next.process(message, connectedClient);
		}
	}

	@Override
	public void setNext(ServerProcessor next) {
		this.next = next;
	}
}
