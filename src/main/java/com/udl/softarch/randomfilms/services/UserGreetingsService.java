package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Greeting;
import com.udl.softarch.randomfilms.models.User;

/**
 * Created by adrian on 23/3/15.
 */
public interface UserGreetingsService {

    User getUserAndGreetings(Long userId);
    Greeting addGreetingToUser(Greeting greeting);
    Greeting updateGreetingFromUser(Greeting updateGreeting, Long greeting);
    void removeGreetingFromUser(Long greetingId);
}
