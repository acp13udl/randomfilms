package com.udl.softarch.springexample.utils;

import com.udl.softarch.springexample.models.Greeting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by adrian on 16/3/15.
 */
public class GenerateRandom {

    public static Greeting RandomGreeting(Iterable<Greeting> greetins){

        Random random = new Random();
        ArrayList<Greeting> greetings =(ArrayList<Greeting>) greetins;
        int randomint = random.nextInt(greetings.size());
        return greetings.get(randomint);
    }
}
