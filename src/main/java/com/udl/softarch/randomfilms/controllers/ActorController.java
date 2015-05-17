package com.udl.softarch.randomfilms.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.randomfilms.Webservice.Webservice;
import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.repositories.ActorRepository;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.services.ActorService;
import com.udl.softarch.randomfilms.services.FilmsPersonInvolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;
import javax.xml.xquery.XQException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
@Controller
@RequestMapping("/films/")
public class ActorController {

    @Autowired
    ActorService actorService;
    @Autowired
    FilmsPersonInvolvedService filmsPersonInvolvedService;

    @RequestMapping(value = "/{id}/actors",method = RequestMethod.GET)
    @ResponseBody
    public List<Actor> receive(@PathVariable("id")Long id) throws XQException, IOException, JAXBException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Film film = filmsPersonInvolvedService.getFilmAndPersonInvolved(id);
        List<Actor> actors;
        if (film.getActors().isEmpty()){
            actors = Webservice.getInstance().getActorsByIMDBId(film.getIdIMDB());
            List<Actor> actorsWithId = actorService.saveActors(id,actors);
            filmsPersonInvolvedService.addActorsToFilm(id,actorsWithId);
        }else{
            actors = film.getActors();
        }
        Preconditions.checkNotNull(actors, "Actors not found");
        return actors;
    }

    @RequestMapping(value = "/{id}/actors",method = RequestMethod.GET,produces = "text/html")
    public ModelAndView receiveHTML(@PathVariable("id")Long id) throws XQException, IOException, JAXBException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView("actor");
        modelAndView.addObject("filmId", id+"");
        modelAndView.addObject("actors",receive(id));
        return modelAndView;
    }
}


