package com.liang.vo;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9072574851801210965L;

	private String id;
	
	private String name;
	
	private String password;
	
	public User(){
		
	}

	
	public User(String id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}


	/**
	 * 获得id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
