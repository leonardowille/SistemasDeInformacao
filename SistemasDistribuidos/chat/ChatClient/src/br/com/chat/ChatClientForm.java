package br.com.chat;

import br.com.chat.client.Client;

import javax.swing.*;
import java.awt.*;

public class ChatClientForm extends JFrame {

	private JPanel mainPanel;
	private JButton conectarButton;
	private JTextField nicknameTextField;
	private JLabel messageLabel;

	private Boolean connectedOnServer = false;
	private Client client = null;

	public ChatClientForm(String title) throws HeadlessException {
		super(title);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(mainPanel);
		this.pack();
		conectarButton.addActionListener(actionEvent -> {
			messageLabel.setText("");
			if (connectedOnServer) {
				client.disconnectOnServer();
				client = null;
				nicknameTextField.setEnabled(true);
				conectarButton.setText("Conectar");
				connectedOnServer = !connectedOnServer;
			} else {
				if (validateNickname()) {
					client = new Client();
					client.start();
					client.identifyOnServer(nicknameTextField.getText());
					nicknameTextField.setEnabled(false);
					conectarButton.setText("Desconectar");
					connectedOnServer = !connectedOnServer;
				}
			}
		});
	}

	private boolean validateNickname() {
		if (!"".equalsIgnoreCase(nicknameTextField.getText())) {
			return true;
		}
		messageLabel.setText("Informe um apelido");
		return false;
	}

	public static void main(String[] args) {
		JFrame frame = new ChatClientForm("Chat");
		frame.setVisible(true);
	}
}
