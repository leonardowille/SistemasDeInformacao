package br.com.chat.client.messageProcessor.chain;

import br.com.chat.client.Client;
import br.com.chat.client.messageProcessor.ClientProcessor;
import br.com.chat.common.messages.Message;

public class UndefinedClientProcessor implements ClientProcessor {

	@Override
	public void process(Message message, Client client) {
		System.out.println("Undefined Message");
	}

	@Override
	public void setNext(ClientProcessor next) {
	}
}
