package com.udl.softarch.randomfilms.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.udl.softarch.randomfilms.Webservice.Webservice;
import com.udl.softarch.randomfilms.models.Director;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.services.DirectorService;
import com.udl.softarch.randomfilms.services.FilmService;
import com.udl.softarch.randomfilms.services.FilmsPersonInvolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;
import javax.xml.xquery.XQException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
@Controller
@RequestMapping("/films")
public class DirectorController {

    @Autowired
    DirectorService directorService;
    @Autowired
    FilmsPersonInvolvedService filmsPersonInvolvedService;
    @Autowired
    FilmService filmService;

    @RequestMapping(value = "/{id}/directors",method = RequestMethod.GET)
    @ResponseBody
    public List<Director> receive(@PathVariable("id")Long id) throws XQException, IOException, JAXBException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Film film = filmsPersonInvolvedService.getFilmAndPersonInvolved(id);
        List<Director> directors = new ArrayList<>();
        if (film.getDirectors().isEmpty()){
            List<String> directorsIMDB = Arrays.asList(film.getDirectorsIMDB().split(","));
            for(String imdb:directorsIMDB) {
                Director director;
                director = Webservice.getInstance().getDirectorByIMDBId(imdb);
                directors.add(director);
            }
            List<Director> directorsWithId = directorService.saveDirectors(id, directors);
            filmsPersonInvolvedService.addDirectorsToFilm(id, directorsWithId);
        }else{
            directors = film.getDirectors();
        }
        Preconditions.checkNotNull(directors, "Directors not found");
        return directors;
    }

    @RequestMapping(value = "/{id}/directors",method = RequestMethod.GET,produces = "text/html")
    public ModelAndView receiveHTML(@PathVariable("id")Long id) throws XQException, IOException, JAXBException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView("director");
        modelAndView.addObject("filmId", id+"");
        modelAndView.addObject("directors", receive(id));
        return modelAndView;
    }

}
