package com.zxa.entitis;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.zxa.utils.RandomStringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class gsonRedisTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    Gson localGson;
    RandomStringUtil randomStringUtil = RandomStringUtil.builder().letters().numbers().create();

    @Test
    public void redisTest() {
        List<String> strings = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Person person = new Person();
            person.setId(Long.valueOf(i));
            person.setName(randomStringUtil.getRandomString(6));
            person.setAge(i);
            person.setLastLoginTime(LocalTime.now());
            person.setCreateTime(LocalDateTime.now());
            person.setBirthday(LocalDate.now());
            String key = randomStringUtil.getRandomString(3);
            redisTemplate.opsForValue().set(key, person);
            strings.add(key);
        }
        for (String string : strings) {
            Person person = (Person) redisTemplate.opsForValue().get(string);
            System.out.println(person);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("time:" + time);
    }

}
