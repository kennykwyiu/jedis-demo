package com.kenny.jedisdemo.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxWait(Duration.ofDays(1_000));
        jedisPool = new JedisPool(poolConfig,
                "192.168.74.128",
                6379,
                1000,
                "Your Own Password");
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

}
