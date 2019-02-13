package com.zxa.utils;

public class RandomStringUtilFactory {
    private static final String NUMBERS = "0123456789";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private StringBuilder characterArray = new StringBuilder();

    public RandomStringUtilFactory numbers() {
        return String(NUMBERS);
    }

    public RandomStringUtilFactory lowercaseLetters() {
        return String(LOWERCASE_LETTERS);
    }

    public RandomStringUtilFactory capitalLetters() {
        return String(CAPITAL_LETTERS);
    }

    public RandomStringUtilFactory letters() {
        String(LOWERCASE_LETTERS);
        return String(CAPITAL_LETTERS);
    }

    public RandomStringUtilFactory String(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        characterArray.append(string);
        return this;
    }

    public RandomStringUtil create() {
        return new RandomStringUtil(characterArray.toString());
    }

    public RandomStringUtilFactory(StringBuilder characterArray) {
        this.characterArray = characterArray;
    }

    public RandomStringUtilFactory() {
    }
}
