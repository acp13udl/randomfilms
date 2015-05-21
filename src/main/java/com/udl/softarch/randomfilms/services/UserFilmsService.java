package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.models.User;


/**
 * Created by adrian on 23/3/15.
 */
public interface UserFilmsService {

    User getUserFilms(Long userId);
    User addFilmToUser(String name, Film film);
}