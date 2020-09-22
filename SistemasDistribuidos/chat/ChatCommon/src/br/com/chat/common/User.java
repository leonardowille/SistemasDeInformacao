package br.com.chat.common;

import java.io.Serializable;

public class User implements Serializable {

	private String nickname;

	public User(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}
}
