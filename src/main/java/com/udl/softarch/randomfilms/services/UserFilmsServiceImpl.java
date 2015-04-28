package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.models.User;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.repositories.UserRepository;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

/**
 * Created by adrian on 23/3/15.
 */

@Service
public class UserFilmsServiceImpl implements UserFilmsService {

    final org.jboss.logging.Logger logger = LoggerFactory.logger(UserFilmsServiceImpl.class);



    @Autowired
    FilmRepository filmRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User getUserFilms(Long userId) {
        User user = userRepository.findOne(userId);
        logger.info("Cargado usuario " + user.getUsername() +
                "con " + user.getFavoritesList().size());
        return user;
    }

    @Transactional
    @Override
    public User addFilmToUser(Long userId, Film film){
        User user = userRepository.findOne(userId);
        user.addToFavorites(film);
        userRepository.save(user);
        return user;
    }

    @Transactional
    @Override
    public void removeFilmFromUser(Long userId,Long filmId) {
        User user = userRepository.findOne(userId);
        Film film = filmRepository.findOne(filmId);
        user.removeFilm(film);
        userRepository.save(user);
    }
}
