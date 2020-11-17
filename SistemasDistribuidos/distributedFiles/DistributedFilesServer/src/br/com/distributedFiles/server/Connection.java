package br.com.distributedFiles.server;

import br.com.distributedFiles.messages.Message;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.distributedFiles.messages.impl.ClientSendFileMessage;
import br.com.distributedFiles.messages.impl.PersistFragmentMessage;
import br.com.distributedFiles.messages.impl.RequestFileResponseMessage;
import br.com.distributedFiles.messages.impl.UpdatePersistedFilesMessage;
import br.com.distributedFiles.server.messageProcessor.ServerMessageProcessor;

public class Connection implements Runnable {

	private ServerMessageProcessor serverMessageProcessor = new ServerMessageProcessor();

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Thread thread;

	public Connection(Socket socket) {
		this.socket = socket;
		configure();
		start();
	}

	public Connection(String host, Integer port) {
		try {
			this.socket = new Socket(host, port);
			configure();
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void configure() {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void start() {
		thread = new Thread(this);
		thread.start();
	}

	public void sendMessage(Message message) {
		try {
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Message message;
		try {
			while (true) {
				message = (Message) in.readObject();
				serverMessageProcessor.process(message, this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void persistFile(ClientSendFileMessage clientSendFileMessage) {
		List<byte[]> fragments = getFragmentsFromFile(clientSendFileMessage.getBytes());
		persistFragment(clientSendFileMessage.getFileName(), fragments.remove(0));
		for (int i = 0; i < 3; i++) {
			Server.SERVER_CONNECTIONS.get(i).sendMessage(new PersistFragmentMessage(clientSendFileMessage.getFileName(), fragments.get(i)));
		}
		sendFilesPersisted();
	}

	public void persistFragment(String fileName, byte[] bytes) {
		verifyPath(Server.BASE_DIRECTORY_PATH);
		try (FileOutputStream fos = new FileOutputStream(Server.BASE_DIRECTORY_PATH + fileName)) {
			fos.write(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendFilesPersisted() {
		File baseDir = new File(Server.BASE_DIRECTORY_PATH);
		UpdatePersistedFilesMessage updatePersistedFilesMessage = new UpdatePersistedFilesMessage(Arrays.stream(Objects.requireNonNull(baseDir.listFiles()))
				.map(File::getName)
				.collect(Collectors.toList()));
		Server.CLIENT_CONNECTIONS.parallelStream().forEach(connection -> sendMessage(updatePersistedFilesMessage));
	}

	private void verifyPath(String directoryPath) {
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}

	private List<byte[]> getFragmentsFromFile(byte[] bytes) {
		int fragmentSize = bytes.length / 4;

		List<byte[]> fragments = new ArrayList<>();
		int initialRange = 0;
		for (int i = 1; i <= 3; i++) {
			fragments.add(Arrays.copyOfRange(bytes, initialRange, fragmentSize * i));
			initialRange += fragmentSize;
		}
		fragments.add(Arrays.copyOfRange(bytes, initialRange, bytes.length));

		return fragments;
	}

	public void clientRequestFile(String fileName) {
		byte[] bytes = null;
		File file = new File(Server.BASE_DIRECTORY_PATH + fileName);
		try {
			bytes = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendMessage(new RequestFileResponseMessage(fileName, bytes));
	}
}
