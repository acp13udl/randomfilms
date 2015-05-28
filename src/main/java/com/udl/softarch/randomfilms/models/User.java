package com.udl.softarch.randomfilms.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Allu on 23/03/2015.
 */
@Entity
public class User implements UserDetails{

    @NotBlank(message = "Username cannot be blank")
    @Id
    private String username;

    @NotBlank(message = "Email cannot be blank")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Film> favoritesList = new ArrayList<Film>();


    public User(){}

    public User(String username, String password){
        this.username=username;
        this.password =password;
    }



    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
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

