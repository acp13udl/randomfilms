package com.udl.softarch.randomfilms.models;

import javax.persistence.Entity;

/**
 * Created by adrian on 31/5/15.
 */
@Entity //Fake entity
public class UserPostWrapper {
    @javax.persistence.Id
    private String username;
    private String password;

    public UserPostWrapper() {
    }

    public UserPostWrapper(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
