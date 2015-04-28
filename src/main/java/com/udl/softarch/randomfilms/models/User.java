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
    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Film> favoritesList = new ArrayList<Film>();


    public User(){}

    public User(String username, String email){
        this.username=username;
        this.email=email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

