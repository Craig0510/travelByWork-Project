package com.example.demo.dto;

public class AccountConfig {
	private String account;
	private String password;

	public AccountConfig(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public AccountConfig() {
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AccountConfig{" + "account='" + account + '\'' + ", password='" + password + '\'' + '}';
	}
}
