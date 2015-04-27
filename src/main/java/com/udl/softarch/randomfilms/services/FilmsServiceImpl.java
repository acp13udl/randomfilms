package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.models.Director;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.repositories.ActorRepository;
import com.udl.softarch.randomfilms.repositories.DirectorRepository;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
public class FilmsServiceImpl implements FilmsService {

    @Autowired
    ActorRepository actorRepository;
    @Autowired
    DirectorRepository directorRepository;
    @Autowired
    FilmRepository filmRepository;

    @Transactional(readOnly = true)
    @Override
    public Film getFilmAndPersonInvolved(Long filmId) {
        Film f = filmRepository.findOne(filmId);
        return f;
    }

    @Transactional
    @Override
    public List<Actor> addActorToFilm(Actor actor) {

        return null;
    }

    @Transactional
    @Override
    public List<Director> addDirectorToFilm(Director director) {
        return null;
    }
}
