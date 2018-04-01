package com.brave.utils;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
@Component(value = "jedisCache")
public class JedisCache {
	private static Jedis jedis = new Jedis("127.0.0.1");
	public static Jedis getMyRedis(){
		return jedis;
	}
}
