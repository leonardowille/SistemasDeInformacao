package br.com.chat;

import br.com.chat.client.Client;
import br.com.chat.common.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChatClientForm extends JFrame implements ActionListener {

	private JPanel mainPanel;
	private JButton connectButton;
	private JTextField nicknameTextField;
	private JLabel messageLabel;
	private JList availableChatList;
	private JTextArea messageTextField;
	private JButton sendButton;
	private JList chatScreenList;

	private Boolean connectedOnServer = false;
	private Client client = null;

	private final DefaultListModel<User> connectedUsersListModel = new DefaultListModel<>();
	private final DefaultListModel<String> chatScreenListModel = new DefaultListModel<>();

	public static void main(String[] args) {
		JFrame frame = new ChatClientForm("Chat");
		frame.setVisible(true);
	}

	public ChatClientForm(String title) {
		super(title);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(mainPanel);
		this.pack();

		connectButton.addActionListener(this);
		sendButton.addActionListener(this);
		availableChatList.setModel(connectedUsersListModel);
		chatScreenList.setModel(chatScreenListModel);
	}

	private boolean validateNickname() {
		if (!"".equalsIgnoreCase(nicknameTextField.getText())) {
			return true;
		}
		messageLabel.setText("Informe um apelido");
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (connectButton.equals(event.getSource())) {
			connectButtonAction();
		} else if (sendButton.equals(event.getSource())) {
			sendButtonAction();
		}
	}

	private void connectButtonAction() {
		messageLabel.setText("");
		if (connectedOnServer) {
			client.disconnectOnServer();
			client = null;
			messageTextField.setEnabled(false);
			sendButton.setEnabled(false);
			nicknameTextField.setEnabled(true);
			connectButton.setText("Conectar");
			connectedOnServer = !connectedOnServer;
		} else {
			if (validateNickname()) {
				client = new Client(this);
				client.start();
				client.identifyOnServer(nicknameTextField.getText());
				messageTextField.setEnabled(true);
				sendButton.setEnabled(true);
				nicknameTextField.setEnabled(false);
				connectButton.setText("Desconectar");
				connectedOnServer = !connectedOnServer;
			}
		}
	}

	private void sendButtonAction() {
		if (messageTextField.getText().equals("")){
			return;
		}

		User selectedUser = (User) availableChatList.getSelectedValue();
		if (selectedUser == null) {
			client.sendPublicChatMessage(messageTextField.getText());
		} else {
			client.sendPrivateChatMessage(selectedUser, messageTextField.getText());
		}
		messageTextField.setText("");
	}

	public void addChatMessage(User user, String textMessage) {
		if (client.getCurrentUser().equals(user)) {
			chatScreenListModel.addElement(user.getNickname() + " (you): " + textMessage);
		} else {
			chatScreenListModel.addElement(user.getNickname() + ": " + textMessage);
		}
	}

	public void updateConnectedUsers(List<User> connectedUsers) {
		connectedUsersListModel.clear();
		connectedUsers.forEach(connectedUsersListModel::addElement);
	}
}
