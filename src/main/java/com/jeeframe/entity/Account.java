package com.jeeframe.entity;

/**
 * <p>
 * descrption:
 * </p>
 * 
 */
public class Account {
	private int id;
	private String name;

	public Account(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public Account(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
