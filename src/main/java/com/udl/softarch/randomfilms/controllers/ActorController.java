package com.udl.softarch.randomfilms.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.randomfilms.Webservice.Webservice;
import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.repositories.ActorRepository;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.services.ActorService;
import com.udl.softarch.randomfilms.services.FilmService;
import com.udl.softarch.randomfilms.services.FilmsPersonInvolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    FilmsPersonInvolvedService filmsPersonInvolvedService;

    @Autowired
    ActorService actorService;

    @Autowired
    FilmRepository filmRepository;

    @RequestMapping(value = "/{id}/actors",method = RequestMethod.GET)
    @ResponseBody
    public List<Actor> receive(@PathVariable("id")Long id) throws XQException, IOException, JAXBException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Actor> actor = Webservice.getInstance().getActorsByIMDBId(filmRepository.findOne(id).getIdIMDB());
        Preconditions.checkNotNull(actor, "Film not found");
        return actor;
    }

    @RequestMapping(value = "/{id}/actors",method = RequestMethod.GET,produces = "text/html")
    public ModelAndView receiveHTML(@PathVariable("id")Long id) throws XQException, IOException, JAXBException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView("actor");
        modelAndView.addObject("filmId", id+"");
        modelAndView.addObject("actors",receive(id));
        return modelAndView;
    }
}


