package com.udl.softarch.randomfilms.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.services.FilmsPersonInvolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Allu on 21/04/2015.
 */
@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    FilmsPersonInvolvedService filmsPersonInvolvedService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Film receive(@PathVariable("id")Long id){

        Preconditions.checkNotNull(filmsPersonInvolvedService.getFilmAndPersonInvolved(id), "Test user not found");
        return filmsPersonInvolvedService.getFilmAndPersonInvolved(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "text/html")
    public ModelAndView receiveHTML(@PathVariable("id")Long id){

        return new ModelAndView("film","film",receive(id));
    }
}
