package br.com.distributedFiles.client.messageProcessor.chain;

import br.com.distributedFiles.client.Client;
import br.com.distributedFiles.messages.Message;

public interface ClientProcessor {
	void process(Message message, Client client);
	void setNext(ClientProcessor next);
}
