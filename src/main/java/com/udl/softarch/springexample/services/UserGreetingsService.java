package com.udl.softarch.springexample.services;

import com.udl.softarch.springexample.models.Greeting;
import com.udl.softarch.springexample.models.User;

/**
 * Created by adrian on 23/3/15.
 */
public interface UserGreetingsService {

    User getUserAndGreeting(Long userId);
    Greeting addGreetingToUser(Greeting greeting);
    Greeting updateGreetingFromUser(Greeting updateGreeting, Long greeting);
    void removeGreetingFromUser(Long greetingId);
}
