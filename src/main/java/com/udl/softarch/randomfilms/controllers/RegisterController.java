package com.udl.softarch.randomfilms.controllers;

import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.models.User;
import com.udl.softarch.randomfilms.repositories.UserRepository;
import com.udl.softarch.randomfilms.services.UserFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by adrian on 27/5/15.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFilmsService userfilmsRepository;

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView showRegisterPage(){

        return new ModelAndView("register");
    }

}
