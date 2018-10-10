package com.jesse.spring_redis.spring_redis_study;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JedisConfig.class)
public class RedisTest {
	
	@Autowired
	private RedisTemplate<String, Object> redis;
	
	@Autowired
	private StringRedisTemplate stringRedis;
	
	public Date getDate() {
		
		Date date = new Date();
		try{
		    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    try {
				date = sdf.parse("2018-10-04 18:59:00");
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(ParseException e){
		    System.out.println(e.getMessage());
		}
		return date;		
	}
	
	@Test
	public void testString() {
		
		//spring-data
		stringRedis.opsForValue().set("ser1", "100");
		redis.opsForValue().set("ser2", "100");
		
		System.out.println(stringRedis.opsForValue().get("ser1"));
		System.out.println(redis.opsForValue().get("ser2"));
		
		//Jedis client
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set("hello", "world");
			System.out.println(jedis.get("hello"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
			if (jedisPool != null) {
				jedisPool.close();
			}
		}
		
	}
	
	
	/*	@Test
	public void testList() {
		
		Product product1 = new Product();
		product1.setName("cellphone");
		product1.setSku("000001");
		product1.setPrice(2000.00f);
		
		Product product2 = new Product();
		product2.setName("SUV CAR");
		product2.setSku("000002");
		product2.setPrice(200000.00f);
		
		Product product3 = new Product();
		product3.setName("SUV CAR");
		product3.setSku("000003");
		product3.setPrice(200000.00f);
		
		BoundListOperations<String, Object> cart_no_ser = redis.boundListOps("cart_no_ser");
		
		System.out.println("List Result:");
		cart_no_ser.rightPush(product1);
		cart_no_ser.leftPush(product2);
		
		System.out.println(cart_no_ser.size());		
		System.out.println(cart_no_ser.getKey());		
		
	}*/
	
	/*@Test
	public void testSet() {
		
		Product product1 = new Product();
		product1.setName("cellphone");
		product1.setSku("000001");
		product1.setPrice(2000.00f);
		
		Product product2 = new Product();
		product2.setName("SUV CAR");
		product2.setSku("000002");
		product2.setPrice(200000.00f);
		
		Product product3 = new Product();
		product3.setName("SUV CAR");
		product3.setSku("000003");
		product3.setPrice(200000.00f);
		
		BoundSetOperations<String, Object> cartset1 = redis.boundSetOps("cartset1");
		BoundSetOperations<String, Object> cartset2 = redis.boundSetOps("cartset2");
		
		System.out.println("Set Result:");
		
		cartset1.add("cartset1", product1);
		cartset1.add("cartset1", product2);
		System.out.println(cartset1.size());
		
		cartset2.add("cartset2", product3);
		System.out.println(cartset2.size());
		
		System.out.println("difference: " + redis.opsForSet().difference("cartset1", "cartset2"));
		System.out.println("union: " + redis.opsForSet().union("cartset1", "cartset2"));
		
	}
	*/
	

}
