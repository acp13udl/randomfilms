package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adrian on 28/4/15.
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;


    @Transactional
    @Override
    public Film saveFilm(Film film) {

        return filmRepository.save(film);
    }

    @Transactional
    @Override
    public void removeFilm(Film film) {

        filmRepository.delete(film);
    }


    @Transactional
    @Override
    public List<Film> getFilmsList(){ // initialization lazy < 11 films

        List<Film> filmsList = filmRepository.findAll();

        for (Film f:filmsList){
            f.getActors().size();
            f.getDirectors().size();
            f.getReviews().size();
        }

        return filmsList;
    }
}
