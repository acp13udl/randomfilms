package com.udl.softarch.randomfilms.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Allu on 20/04/2015.
 */
public class RandomFavorites {

    private List<Film> randomFilms= new ArrayList<>();


    public RandomFavorites() {
    }

    public RandomFavorites(List<Film> randomFilms) {

        randomFilms.addAll(randomFilms);
    }

    public List<Film> getRecommendedList() {
        return randomFilms;
    }
}