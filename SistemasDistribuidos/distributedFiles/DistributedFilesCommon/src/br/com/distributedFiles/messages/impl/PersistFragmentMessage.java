package br.com.distributedFiles.messages.impl;

import br.com.distributedFiles.messages.Message;

public class PersistFragmentMessage implements Message {

	private String fileName;
	private byte[] bytes;

	public PersistFragmentMessage(String fileName, byte[] bytes) {
		this.fileName = fileName;
		this.bytes = bytes;
	}

	public String getFileName() {
		return fileName;
	}

	public byte[] getBytes() {
		return bytes;
	}
}