/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.client;

import br.com.chat.client.messageProcessor.ClientMessageProcessor;
import br.com.chat.common.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ListenerSocket implements Runnable {

	ClientMessageProcessor clientMessageProcessor = new ClientMessageProcessor();

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
				closeConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeConnection() {
		try {
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
