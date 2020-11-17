package br.com.distributedFiles.client.messageProcessor.chain.impl;

import br.com.distributedFiles.client.Client;
import br.com.distributedFiles.client.messageProcessor.chain.ClientProcessor;
import br.com.distributedFiles.messages.Message;

public class UndefinedClientProcessor implements ClientProcessor {

	@Override
	public void process(Message message, Client client) {
		System.out.println("Undefined Message");
	}

	@Override
	public void setNext(ClientProcessor next) {
	}
}
