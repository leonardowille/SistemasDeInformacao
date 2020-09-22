package br.com.chat.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static List<ConnectedClient> CONNECTED_CLIENTS = new ArrayList<>();

	public void start() {
		try {
			ServerSocket serverSocket = new ServerSocket(8084);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				ConnectedClient cli = new ConnectedClient(clientSocket);
				cli.configure();
				cli.start();
				CONNECTED_CLIENTS.add(cli);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
