package br.com.distributedFiles.messages.impl;

import br.com.distributedFiles.messages.Message;

public class ClientRequestFileMessage implements Message {

	private String fileName;

	public ClientRequestFileMessage(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
}