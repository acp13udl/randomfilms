package com.udl.softarch.randomfilms.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
@Entity
@XmlRootElement
public class Actor extends PersonInvolved{

    @XmlElement private String actorName;

    @XmlElement private String bio;

    @XmlElement private String birthName;

    @XmlElement private String dateOfBirth;

    @XmlElement private String height;

    @XmlElement private String placeOfBirth;

    @XmlElement private String urlPhoto;

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

    public String getBio() {
        return bio;
    }

    public String getBirthName() {
        return birthName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getHeight() {
        return height;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public List<Film> getFilms() {
        return films;
    }
}
