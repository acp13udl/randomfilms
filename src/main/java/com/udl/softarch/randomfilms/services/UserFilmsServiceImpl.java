package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.models.User;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.repositories.UserRepository;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by adrian on 23/3/15.
 */

@Service
public class UserFilmsServiceImpl implements UserFilmsService {


    @Autowired
    FilmRepository filmRepository;
    @Autowired
    UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public User getUserFilms(String id) {
        User user = userRepository.findUserByUsername(id);
        user.initializeLazyFavoritList();
        return user;
    }

    @Transactional
    @Override
    public User addFilmToUser(String username, Film film) {
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            user.getFavoritesList().size();
            if (!existFilm(user.getFavoritesList(),film)){
                user.addToFavorites(film);
            }
            userRepository.save(user);
        }
        return user;
    }

    private boolean existFilm(List<Film> films, Film film) {

        for (Film f : films) {
            if (f.getId().longValue() == film.getId().longValue()) {
                return true;
            }
        }
        return false;
    }
}
