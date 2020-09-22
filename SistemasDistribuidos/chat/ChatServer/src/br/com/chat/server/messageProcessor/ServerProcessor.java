package br.com.chat.server.messageProcessor;

import br.com.chat.common.messages.Message;
import br.com.chat.server.ConnectedClient;

public interface ServerProcessor {
	void process(Message message, ConnectedClient connectedClient);
	void setNext(ServerProcessor next);
}
