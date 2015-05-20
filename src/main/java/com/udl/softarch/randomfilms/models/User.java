package com.udl.softarch.randomfilms.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allu on 23/03/2015.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    private String password;

    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Film> favoritesList = new ArrayList<Film>();


    public User(){}

    public User(String username, String password){
        this.username=username;
        this.password =password;
    }


    public Long getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Film> getFavoritesList() {
        return favoritesList;
    }

    public void addToFavorites(Film film){
       favoritesList.add(film);
    }

    public void removeFilm(Film film){
        favoritesList.remove(film);
    }
}

