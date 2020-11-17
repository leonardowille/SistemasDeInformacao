package br.com.distributedFiles.client;

import br.com.distributedFiles.client.messageProcessor.ClientMessageProcessor;
import br.com.distributedFiles.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ListenerSocket implements Runnable{

	private ClientMessageProcessor clientMessageProcessor = new ClientMessageProcessor();

	Socket socket = null;
	Thread thread = null;
	ObjectInputStream in = null;
	Client client = null;

	public ListenerSocket(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		Message message = null;
		try {
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				message = (Message) in.readObject();
				clientMessageProcessor.process(message, client);
			} catch (IOException e) {
//				closeConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
