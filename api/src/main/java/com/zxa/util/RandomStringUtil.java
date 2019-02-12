package com.zxa.util;

import org.springframework.util.StringUtils;

import java.util.Random;

public class RandomStringUtil {
    private final String characterArray;
    private final Random random=new Random();
    public RandomStringUtil(String characterArray) {
        this.characterArray = characterArray;
    }

    public String getRandomString(int length){
        if (length < 0){
            throw new IllegalArgumentException("The string length cannot be less than 0");
        }
        if (length == 0 || StringUtils.isEmpty(characterArray)){
            return "";
        }
        StringBuilder string=new StringBuilder();
        for (int i=0;i<length;i++){
            string.append(characterArray.charAt(random.nextInt(characterArray.length())));
        }
        return  string.toString();
    }

    public static RandomStringUtilBuilder build(){
        return new RandomStringUtilBuilder();
    }

    public static void main(String[] args) {
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
