package com.zxa.utils;

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

    public static RandomStringUtilFactory builder(){
        return new RandomStringUtilFactory();
    }

}
