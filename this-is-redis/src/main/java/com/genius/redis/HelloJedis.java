package com.genius.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import redis.clients.jedis.Jedis;

public class HelloJedis {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);
		String result = jedis.set("redisbook", "Hello redis");
		System.out.println(result);
		System.out.println(jedis.get("redisbook"));

		RedisClient redisClient = RedisClient.create("redis://localhost:6379");
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisStringCommands<String, String> sync = connection.sync();
		String value = sync.get("redisbook");
		connection.close();
		redisClient.shutdown();
		System.out.println(value);
	}
}