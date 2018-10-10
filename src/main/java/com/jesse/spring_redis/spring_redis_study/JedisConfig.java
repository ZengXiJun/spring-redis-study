package com.jesse.spring_redis.spring_redis_study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class JedisConfig {
	
	@Bean
	public RedisConnectionFactory redisCF() {
		JedisConnectionFactory jedisConnetionFactory = new JedisConnectionFactory();
		jedisConnetionFactory.afterPropertiesSet();
		return jedisConnetionFactory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redis = new RedisTemplate<String, Object>();
		redis.setConnectionFactory(factory);
		redis.setKeySerializer(new StringRedisSerializer());
		redis.setValueSerializer(new JdkSerializationRedisSerializer());
		return redis;
	}
	
	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
		
		return new StringRedisTemplate(factory);
		
	}
	

}
