package com.zxa;

import com.zxa.util.RandomStringUtil;
import com.zxa.util.RandomStringUtilBuilder;
import org.junit.Test;

public class RandomStringTest {

    @Test
    public  void testBuilder(){
        for (int i = 0; i < 20; i++) {
            String string = new RandomStringUtilBuilder().letters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
        for (int i = 0; i < 20; i++) {
            String string = new RandomStringUtilBuilder().lowercaseLetters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
        for (int i = 0; i < 20; i++) {
            String string = new RandomStringUtilBuilder().CapitalLetters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
    }

    @Test
    public void testBuild(){
        for (int i = 0; i < 20; i++) {
            String string = RandomStringUtil.build().letters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
        for (int i = 0; i < 20; i++) {
            String string = RandomStringUtil.build().lowercaseLetters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
        for (int i = 0; i < 20; i++) {
            String string = RandomStringUtil.build().CapitalLetters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
    }
}
