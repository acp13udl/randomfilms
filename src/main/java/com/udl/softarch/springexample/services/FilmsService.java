package com.udl.softarch.springexample.services;


import com.udl.softarch.springexample.models.Actor;
import com.udl.softarch.springexample.models.Director;
import com.udl.softarch.springexample.models.Film;

import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
public interface FilmsService {

    Film getFilmAndPersonInvolved(Long filmId);
    List<Actor> addActorToFilm(Actor actor);
    List<Director> addDirectorToFilm(Director director);
}
