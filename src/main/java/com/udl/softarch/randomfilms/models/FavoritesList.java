package com.udl.softarch.randomfilms.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ler2 on 20/04/15.
 */
@Entity
public class FavoritesList {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Film> films = new ArrayList<Film>();

    private int numberVisibleFilms;

    public FavoritesList(){}

    public void addFilm(Film film){
        films.add(film);
    }
    public void removeFilm(Film film){
        films.remove(film);
    }
    public Film getFilm(int position){
        return films.get(position);
    }

    public int getNumberVisibleFilms() {
        return numberVisibleFilms;
    }

    public void setNumberVisibleFilms(int numberVisibleFilms) {
        this.numberVisibleFilms = numberVisibleFilms;
    }
}
