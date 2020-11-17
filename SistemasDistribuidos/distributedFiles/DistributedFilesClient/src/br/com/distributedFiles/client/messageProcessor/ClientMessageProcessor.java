package br.com.distributedFiles.client.messageProcessor;

import br.com.distributedFiles.client.Client;
import br.com.distributedFiles.client.messageProcessor.chain.impl.RequestFileResponseProcessor;
import br.com.distributedFiles.client.messageProcessor.chain.impl.UndefinedClientProcessor;
import br.com.distributedFiles.client.messageProcessor.chain.impl.UpdatePersistedFilesProcessor;
import br.com.distributedFiles.messages.Message;

public class ClientMessageProcessor {

	public void process(Message message, Client client) {
		UpdatePersistedFilesProcessor updatePersistedFilesProcessor = new UpdatePersistedFilesProcessor();
		RequestFileResponseProcessor requestFileResponseProcessor = new RequestFileResponseProcessor();
		UndefinedClientProcessor undefinedClientProcessor = new UndefinedClientProcessor();

		updatePersistedFilesProcessor.setNext(requestFileResponseProcessor);
		requestFileResponseProcessor.setNext(undefinedClientProcessor);

		updatePersistedFilesProcessor.process(message, client);
	}
}
