package br.com.distributedFiles.server.messageProcessor;

import br.com.distributedFiles.server.Connection;
import br.com.distributedFiles.server.messageProcessor.chain.ServerProcessor;
import br.com.distributedFiles.server.messageProcessor.chain.impl.ClientRequestFileProcessor;
import br.com.distributedFiles.server.messageProcessor.chain.impl.ClientSendFileProcessor;
import br.com.distributedFiles.server.messageProcessor.chain.impl.PersistFragmentProcessor;
import br.com.distributedFiles.server.messageProcessor.chain.impl.UndefinedServerProcessor;
import br.com.distributedFiles.messages.Message;

public class ServerMessageProcessor {

	public void process(Message message, Connection connection) {
		ClientSendFileProcessor clientSendFileProcessor = new ClientSendFileProcessor();
		PersistFragmentProcessor persistFragmentProcessor = new PersistFragmentProcessor();
		ClientRequestFileProcessor clientRequestFileProcessor = new ClientRequestFileProcessor();
		ServerProcessor unidefinedMessage = new UndefinedServerProcessor();

		clientSendFileProcessor.setNext(persistFragmentProcessor);
		persistFragmentProcessor.setNext(clientRequestFileProcessor);
		clientRequestFileProcessor.setNext(unidefinedMessage);

		clientSendFileProcessor.process(message, connection);
	}
}
