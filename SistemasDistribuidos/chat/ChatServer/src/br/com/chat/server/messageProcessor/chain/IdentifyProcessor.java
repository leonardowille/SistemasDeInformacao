package br.com.chat.server.messageProcessor.chain;

import br.com.chat.common.messages.client.ClientIdentityMessage;
import br.com.chat.common.messages.Message;
import br.com.chat.server.ConnectedClient;
import br.com.chat.server.messageProcessor.ServerProcessor;

public class IdentifyProcessor implements ServerProcessor {

	private ServerProcessor next;

	@Override
	public void process(Message message, ConnectedClient connectedClient) {
		if (message instanceof ClientIdentityMessage) {
			ClientIdentityMessage clientIdentityMessage = (ClientIdentityMessage) message;
			connectedClient.identifyUser(clientIdentityMessage.getClient());
		} else {
			this.next.process(message, connectedClient);
		}
	}

	@Override
	public void setNext(ServerProcessor next) {
		this.next = next;
	}
}
