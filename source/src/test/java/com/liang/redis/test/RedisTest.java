package com.liang.redis.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.liang.dao.IUserDao;
import com.liang.vo.User;


@ContextConfiguration(locations = {"classpath*:applicationContext.xml"}) 
public class RedisTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private IUserDao userDao;

	
//	@Before
//	public void init(){
//		ApplicationContext context = new ClassPathXmlApplicationContext();
//	}
	
	/**
	 * 新增
	 */
	@Test
	public void testAddUser(){
		User user = new User();
		user.setId("user1");
		user.setName("China姚明");
		boolean result = userDao.add(user);
		Assert.assertTrue(result);
	}
	
	
	/**
	 * 获取
	 */
	@Test
	public void testGetUser(){
		String id = "user1";
		User user = userDao.getUser(id);
		System.out.println(user.getName());
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getName(), "spring+redis");
	}
	
	@Test
	public void testDelUser(){
		String id = "user1";
		userDao.delete(id);
	}

	
	/**
	 * 注入userDao
	 * @param userDao
	 */
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public IUserDao getUserDao() {
		return userDao;
	}
	
}
