package br.com.chat.server.messageProcessor.chain;

import br.com.chat.common.messages.Message;
import br.com.chat.common.messages.client.ClientPrivateChatMessageMessage;
import br.com.chat.server.ConnectedClient;
import br.com.chat.server.messageProcessor.ServerProcessor;

public class PrivateChatMessageProcessor implements ServerProcessor {

	private ServerProcessor next;

	@Override
	public void process(Message message, ConnectedClient connectedClient) {
		if (message instanceof ClientPrivateChatMessageMessage) {
			ClientPrivateChatMessageMessage clientPrivateChatMessageMessage = (ClientPrivateChatMessageMessage) message;
			connectedClient.sendPrivateChatMessage(clientPrivateChatMessageMessage.getTarget(), clientPrivateChatMessageMessage.getTextMessage());
		} else {
			this.next.process(message, connectedClient);
		}
	}

	@Override
	public void setNext(ServerProcessor next) {
		this.next = next;
	}
}
