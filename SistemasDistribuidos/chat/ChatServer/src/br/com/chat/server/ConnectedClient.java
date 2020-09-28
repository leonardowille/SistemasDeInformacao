package br.com.chat.server;

import br.com.chat.common.*;
import br.com.chat.common.messages.*;
import br.com.chat.common.messages.client.ClientDisconnectMessage;
import br.com.chat.common.messages.server.*;
import br.com.chat.server.messageProcessor.ServerMessageProcessor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConnectedClient implements Runnable, Comparable {

	ServerMessageProcessor serverMessageProcessor = new ServerMessageProcessor();

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Thread thread;
	private User user;

	public ConnectedClient(Socket socket) {
		this.socket = socket;
	}

	public void configure() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(Message message) {
		try {
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage(Message message, User target) {
		Server.CONNECTED_CLIENTS.stream()
				.filter(connectedClient -> connectedClient.compareTo(target) > 0)
				.findFirst()
				.ifPresent(connectedClient -> connectedClient.sendMessage(message));
	}

	public void sendMessageForAll(Message message) {
		Server.CONNECTED_CLIENTS.forEach(connectedClient -> connectedClient.sendMessage(message));
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		Message message;
		try {
			while (true) {
				message = (Message) in.readObject();
				serverMessageProcessor.process(message, this);

				if (message instanceof ClientDisconnectMessage) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void identifyUser(User user) {
		ServerIdentifyResponseMessage serverIdentifyResponseMessage;
		if (Server.CONNECTED_CLIENTS.stream().anyMatch(connectedClient1 -> connectedClient1.compareTo(user) > 0)) {
			serverIdentifyResponseMessage = new ServerIdentifyResponseMessage();
			serverIdentifyResponseMessage.setError("Nickname already in use!");
		} else {
			this.user = user;
			serverIdentifyResponseMessage = new ServerIdentifyResponseMessage();
			serverIdentifyResponseMessage.setUser(user);
		}
		sendMessage(serverIdentifyResponseMessage);
		sendConnectedUsers();
	}

	private void sendConnectedUsers() {
		ServerConnectedUsersMessage serverConnectedUsersMessage = new ServerConnectedUsersMessage();
		List<User> connectedUsers = Server.CONNECTED_CLIENTS.stream()
				.filter(connectedClient -> Objects.nonNull(connectedClient.user))
				.map(connectedClient -> connectedClient.user)
				.collect(Collectors.toList());
		serverConnectedUsersMessage.setConnectedUsers(connectedUsers);
		sendMessageForAll(serverConnectedUsersMessage);
	}

	public void disconectUser() {
		Server.CONNECTED_CLIENTS.remove(this);
		disconnectClient();
		sendConnectedUsers();
	}

	private void disconnectClient() {
		try {
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int compareTo(Object client) {
		if (client instanceof User) {
			User userObject = (User) client;
			return this.user != null && this.user.getNickname().equalsIgnoreCase(userObject.getNickname()) ? 1 : 0;
		}
		return 0;
	}

	public void sendPublicChatMessage(String textMessage) {
		sendMessageForAll(new ServerPublicChatMessageMessage(this.user, textMessage));
	}

	public void sendPrivateChatMessage(User target, String textMessage) {
		sendMessage(new ServerPrivateChatMessageMessage(this.user, textMessage), target);
		sendMessage(new ServerPrivateChatMessageMessage(this.user, textMessage), this.user);
	}
}
