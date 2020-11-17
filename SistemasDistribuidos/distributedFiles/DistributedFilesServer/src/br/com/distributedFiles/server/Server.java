package br.com.distributedFiles.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static List<Connection> SERVER_CONNECTIONS = new ArrayList<>();
	public static List<Connection> CLIENT_CONNECTIONS = new ArrayList<>();
	public static String BASE_DIRECTORY_PATH = "/home/leonardo/temp_distributed_files/" + System.getProperty("port") + "/";

	public Server(Integer port, boolean mainServer) {
		if (mainServer) {
			SERVER_CONNECTIONS.add(new Connection("localhost", 8082));
			SERVER_CONNECTIONS.add(new Connection("localhost", 8083));
			SERVER_CONNECTIONS.add(new Connection("localhost", 8084));
		}

		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				CLIENT_CONNECTIONS.add(new Connection(clientSocket));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
