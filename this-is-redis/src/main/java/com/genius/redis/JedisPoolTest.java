package com.genius.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

public class JedisPoolTest {

	public static void main(String[] args) {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxIdle(20);
		config.setBlockWhenExhausted(true);

		JedisPool pool = new JedisPool(config, "localhost", 6379);

		Jedis firstClient = pool.getResource();
		firstClient.hset("info:genius", "이름", "최성조");
		firstClient.hset("info:genius", "생일", "1980-11-09");

		Jedis secondsClient = pool.getResource();
		Map<String, String> result = secondsClient.hgetAll("info:genius");
		System.out.println(result);
	}
}