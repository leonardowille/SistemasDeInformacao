package br.com.distributedFiles.client.messageProcessor.chain.impl;

import br.com.distributedFiles.client.Client;
import br.com.distributedFiles.client.messageProcessor.chain.ClientProcessor;
import br.com.distributedFiles.messages.Message;
import br.com.distributedFiles.messages.impl.RequestFileResponseMessage;

public class RequestFileResponseProcessor implements ClientProcessor {

	private ClientProcessor next;

	public void process(Message message, Client client) {
		if (message instanceof RequestFileResponseMessage) {
			RequestFileResponseMessage requestFileResponseMessage = (RequestFileResponseMessage) message;
			client.requestedFileReceived(requestFileResponseMessage.getFileName(), requestFileResponseMessage.getBytes());
		} else {
			this.next.process(message, client);
		}
	}

	@Override
	public void setNext(ClientProcessor next) {
		this.next = next;
	}
}
