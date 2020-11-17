package br.com.distributedFiles;

import br.com.distributedFiles.server.Server;

public class Main {

	public static void main(String[] args) {
		new Server(Integer.valueOf(System.getProperty("port")), Boolean.parseBoolean(System.getProperty("mainServer")));
	}
}
