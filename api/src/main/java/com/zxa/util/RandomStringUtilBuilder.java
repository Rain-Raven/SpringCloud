package com.zxa.util;

public class RandomStringUtilBuilder {
    private static final String NUMBERS = "0123456789";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private StringBuilder characterArray = new StringBuilder();

    public RandomStringUtilBuilder numbers() {
        return String(NUMBERS);
    }

    public RandomStringUtilBuilder lowercaseLetters() {
        return String(LOWERCASE_LETTERS);
    }

    public RandomStringUtilBuilder CapitalLetters() {
        return String(CAPITAL_LETTERS);
    }

    public RandomStringUtilBuilder letters() {
        String(LOWERCASE_LETTERS);
        return String(CAPITAL_LETTERS);
    }

    public RandomStringUtilBuilder String(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        characterArray.append(string);
        return this;
    }

    public RandomStringUtil create() {
        return new RandomStringUtil(characterArray.toString());
    }

    public RandomStringUtilBuilder(StringBuilder characterArray) {
        this.characterArray = characterArray;
    }

    public RandomStringUtilBuilder() {
    }

    public static void main(String[] args) {
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
}
