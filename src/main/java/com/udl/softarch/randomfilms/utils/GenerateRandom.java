package com.udl.softarch.randomfilms.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by adrian on 16/3/15.
 */
public class GenerateRandom {

    public static List<Long> randomLongValues(long size){
        List<Long> res = new ArrayList<>();
        Random random = new Random();
        int x = 0;
        while(x<10){
            long aux = random.nextLong()%size;
            if(!res.contains(aux)){
                res.add(aux);
                x++;
            }
        }
        return res;
    }
}
