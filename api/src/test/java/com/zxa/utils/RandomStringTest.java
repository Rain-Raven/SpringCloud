package com.zxa.utils;

import org.junit.Test;

public class RandomStringTest {

    @Test
    public  void testBuilder(){
        for (int i = 0; i < 20; i++) {
            String string = new RandomStringUtilFactory().letters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
        for (int i = 0; i < 20; i++) {
            String string = new RandomStringUtilFactory().lowercaseLetters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
        for (int i = 0; i < 20; i++) {
            String string = new RandomStringUtilFactory().CapitalLetters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
    }

    @Test
    public void testBuild(){
        for (int i = 0; i < 20; i++) {
            String string = RandomStringUtil.builder().letters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
        for (int i = 0; i < 20; i++) {
            String string = RandomStringUtil.builder().lowercaseLetters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
        for (int i = 0; i < 20; i++) {
            String string = RandomStringUtil.builder().CapitalLetters().numbers().create().getRandomString(i);
            System.out.println(string);
        }
    }
}
