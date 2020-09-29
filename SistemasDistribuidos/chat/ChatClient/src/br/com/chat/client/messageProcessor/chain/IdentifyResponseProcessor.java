package br.com.chat.client.messageProcessor.chain;

import br.com.chat.client.Client;
import br.com.chat.client.messageProcessor.ClientProcessor;
import br.com.chat.common.messages.server.ServerIdentifyResponseMessage;
import br.com.chat.common.messages.Message;

public class IdentifyResponseProcessor implements ClientProcessor {

	private ClientProcessor next;

	public void process(Message message, Client client) {
		if (message instanceof ServerIdentifyResponseMessage) {
			ServerIdentifyResponseMessage identificationResponse = (ServerIdentifyResponseMessage) message;
			if (identificationResponse.getError() != null) {
				client.erroOnIdentifyOnServer(identificationResponse.getError());
			} else {
				client.identifiedOnServer(identificationResponse.getUser());
			}
		} else {
			this.next.process(message, client);
		}
	}

	@Override
	public void setNext(ClientProcessor next) {
		this.next = next;
	}
}
