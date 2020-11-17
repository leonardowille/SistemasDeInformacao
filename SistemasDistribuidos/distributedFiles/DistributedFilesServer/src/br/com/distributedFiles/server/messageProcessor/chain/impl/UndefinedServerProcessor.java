package br.com.distributedFiles.server.messageProcessor.chain.impl;

import br.com.distributedFiles.messages.Message;
import br.com.distributedFiles.server.Connection;
import br.com.distributedFiles.server.messageProcessor.chain.ServerProcessor;

public class UndefinedServerProcessor implements ServerProcessor {

	@Override
	public void process(Message message, Connection connection) {
		System.out.println("Undefined Message");
	}

	@Override
	public void setNext(ServerProcessor next) {
	}
}
