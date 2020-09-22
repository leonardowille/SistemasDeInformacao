package br.com.chat.server.messageProcessor;

import br.com.chat.common.messages.Message;
import br.com.chat.server.ConnectedClient;
import br.com.chat.server.messageProcessor.chain.*;

public class ServerMessageProcessor {

	public void process(Message message, ConnectedClient connectedClient) {
		ServerProcessor identificationServerProcessor = new IdentifyProcessor();
		ServerProcessor disconnectionServerProcessor = new DisconnectProcessor();
		PublicChatMessageProcessor publicChatMessageProcessor = new PublicChatMessageProcessor();
		PrivateChatMessageProcessor privateChatMessageProcessor = new PrivateChatMessageProcessor();
		ServerProcessor unidefinedMessage = new UndefinedServerProcessor();

		identificationServerProcessor.setNext(disconnectionServerProcessor);
		disconnectionServerProcessor.setNext(publicChatMessageProcessor);
		publicChatMessageProcessor.setNext(privateChatMessageProcessor);
		privateChatMessageProcessor.setNext(unidefinedMessage);

		identificationServerProcessor.process(message, connectedClient);
	}
}
