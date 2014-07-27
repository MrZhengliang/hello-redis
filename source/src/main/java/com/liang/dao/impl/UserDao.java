package com.liang.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import com.liang.dao.AbstractBaseRedisDao;
import com.liang.dao.IUserDao;
import com.liang.vo.User;

/**
 * @author Administrator
 *
 */
public class UserDao extends AbstractBaseRedisDao<String, User> implements IUserDao {

	/* 新增user
	 * @see com.liang.dao.IUserDao#add(com.liang.vo.User)
	 */
	public boolean add(final User user) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>(){			
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(user.getId());
				byte[] name = serializer.serialize(user.getName());			
				
				//Boolean b = connection.setNX(key,name);
				//System.out.println("是否关闭:"+connection.isClosed());
				//System.out.println("setNX:"+b);
				return connection.setNX(key,name);
			}			
		});
		return result;
	}

	/**
	 * 批量新增 使用pipeline方式   
	 * 参数加final，不准许改变属性
	 */
	public boolean add(final List<User> userList) {
		Assert.notEmpty(userList);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>(){

			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				for(User u : userList){
					byte[] key = serializer.serialize(u.getId());
					byte[] name = serializer.serialize(u.getName());
					connection.setNX(key, name);//设置属性和值
				}
				return true;
			}
			
		},false,true);
		return result;
	}

	/* 删除
	 * (non-Javadoc)
	 * @see com.liang.dao.IUserDao#delete(java.lang.String)
	 */
	public void delete(String key) {
		List<String> list = new ArrayList<String>();
		list.add(key);
		delete(list);
	}

	/* 删除多个
	 * (non-Javadoc)
	 * @see com.liang.dao.IUserDao#delete(java.util.List)
	 */
	public void delete(List<String> keys) {
		redisTemplate.delete(keys);
	}

	/* 
	 * 通过key获取
	 * (non-Javadoc)
	 * @see com.liang.dao.IUserDao#getUser(java.lang.String)
	 */
	public User getUser(final String keyId) {
		User result = redisTemplate.execute(new RedisCallback<User>(){

			public User doInRedis(RedisConnection connection)
					throws DataAccessException {
				//获取template的serialize
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(keyId);
				byte[] value = connection.get(key);//获取value
				if(value == null){
					return null;
				}
				String name = serializer.deserialize(value);
				return new User(keyId,name,null);//返回用户
			}		
		});
		return result;
	}

	/* 
	 * 更新用户
	 * (non-Javadoc)
	 * @see com.liang.dao.IUserDao#update(com.liang.vo.User)
	 */
	public boolean update(final User user) {
		String key = user.getId();
		if(getUser(key) == null){
			throw new NullPointerException("数据行不存在,key="+key);
		}
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>(){
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				//获取序列化工具
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(user.getId());
				byte[] name = serializer.serialize(user.getName());
				connection.set(key, name);				
				return true;
			}			
		});
		return result;
	}

}
