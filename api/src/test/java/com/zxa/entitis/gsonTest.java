package com.zxa.entitis;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zxa.utils.RandomStringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class gsonTest {
    @Autowired
    Gson localGson;

    private RandomStringUtil randomStringUtil=RandomStringUtil.builder().letters().create();
    @Test
    public void testLocalDate() {
        Person person = new Person();
        person.setBirthday(LocalDate.of(1996, 4, 19));
        person.setCreateTime(LocalDateTime.now());
 //       person.setLastLoginTime(LocalTime.now());
        System.out.println(localGson.toJson(person));
        String string = "{\"name\":null,\"id\":null,\"age\":null,\"birthday\":\"1996-04-19\",\"lastLoginTime\":null,\"createTime\":\"2019-02-12 13:49:23\"}";
        Person person1 = localGson.fromJson(string, Person.class);
        System.out.println(person1);
    }

    @Test
    public void testPersonList(){
        List people=new ArrayList<>();
        for (int i=0;i<20;i++){
            Person person=new Person();
            person.setId(Long.valueOf(i));
            person.setName(randomStringUtil.getRandomString(6));
            person.setAge(i);
            person.setLastLoginTime(LocalTime.now());
            person.setCreateTime(LocalDateTime.now());
            person.setBirthday(LocalDate.now());
            people.add(person);
        }
        System.out.println(localGson.toJson(people));
        List<Person> p=localGson.fromJson("[{\"name\":\"PQyDui\",\"id\":0,\"age\":0,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"cwypTY\",\"id\":1,\"age\":1,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"sPEkZS\",\"id\":2,\"age\":2,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"mvgpNO\",\"id\":3,\"age\":3,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"tJNHma\",\"id\":4,\"age\":4,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"iyvGBN\",\"id\":5,\"age\":5,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"rUSEBL\",\"id\":6,\"age\":6,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"GuBmjn\",\"id\":7,\"age\":7,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"hEKMYs\",\"id\":8,\"age\":8,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"lFffrQ\",\"id\":9,\"age\":9,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"dDxUgI\",\"id\":10,\"age\":10,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"fErTQn\",\"id\":11,\"age\":11,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"SKwKHu\",\"id\":12,\"age\":12,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"EhgMvr\",\"id\":13,\"age\":13,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"BTnooM\",\"id\":14,\"age\":14,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"mFSwdO\",\"id\":15,\"age\":15,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"hTQRtW\",\"id\":16,\"age\":16,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"wAtCxF\",\"id\":17,\"age\":17,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"VErlwG\",\"id\":18,\"age\":18,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"},{\"name\":\"hmjUBr\",\"id\":19,\"age\":19,\"birthday\":\"2019-02-12\",\"lastLoginTime\":\"13:38:50\",\"createTime\":\"2019-02-12 13:38:50\"}]",List.class);
        System.out.println(p);
    }
}
