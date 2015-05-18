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
public class Director extends PersonInvolved{

    @XmlElement
    private String directorName;

    @Lob
    @Column(length = 100000)
    @XmlElement private String bio;

    @XmlElement private String birthName;

    @XmlElement private String directorId;

    @XmlElement private String dateOfBirth;

    @XmlElement private String height;

    @XmlElement private String placeOfBirth;

    @XmlElement private String urlPhoto;

    @ManyToMany(mappedBy = "directors")
    private List<Film> films = new ArrayList<>();

    public Director(){}

    public Director(String directorId,String directorName, String bio, String birthName, String dateOfBirth, String height, String urlPhoto, String placeOfBirth) {
        this.directorId = directorId;
        this.directorName = directorName;
        this.bio = bio;
        this.birthName = birthName;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.urlPhoto = urlPhoto;
        this.placeOfBirth = placeOfBirth;
    }

    @Override
    public boolean isDirector() {
        return true;
    }

    @Override
    public boolean isActor() {
        return false;
    }

    public String getDirectorName() {
        return directorName;
    }

    public List<Film> getFilms() {
        return films;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getHeight() {
        return height;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBirthName() {
        return birthName;
    }

    public String getBio() {
        return bio;
    }

    public String getDirectorId() {
        return directorId;
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorName='" + directorName + '\'' +
                ", bio='" + bio + '\'' +
                ", birthName='" + birthName + '\'' +
                ", directorId='" + directorId + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", height='" + height + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", urlPhoto='" + urlPhoto + '\'' +
                ", films=" + films +
                '}';
    }
}
