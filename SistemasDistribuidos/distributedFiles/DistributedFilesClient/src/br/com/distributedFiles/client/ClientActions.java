package br.com.distributedFiles.client;

import java.io.File;

public class ClientActions {

	private Client client = null;
	public static String BASE_DIRECTORY_PATH = "/home/leonardo/temp_distributed_files/requested_files/";

	public ClientActions() {
		startConnection();
		client.sendFile(new File("/home/leonardo/temp_distributed_files/distributed_file.txt"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		client.requestFile("distributed_file.txt");
		while (true);
	}

	private void startConnection() {
		if (client == null) {
			client = new Client();
			client.start();
		}
	}
}
