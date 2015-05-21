package com.udl.softarch.randomfilms.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.models.Review;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.services.FilmService;
import com.udl.softarch.randomfilms.services.FilmsPersonInvolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Allu on 21/04/2015.
 */
@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    FilmsPersonInvolvedService filmsPersonInvolvedService;

    @Autowired
    FilmService filmService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Film receive(@PathVariable("id")Long id){
        Film film = filmsPersonInvolvedService.getFilmAndPersonInvolved(id);
        System.out.println(film.toString());
        Preconditions.checkNotNull(film, "Film not found");
        return film;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "text/html")
    public ModelAndView receiveHTML(Principal principal,@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("film");
        modelAndView.addObject("film",receive(id));
        if (principal != null){
            modelAndView.addObject("username",principal.getName());
        }
        return modelAndView;

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Long createReview(@PathVariable("id") Long id,@Valid @RequestBody Review review){

        filmsPersonInvolvedService.addReviewToFilm(id,review);
        return id;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String createHtml(@PathVariable("id") Long id,@Valid @ModelAttribute("review") Review review, BindingResult binding) {
        if(binding.hasErrors()) {
            return "form";
        }
        return "redirect:/films/"+createReview(id, review);
    }
}
