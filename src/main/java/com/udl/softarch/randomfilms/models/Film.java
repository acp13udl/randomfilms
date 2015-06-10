package com.udl.softarch.randomfilms.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Director> directors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Actor> actors = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @XmlElement private String idIMDB;

    @XmlElement private String genres;

    @XmlElement private String metascore;

    @XmlElement private String directorsIMDB;

    @Lob
    @Column(length = 100000)
    @XmlElement private String plot;

    @XmlElement private String rated;

    @XmlElement private float rating;

    @XmlElement private int releaseDate;

    @XmlElement private String runtime;

    @Lob
    @Column(length = 100000)
    @XmlElement private String simplePlot;

    @XmlElement private String urlIMDB;

    @XmlElement private String urlPoster;

    @XmlElement private int year;

    @XmlElement private String title;

    private String saveUrl;

    public Film(){}

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

    public String getGenres() {
        return genres;
    }

    public Film(String idIMDB, String genres, String metascore, String plot, String rated, float rating, int releaseDate,
                String runTime, String simplePlot, String urlIMDB, String urlPoster, int year, String title,String directorsIMDB){

        this.idIMDB = idIMDB;
        this.genres = genres;
        this.metascore = metascore;
        this.plot = plot;
        this.rated = rated;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.runtime = runTime;
        this.simplePlot = simplePlot;
        this.urlIMDB = urlIMDB;
        this.urlPoster = urlPoster;
        this.year = year;
        this.title = title;
        this.directorsIMDB = directorsIMDB;
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

    public String getReleaseDate() {
        String res = Integer.toString(this.releaseDate);
        try {
            return res.substring(0, 4) + " - " + res.substring(4, 6) + " - " + res.substring(6, res.length());
        }catch (Exception exception){
            return "";
        }
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
        return runtime;
    }

    public String getTitle() {
        return title;
    }

    public String getDirectorsIMDB() {
        return directorsIMDB;
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

    public void addReview(Review review){
        reviews.add(review);
    }

    public void createUrlParams() throws UnsupportedEncodingException{
        this.saveUrl = "?idIMDB=" + idIMDB +
                "&genres=" + genres +
                "&metascore=" + metascore+
                "&plot=" + URLEncoder.encode(plot, "UTF-8") +
                "&rated=" + rated +
                "&rating=" + rating +
                "&releaseDate=" + releaseDate +
                "&runTime=" + runtime+
                "&simplePlot=" + URLEncoder.encode(simplePlot, "UTF-8") +
                "&urlIMDB=" + urlIMDB +
                "&urlPoster=" +  urlPoster +
                "&year=" + year +
                "&title=" + title +
                "&directorsIMDB="+directorsIMDB;
    }


    public String getUrlParams() {

        return this.saveUrl;
    }

}
