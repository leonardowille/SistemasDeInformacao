package br.com.distributedFiles.messages.impl;

import br.com.distributedFiles.messages.Message;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ClientSendFileMessage implements Message {

	private String fileName;
	private byte[] bytes;

	public ClientSendFileMessage(File file) throws IOException {
		this.fileName = file.getName();
		this.bytes = Files.readAllBytes(file.toPath());
	}

	public String getFileName() {
		return fileName;
	}

	public byte[] getBytes() {
		return bytes;
	}
}