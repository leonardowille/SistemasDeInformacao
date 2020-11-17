package br.com.distributedFiles.messages.impl;

import br.com.distributedFiles.messages.Message;

import java.util.List;

public class UpdatePersistedFilesMessage implements Message {

	private List<String> fileNames;

	public UpdatePersistedFilesMessage(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public List<String> getFileNames() {
		return fileNames;
	}
}