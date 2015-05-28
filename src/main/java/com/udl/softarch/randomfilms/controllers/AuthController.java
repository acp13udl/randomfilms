package com.udl.softarch.randomfilms.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.models.User;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.repositories.UserRepository;
import com.udl.softarch.randomfilms.services.FilmsPersonInvolvedService;
import com.udl.softarch.randomfilms.services.UserFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Allu on 19/05/2015.
 */
@Controller
@RequestMapping("/validation")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFilmsService userfilmsRepository;

    @Autowired
    FilmsPersonInvolvedService filmRepository;

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public String authFavorites(Principal principal){

        User user = userRepository.findUserByUsername(principal.getName());
        if(user==null){
            return "redirect:/";
        }
        return "redirect:/users/"+user.getUsername()+"/favorites";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET, produces = "text/html")
    public String AuthAddToFavorites(Principal principal,@RequestParam(value = "filmId",
            required = true) final Long filmId){

        Film film = filmRepository.getFilmAndPersonInvolved(filmId);
        User user = null;
        if(film!=null)
           user = userfilmsRepository.addFilmToUser(principal.getName(),film);
        if (user == null)
            return "redirect:/";
        return "redirect:/users/"+user.getUsername()+"/favorites";
    }

}
