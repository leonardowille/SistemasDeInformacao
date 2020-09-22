/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.client;

import br.com.chat.common.*;
import br.com.chat.common.messages.*;
import br.com.chat.common.messages.client.ClientDisconnectMessage;
import br.com.chat.common.messages.client.ClientIdentityMessage;
import br.com.chat.common.messages.client.ClientPrivateChatMessageMessage;
import br.com.chat.common.messages.client.ClientPublicChatMessageMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class WriterSocket implements Runnable{

    Socket socket = null;
    Thread thread = null;
    ObjectOutputStream out = null;
    Client client = null;

    public WriterSocket(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
        try{
            out = new ObjectOutputStream(socket.getOutputStream());
        }catch(IOException e){
            System.err.println("ERRO 0:...");
        }
        thread = new Thread(this);
        thread.start();
    }

    private void sendMessage(Message message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.identify();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendPublicChatMessage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendPrivateChatMessage();
    }

    private void identify() {
        sendMessage(new ClientIdentityMessage(new User("xyz")));
    }

    private void disconnect() {
        sendMessage(new ClientDisconnectMessage());
    }

    private void sendPublicChatMessage() {
        sendMessage(new ClientPublicChatMessageMessage("Mensagem publica do usuário XYZ"));
    }

    private void sendPrivateChatMessage() {
        sendMessage(new ClientPrivateChatMessageMessage(client.getConnectedClients().get(0), "Mensagem privada do usuário XYZ para o usuário ABC"));
    }
}
