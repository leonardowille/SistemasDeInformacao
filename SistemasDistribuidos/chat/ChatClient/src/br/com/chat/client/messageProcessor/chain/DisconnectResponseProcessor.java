package br.com.chat.client.messageProcessor.chain;

import br.com.chat.client.Client;
import br.com.chat.client.messageProcessor.ClientProcessor;
import br.com.chat.common.messages.server.ServerDisconnectResponseMessage;
import br.com.chat.common.messages.Message;

public class DisconnectResponseProcessor implements ClientProcessor {

	private ClientProcessor next;

	public void process(Message message, Client client) {
		if (message instanceof ServerDisconnectResponseMessage) {
			client.disconnectUser();
		} else {
			this.next.process(message, client);
		}
	}

	@Override
	public void setNext(ClientProcessor next) {
		this.next = next;
	}
}
