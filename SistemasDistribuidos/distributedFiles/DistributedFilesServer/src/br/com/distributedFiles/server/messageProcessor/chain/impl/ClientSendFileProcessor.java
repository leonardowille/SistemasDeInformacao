package br.com.distributedFiles.server.messageProcessor.chain.impl;

import br.com.distributedFiles.messages.Message;
import br.com.distributedFiles.messages.impl.ClientSendFileMessage;
import br.com.distributedFiles.server.Connection;
import br.com.distributedFiles.server.messageProcessor.chain.ServerProcessor;

public class ClientSendFileProcessor implements ServerProcessor {

	private ServerProcessor next;

	@Override
	public void process(Message message, Connection connection) {
		if (message instanceof ClientSendFileMessage) {
			ClientSendFileMessage clientSendFileMessage = (ClientSendFileMessage) message;
			connection.persistFile(clientSendFileMessage);
		} else {
			this.next.process(message, connection);
		}
	}

	@Override
	public void setNext(ServerProcessor next) {
		this.next = next;
	}
}
