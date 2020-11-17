package br.com.distributedFiles.server.messageProcessor.chain;

import br.com.distributedFiles.server.Connection;
import br.com.distributedFiles.messages.Message;

public interface ServerProcessor {
	void process(Message message, Connection connection);
	void setNext(ServerProcessor next);
}
