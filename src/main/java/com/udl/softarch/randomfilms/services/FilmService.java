package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.models.User;

import java.util.List;

/**
 * Created by adrian on 28/4/15.
 */
public interface FilmService {

    Film saveFilm(Film film);
    void removeFilm(Film film);
    List<Film> getFilmsList();
}
