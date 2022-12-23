package com.bc.utils;

import java.util.Random;

public class random {
    public String randomNum(){
        String str = null;
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<16;i++) {
                result.append(Integer.toHexString(new Random().nextInt(10)));
            }
            str =  result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
