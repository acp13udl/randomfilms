package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.User;
import com.udl.softarch.randomfilms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Allu on 21/05/2015.
 */
public class RepositoryUserDetailsService implements UserDetailsService{
    private UserRepository repository;

    @Autowired
    public RepositoryUserDetailsService(UserRepository repository) {
        this.repository = repository;
        User u = new User("admin", "admin");
        u.getFavoritesList().size();
        this.repository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("No user found with username: " + username);
        return user;
    }
}

