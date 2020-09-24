package br.com.chat.client;

import br.com.chat.ChatClientForm;
import br.com.chat.common.User;
import br.com.chat.common.messages.Message;
import br.com.chat.common.messages.client.ClientDisconnectMessage;
import br.com.chat.common.messages.client.ClientIdentityMessage;
import br.com.chat.common.messages.client.ClientPrivateChatMessageMessage;
import br.com.chat.common.messages.client.ClientPublicChatMessageMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

	private List<User> connectedUsers = new ArrayList<>();
	private User currentUser;
	private ListenerSocket listenerSocket;
	private ObjectOutputStream out = null;
	private ChatClientForm form;

	public Client(ChatClientForm form) {
		this.form = form;
	}

	public void connect(String host, Integer porta) {
		try {
			Socket socket = new Socket(host, porta);
			listenerSocket = new ListenerSocket(socket, this);
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage(Message message) {
		try {
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		this.connect("localhost", 8084);
	}

	public void disconnectUser() {
		this.currentUser = null;
		System.out.println("disconnectUser");
	}

	public void receiveTextMessage(User user, String textMessage) {
		form.addChatMessage(user, textMessage);
	}

	public void identifyOnServer(String nickname) {
		sendMessage(new ClientIdentityMessage(new User(nickname)));
	}

	public void disconnectOnServer() {
		sendMessage(new ClientDisconnectMessage());
		listenerSocket.closeConnection();
		disconnectUser();
	}

	public void sendPublicChatMessage(String textMessage) {
		sendMessage(new ClientPublicChatMessageMessage(textMessage));
	}

	public void sendPrivateChatMessage(User selectedUser, String textMessage) {
		sendMessage(new ClientPrivateChatMessageMessage(selectedUser, textMessage));
	}

	public void updateConnectedUsers(List<User> connectedUsers) {
		this.connectedUsers = connectedUsers;
		form.updateConnectedUsers(this.connectedUsers);
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
		System.out.println("currentUser nick: " + currentUser.getNickname());
	}
}
