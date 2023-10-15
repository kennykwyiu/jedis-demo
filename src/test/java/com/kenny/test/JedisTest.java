package com.kenny.test;

import com.kenny.jedisdemo.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
//        jedis = new Jedis("192.168.74.128", 6379);
        jedis = JedisConnectionFactory.getJedis();
//        jedis.auth("");
        jedis.select(1);
    }

    @Test
    void testString() {
        String result = jedis.set("name", "Kenny Yiu");
        System.out.println("result = " + result);
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testHash() {
        jedis.hset("user:1", "name", "Jack");
        jedis.hset("user:1", "age", "21");

        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);

    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
