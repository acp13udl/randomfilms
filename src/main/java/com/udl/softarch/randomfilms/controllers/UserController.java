package com.udl.softarch.randomfilms.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.randomfilms.Webservice.Webservice;
import com.udl.softarch.randomfilms.models.Actor;

import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.models.User;
import com.udl.softarch.randomfilms.models.UserPostWrapper;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.repositories.UserRepository;
import com.udl.softarch.randomfilms.services.UserFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import javax.xml.xquery.XQException;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * Created by adrian on 23/3/15.
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserFilmsService userFilmsService;
    @Autowired
    FilmRepository filmRepository;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> list(@RequestParam(required = false, defaultValue = "0") int page,
                               @RequestParam(required = false, defaultValue = "10") int size){
        PageRequest pageRequest = new PageRequest(page, size);
        return userRepository.findAll(pageRequest).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
     public ModelAndView listHTML(@RequestParam(required = false, defaultValue = "0") int page,
                                  @RequestParam(required = false, defaultValue = "10") int size){
        return new ModelAndView("users", "users", list(page, size));
    }

    @RequestMapping(value = "/{id}/favorites",method = RequestMethod.GET)
    @ResponseBody
    public User receiveFavorites(@PathVariable("id")String id){

        Preconditions.checkNotNull(userRepository.findUserByUsername(id), "User not exist");
        User user = userFilmsService.getUserFilms(id);
        return user;
    }

    @RequestMapping(value = "/{id}/favorites",method = RequestMethod.GET,produces = "text/html")
    public ModelAndView receiveFavoritesHTML(@PathVariable("id")String id){
        return new ModelAndView("favorites","user",receiveFavorites(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User receiveProfile(@PathVariable("id")String id){
        User user = userRepository.findUserByUsername(id);
        Preconditions.checkNotNull(user, "Test user not found");
        return user;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "text/html")
    public ModelAndView receiveProfileHTML(@PathVariable("id")String id){

        return new ModelAndView("user","user",receiveProfile(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User create(@Valid @ModelAttribute UserPostWrapper user, HttpServletResponse response) {
        return userRepository.save(new User(user.getUsername(),user.getPassword()));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String createHtml(@Valid @ModelAttribute UserPostWrapper user, BindingResult binding, HttpServletResponse response) {
        if(binding.hasErrors()) {
            return "form";
        }
        create(user, response);
        return "redirect:/users/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User update(@PathVariable("id") Long id, @Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public String updateHTML(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user,
                             BindingResult binding) {
        if (binding.hasErrors()) {
            return "form";
        }
        return "redirect:/user/" + update(id,user).getUsername();
    }

    @RequestMapping(value = "/session",method = RequestMethod.GET)
    @ResponseBody
    public User getSessionUser(Principal principal){
        if (principal!= null)
            return  userRepository.findUserByUsername(principal.getName());
        else
           return new User();
    }


}
