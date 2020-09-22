package br.com.chat.common.messages.server;

import br.com.chat.common.User;
import br.com.chat.common.messages.Message;

import java.util.List;

public class ServerConnectedUsersMessage implements Message {

	private List<User> connectedUsers;

	public List<User> getConnectedUsers() {
		return connectedUsers;
	}

	public void setConnectedUsers(List<User> connectedUsers) {
		this.connectedUsers = connectedUsers;
	}
}
