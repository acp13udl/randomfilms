package com.udl.softarch.randomfilms.services;


import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.models.Director;
import com.udl.softarch.randomfilms.models.Film;

import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
public interface FilmsPersonInvolvedService {

    Film getFilmAndPersonInvolved(Long filmId);
    Film addActorToFilm(Long filmId,Actor actor);
    Film addActorsToFilm(Long filmId,List<Actor> actors);
    Film addDirectorToFilm(Long filmId,Director director);
    Film addDirectorsToFilm(Long filmId,List<Director> actors);
}
