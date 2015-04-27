package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Greeting;
import com.udl.softarch.randomfilms.models.User;
import com.udl.softarch.randomfilms.repositories.GreetingRepository;
import com.udl.softarch.randomfilms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by adrian on 23/3/15.
 */
public class UserGreetingsServiceImpl implements UserGreetingsService {

    @Autowired
    GreetingRepository greetingRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User getUserAndGreetings(Long userId) {
        User u = userRepository.findOne(userId);
        return u;
    }

    @Transactional
    @Override
    public Greeting addGreetingToUser(Greeting greeting) {
        User u = userRepository.findUserByEmail(greeting.getEmail());
        if (u==null){
            String email = greeting.getEmail();
            String username = email.substring(0,email.indexOf('0'));
            u = new User(username,email);
        }
        greetingRepository.save(greeting);
        u.addGreeting(greeting);
        userRepository.save(u);
        return greeting;
    }

    @Transactional
    @Override
    public Greeting updateGreetingFromUser(Greeting updateGreeting, Long greeting) {
        Greeting oldGreeting = greetingRepository.findOne(greeting);
        oldGreeting.setContent(updateGreeting.getContent());
        oldGreeting.setDate(updateGreeting.getDate());
        if (!updateGreeting.getEmail().equals(oldGreeting.getEmail()))
            throw  new RuntimeException("Greeting email connot be updated");

        return greetingRepository.save(oldGreeting);
    }


    @Transactional
    @Override
    public void removeGreetingFromUser(Long greetingId) {

        Greeting g = greetingRepository.findOne(greetingId);
        User u = userRepository.findUserByEmail(g.getEmail());
        if (u==null){
            u.removeGreeting(g);
            userRepository.save(u);
        }

        greetingRepository.delete(g);
    }
}
