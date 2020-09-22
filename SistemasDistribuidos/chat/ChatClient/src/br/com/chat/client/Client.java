package br.com.chat.client;

import br.com.chat.common.User;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

	private List<User> connectedUsers = new ArrayList<>();
	private User currentUser;

	public void connect(String host, Integer porta) {
		try {
			Socket socket = new Socket(host, porta);
			new ListenerSocket(socket, this);
			new WriterSocket(socket, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		this.connect("localhost", 8084);
	}

	public List<User> getConnectedClients() {
		return connectedUsers;
	}

	public void setConnectedUsers(List<User> connectedUsers) {
		this.connectedUsers = connectedUsers;
		System.out.println("connected Users: " + this.connectedUsers.size());
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
		System.out.println("currentUser nick: " + currentUser.getNickname());
	}

	public void disconnectUser() {
		this.currentUser = null;
		System.out.println("disconnectUser");
	}

	public void receiveTextMessage(User user, String textMessage) {
		System.out.println("Mensagem recebida - Usuário " + user.getNickname() + ": " + textMessage);
	}
}
