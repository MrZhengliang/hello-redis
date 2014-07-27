/**
 * 
 */
package com.liang.dao;

import java.util.List;

import com.liang.vo.User;

/**
 * 
 * IUserDao接口
 * @author Administrator
 *
 */
public interface IUserDao {

	/**
	 * 新增
	 * @param user
	 * @return
	 */
	boolean add(User user);
	
	/**
	 * 批量新增
	 * @param userList
	 * @return
	 */
	boolean add(List<User> userList);
	
	/**
	 * 删除
	 * @param key
	 */
	void delete(String key);
	
	/**
	 * 删除多个
	 * @param userList
	 */
	void delete(List<String> userList);
	
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	boolean update(User user);
	
	/**
	 * 通过key获取
	 * @param keyId
	 * @return
	 */
	User getUser(String keyId);
	
}
