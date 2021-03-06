package com.liang.dao;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * 
 * 抽象redisDao
 * @author Administrator
 *
 */
public abstract class AbstractBaseRedisDao <K, V>{

	/*
	 * 自动装配
	 */
	@Autowired
	protected RedisTemplate<K, V> redisTemplate ;

	
	/**
	 * 设置redisTemplate 
	 * @param redisTemplate
	 */
	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	
	/**
	 * 获取 RedisSerializer
	 */
	protected RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}
}
