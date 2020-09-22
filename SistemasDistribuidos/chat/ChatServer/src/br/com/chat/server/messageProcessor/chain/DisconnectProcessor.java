package br.com.chat.server.messageProcessor.chain;

import br.com.chat.common.messages.client.ClientDisconnectMessage;
import br.com.chat.common.messages.Message;
import br.com.chat.server.ConnectedClient;
import br.com.chat.server.messageProcessor.ServerProcessor;

public class DisconnectProcessor implements ServerProcessor {

	private ServerProcessor next;

	@Override
	public void process(Message message, ConnectedClient connectedClient) {
		if (message instanceof ClientDisconnectMessage) {
			connectedClient.disconectUser();
		} else {
			this.next.process(message, connectedClient);
		}
	}

	@Override
	public void setNext(ServerProcessor next) {
		this.next = next;
	}
}
