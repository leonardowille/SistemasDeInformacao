package br.com.chat.server.messageProcessor.chain;

import br.com.chat.common.messages.Message;
import br.com.chat.server.ConnectedClient;
import br.com.chat.server.messageProcessor.ServerProcessor;

public class UndefinedServerProcessor implements ServerProcessor {

	@Override
	public void process(Message message, ConnectedClient connectedClient) {
		System.out.println("Undefined Message");
	}

	@Override
	public void setNext(ServerProcessor next) {
	}
}
