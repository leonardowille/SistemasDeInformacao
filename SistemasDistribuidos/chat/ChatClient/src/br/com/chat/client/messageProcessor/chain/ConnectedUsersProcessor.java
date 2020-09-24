package br.com.chat.client.messageProcessor.chain;

import br.com.chat.client.Client;
import br.com.chat.client.messageProcessor.ClientProcessor;
import br.com.chat.common.messages.Message;
import br.com.chat.common.messages.server.ServerConnectedUsersMessage;

public class ConnectedUsersProcessor implements ClientProcessor {

	private ClientProcessor next;

	public void process(Message message, Client client) {
		if (message instanceof ServerConnectedUsersMessage) {
			ServerConnectedUsersMessage serverConnectedUsersMessage = (ServerConnectedUsersMessage) message;
			client.updateConnectedUsers(serverConnectedUsersMessage.getConnectedUsers());
		} else {
			this.next.process(message, client);
		}
	}

	@Override
	public void setNext(ClientProcessor next) {
		this.next = next;
	}
}
