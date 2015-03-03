package com.snail.entity;

/**
 * <p>
 * descrption:
 * </p>
 * 
 * @author fuzl
 * @date 2015年2月6日
 * @Copyright 2015 Snail Soft, Inc. All rights reserved.
 */
public class Account {
	private int id;
	private String name;

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