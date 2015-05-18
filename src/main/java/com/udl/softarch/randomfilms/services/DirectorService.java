package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.models.Director;

import java.util.List;

/**
 * Created by Allu on 18/05/2015.
 */
public interface DirectorService {
    List<Director> saveDirectors(Long filmId,List<Director> directors);
}
