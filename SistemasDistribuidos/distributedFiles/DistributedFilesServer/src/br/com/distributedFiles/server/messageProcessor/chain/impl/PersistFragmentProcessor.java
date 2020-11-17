package br.com.distributedFiles.server.messageProcessor.chain.impl;

import br.com.distributedFiles.messages.Message;
import br.com.distributedFiles.messages.impl.PersistFragmentMessage;
import br.com.distributedFiles.server.Connection;
import br.com.distributedFiles.server.messageProcessor.chain.ServerProcessor;

public class PersistFragmentProcessor implements ServerProcessor {

	private ServerProcessor next;

	@Override
	public void process(Message message, Connection connection) {
		if (message instanceof PersistFragmentMessage) {
			PersistFragmentMessage persistFragmentMessage = (PersistFragmentMessage) message;
			connection.persistFragment(persistFragmentMessage.getFileName(), persistFragmentMessage.getBytes());
		} else {
			this.next.process(message, connection);
		}
	}

	@Override
	public void setNext(ServerProcessor next) {
		this.next = next;
	}
}
