package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by adrian on 28/4/15.
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;


    @Override
    public Film saveFilm(Film film) {

        return filmRepository.save(film);
    }

    @Override
    public void removeFilm(Film film) {
        filmRepository.delete(film);
    }
}
