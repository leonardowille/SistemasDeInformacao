package br.com.distributedFiles.client;

import br.com.distributedFiles.messages.Message;
import br.com.distributedFiles.messages.impl.ClientRequestFileMessage;
import br.com.distributedFiles.messages.impl.ClientSendFileMessage;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client {

	private ListenerSocket listenerSocket;
	private ObjectOutputStream out = null;

	public void start() {
		this.connect("localhost", 8081);
	}

	public void connect(String host, Integer port) {
		try {
			Socket socket = new Socket(host, port);
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

	public void sendFile(File file) {
		try {
			sendMessage(new ClientSendFileMessage(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updatePersistedFiles(List<String> fileNames) {
		fileNames.forEach(System.out::println);
	}

	public void requestFile(String fileName) {
		sendMessage(new ClientRequestFileMessage(fileName));
	}

	public void requestedFileReceived(String fileName, byte[] bytes) {
		verifyPath(ClientActions.BASE_DIRECTORY_PATH);
		try (FileOutputStream fos = new FileOutputStream(ClientActions.BASE_DIRECTORY_PATH + fileName)) {
			fos.write(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void verifyPath(String directoryPath) {
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}
}
