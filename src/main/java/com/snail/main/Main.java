package com.snail.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.snail.entity.Account;
import com.snail.service.AccountService;

/**
 * <p>
 * descrption:
 * </p>
 * 
 * @author fuzl
 * @date 2015年2月6日
 * @Copyright 2015 Snail Soft, Inc. All rights reserved.
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-cache-anno.xml");// 加载 spring 配置文件

		AccountService s = (AccountService) context
				.getBean("accountServiceBean");
		// // 第一次查询，应该走数据库
		// System.out.print("first query...");
		// s.getAccountByName("somebody");
		// // 第二次查询，应该不查数据库，直接返回缓存的值
		// System.out.print("second query...");
		//
		// s.getAccountByName("somebody");
		// System.out.println();

		Account a1 = s.getAccount("hellobody", "123456", true);// 应该查询数据库
		System.out.println("real querying A1... username:" + a1.getName()+",password:"+a1.getPassword());
		Account a2 = s.getAccount("hellobody", "123456", true);// 应该走缓存
		System.out.println("real querying A1... username:" + a2.getName()+",password:"+a2.getPassword());
		Account a3 = s.getAccount("hellobody", "123456", false);// 应该走缓存
		System.out.println("real querying A1... username:" + a3.getName()+",password:"+a3.getPassword());

		Account a4 = s.getAccount("kitbody", "654321", true);// 应该查询数据库
		System.out.println("real querying A1... username:" + a4.getName()+",password:"+a4.getPassword());
		Account a5 = s.getAccount("kitbody", "654321", true);// 应该走缓存
		System.out.println("real querying A1... username:" + a5.getName()+",password:"+a4.getPassword());
	}

}
