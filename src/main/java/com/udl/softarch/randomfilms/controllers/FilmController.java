package com.udl.softarch.randomfilms.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.models.User;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.services.FilmService;
import com.udl.softarch.randomfilms.services.FilmsPersonInvolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    FilmsPersonInvolvedService filmsPersonInvolvedService;
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    FilmService filmService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Film receive(@PathVariable("id")Long id){
        Film film = filmsPersonInvolvedService.getFilmAndPersonInvolved(id);
        Preconditions.checkNotNull(film, "Film not found");
        return film;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "text/html")
    public ModelAndView receiveHTML(@PathVariable("id")Long id){
        createFilm();
        return new ModelAndView("film","film",receive(id));
    }

    private Film createFilm(){
        Film film = new Film("tt0096895","Accion asesinato","89","Van dos y se cae en el de enmedio","ffds",8.5f,1999,"120m","POLE","fafafa","asfafaf",1999,"Batman");
        return filmRepository.save(film);
    }
}
