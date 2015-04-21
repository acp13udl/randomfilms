package com.udl.softarch.springexample.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ler2 on 20/04/15.
 */
@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Director> directors = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Actor> actors = new ArrayList<>();

    private String idIMDB;

    private String[] genres;

    private String metascore;

    private String plot;

    private String rated;

    private float rating;

    private int releaseDate;

    private String runTime;

    private String simplePlot;

    private String urlIMDB;

    private String urlPoster;

    private int year;

    private String tittle;

    public Long getId() {
        return Id;
    }

    public String getIdIMDB() {
        return idIMDB;
    }

    public void setIdIMDB(String idIMDB) {
        this.idIMDB = idIMDB;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getSimplePlot() {
        return simplePlot;
    }

    public void setSimplePlot(String simplePlot) {
        this.simplePlot = simplePlot;
    }

    public String getUrlIMDB() {
        return urlIMDB;
    }

    public void setUrlIMDB(String urlIMDB) {
        this.urlIMDB = urlIMDB;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void addDirectors(Collection<? extends Director> collection){
        directors.addAll(collection);
    }

    public List<Director> getDirectors(){
        return directors;
    }

    public void addActors(Collection<? extends Actor> collection){
        actors.addAll(collection);
    }

    public List<Actor> getActors(){
        return actors;
    }
}
