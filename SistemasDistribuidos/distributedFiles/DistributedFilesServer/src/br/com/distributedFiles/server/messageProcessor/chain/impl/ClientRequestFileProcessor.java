package br.com.distributedFiles.server.messageProcessor.chain.impl;

import br.com.distributedFiles.messages.Message;
import br.com.distributedFiles.messages.impl.ClientRequestFileMessage;
import br.com.distributedFiles.server.Connection;
import br.com.distributedFiles.server.messageProcessor.chain.ServerProcessor;

public class ClientRequestFileProcessor implements ServerProcessor {

	private ServerProcessor next;

	@Override
	public void process(Message message, Connection connection) {
		if (message instanceof ClientRequestFileMessage) {
			ClientRequestFileMessage clientRequestFileMessage = (ClientRequestFileMessage) message;
			connection.clientRequestFile(clientRequestFileMessage.getFileName());
		} else {
			this.next.process(message, connection);
		}
	}

	@Override
	public void setNext(ServerProcessor next) {
		this.next = next;
	}
}
