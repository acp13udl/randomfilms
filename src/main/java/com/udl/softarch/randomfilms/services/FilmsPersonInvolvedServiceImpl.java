package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.models.Director;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.repositories.ActorRepository;
import com.udl.softarch.randomfilms.repositories.DirectorRepository;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allu on 21/04/2015.
 */
@Service
public class FilmsPersonInvolvedServiceImpl implements FilmsPersonInvolvedService {

    @Autowired
    ActorRepository actorRepository;
    @Autowired
    DirectorRepository directorRepository;
    @Autowired
    FilmRepository filmRepository;

    @Transactional(readOnly = true)
    @Override
    public Film getFilmAndPersonInvolved(Long filmId) {
        Film film = filmRepository.findOne(filmId);
        film.getActors().size();
        film.getDirectors().size();
        film.getReviews().size();
        return film;
    }

    @Transactional
    @Override
    public Film addActorToFilm(Long actorId,Long filmId) {
        Actor actor = actorRepository.findOne(actorId);
        Film film = filmRepository.findOne(actorId);
        film.addActor(actor);

        filmRepository.save(film);

        return film;
    }

    @Transactional
    @Override
    public Film addDirectorToFilm(Long directorId,Long filmId) {
        Director director = directorRepository.findOne(directorId);
        Film film = filmRepository.findOne(filmId);
        film.addDirector(director);

        filmRepository.save(film);

        return film;
    }
}
