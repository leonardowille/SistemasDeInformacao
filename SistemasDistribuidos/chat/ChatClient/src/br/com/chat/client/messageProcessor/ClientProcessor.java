package br.com.chat.client.messageProcessor;

import br.com.chat.client.Client;
import br.com.chat.common.messages.Message;

public interface ClientProcessor {
	void process(Message message, Client client);
	void setNext(ClientProcessor next);
}
