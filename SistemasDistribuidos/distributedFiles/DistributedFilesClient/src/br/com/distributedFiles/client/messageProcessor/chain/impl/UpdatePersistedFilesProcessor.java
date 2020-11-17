package br.com.distributedFiles.client.messageProcessor.chain.impl;

import br.com.distributedFiles.client.Client;
import br.com.distributedFiles.client.messageProcessor.chain.ClientProcessor;
import br.com.distributedFiles.messages.Message;
import br.com.distributedFiles.messages.impl.UpdatePersistedFilesMessage;

public class UpdatePersistedFilesProcessor implements ClientProcessor {

	private ClientProcessor next;

	public void process(Message message, Client client) {
		if (message instanceof UpdatePersistedFilesMessage) {
			UpdatePersistedFilesMessage updatePersistedFilesMessage = (UpdatePersistedFilesMessage) message;
			client.updatePersistedFiles(updatePersistedFilesMessage.getFileNames());
		} else {
			this.next.process(message, client);
		}
	}

	@Override
	public void setNext(ClientProcessor next) {
		this.next = next;
	}
}
