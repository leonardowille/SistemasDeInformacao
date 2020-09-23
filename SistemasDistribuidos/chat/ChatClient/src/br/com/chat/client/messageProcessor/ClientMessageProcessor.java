package br.com.chat.client.messageProcessor;

import br.com.chat.client.Client;
import br.com.chat.client.messageProcessor.chain.*;
import br.com.chat.common.messages.Message;

public class ClientMessageProcessor {

	public void process(Message message, Client client) {
		IdentifyResponseProcessor identifyResponseProcessor = new IdentifyResponseProcessor();
		ConnectedUsersProcessor connectedUsersProcessor = new ConnectedUsersProcessor();
		ServerPublicChatMessageMessageProcessor serverPublicChatMessageMessageProcessor = new ServerPublicChatMessageMessageProcessor();
		ServerPrivateChatMessageMessageProcessor serverPrivateChatMessageMessageProcessor = new ServerPrivateChatMessageMessageProcessor();
		UndefinedClientProcessor undefinedClientProcessor = new UndefinedClientProcessor();

		identifyResponseProcessor.setNext(connectedUsersProcessor);
		connectedUsersProcessor.setNext(serverPublicChatMessageMessageProcessor);
		serverPublicChatMessageMessageProcessor.setNext(serverPrivateChatMessageMessageProcessor);
		serverPrivateChatMessageMessageProcessor.setNext(undefinedClientProcessor);

		identifyResponseProcessor.process(message, client);
	}
}
