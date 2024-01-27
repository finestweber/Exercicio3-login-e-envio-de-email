package com.finestweber.exercicio3.util;

import java.security.SecureRandom;

public class RandomString {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUXYWZabcdefghijklmnopqrstuvxywz123456789";
    public static String generateRandom(int length){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<length; i++){
            int index = secureRandom.nextInt(CHARACTERS.length());
            char caractere = CHARACTERS.charAt(index);
            stringBuilder.append(caractere);
        }
        return stringBuilder.toString();
    }
}
