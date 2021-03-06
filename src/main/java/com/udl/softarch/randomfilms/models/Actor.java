package com.udl.softarch.randomfilms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @Lob
    @Column(length = 100000)
    @XmlElement private String bio;

    @XmlElement private String birthName;

    @XmlElement private String actorId;

    @XmlElement private String dateOfBirth;

    @XmlElement private String height;

    @XmlElement private String placeOfBirth;

    @XmlElement private String urlPhoto;

    @ManyToMany(mappedBy = "actors")
    private List<Film> films = new ArrayList<>();

    public  Actor(){}

    public Actor(String actorId,String actorName, String bio, String birthName, String dateOfBirth, String height, String placeOfBirth, String urlPhoto) {
        this.actorId = actorId;
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

    @JsonIgnore
    @JsonProperty(value = "films")
    public List<Film> getFilms() {
        return films;
    }

    public String getActorId() {
        return actorId;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actorName='" + actorName + '\'' +
                ", bio='" + bio + '\'' +
                ", birthName='" + birthName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", height='" + height + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", urlPhoto='" + urlPhoto + '\'' +
                ", films=" + films +
                '}';
    }
}
