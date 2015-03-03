package com.snail.service;

import org.springframework.cache.annotation.Cacheable;

import com.snail.entity.Account;

/**
 * <p>
 * descrption:
 * </p>
 * 
 * @author fuzl
 * @date 2015年2月6日
 * @Copyright 2015 Snail Soft, Inc. All rights reserved.
 */
public class AccountService {

	@Cacheable(value = "accountCache")
	// 使用了一个缓存名叫 accountCache
	public Account getAccountByName(String userName) {
		// 方法内部实现不考虑缓存逻辑，直接实现业务
		System.out.println("real query account." + userName);
		return getFromDB(userName);
	}

	private Account getFromDB(String acctName) {
		System.out.println("real querying db..." + acctName);
		return new Account(acctName);
	}

	@Cacheable(value = "accountCache2", key = "#userName.concat(#password)")
	public Account getAccount(String userName, String password, boolean sendLog) {
		// 方法内部实现不考虑缓存逻辑，直接实现业务
		return getFromDB2(userName, password);

	}

	private Account getFromDB2(String acctName, String password) {
		System.out.println("real querying db... username:" + acctName+",password:"+password);
		return new Account(acctName);
	}
}
