package com.udl.softarch.randomfilms.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
@Entity
public class Actor extends PersonInvolved{

    private String actorName;

    private String bio;

    private String birthName;

    private String dateOfBirth;

    private String height;

    private String placeOfBirth;

    private String urlPhoto;

    @ManyToMany(mappedBy = "actors")
    private List<Film> films = new ArrayList<>();

    public  Actor(){}

    public Actor(String actorName, String bio, String birthName, String dateOfBirth, String height, String placeOfBirth, String urlPhoto) {
        this.actorName = actorName;
        this.bio = bio;
        this.birthName = birthName;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.placeOfBirth = placeOfBirth;
        this.urlPhoto = urlPhoto;
    }

    @Override
    public boolean isDirector() {
        return false;
    }

    @Override
    public boolean isActor() {
        return true;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBirthName() {
        return birthName;
    }

    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
