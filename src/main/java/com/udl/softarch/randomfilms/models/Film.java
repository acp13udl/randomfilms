package com.udl.softarch.randomfilms.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ler2 on 20/04/15.
 */
@Entity
@XmlRootElement
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Director> directors = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Actor> actors = new ArrayList<>();

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    @XmlElement private String idIMDB;

    @XmlElement private String genres;

    @XmlElement private String metascore;

    @XmlElement private String plot;

    @XmlElement private String rated;

    @XmlElement private float rating;

    @XmlElement private int releaseDate;

    @XmlElement private String runTime;

    @XmlElement private String simplePlot;

    @XmlElement private String urlIMDB;

    @XmlElement private String urlPoster;

    @XmlElement private int year;

    @XmlElement private String title;

    public  Film(){}

    public Film(String title) {
        this.title = title;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getIdIMDB() {
        return idIMDB;
    }


    public String getMetascore() {
        return metascore;
    }

    public String getPlot() {
        return plot;
    }

    public float getRating() {
        return rating;
    }

    public String getRated() {
        return rated;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public String getSimplePlot() {
        return simplePlot;
    }

    public String getUrlIMDB() {
        return urlIMDB;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public int getYear() {
        return year;
    }

    public String getRunTime() {
        return runTime;
    }

    public String getTitle() {
        return title;
    }

    public void addActor(Actor actor){
        actors.add(actor);
    }
    public void addDirector(Director director){
        directors.add(director);
    }

    public Long getId() {
        return Id;
    }
}