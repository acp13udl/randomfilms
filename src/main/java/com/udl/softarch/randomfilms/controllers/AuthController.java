package com.udl.softarch.randomfilms.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.randomfilms.models.User;
import com.udl.softarch.randomfilms.repositories.UserRepository;
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

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public User validateUser(Principal principal){
        User user = userRepository.findUserByUsername(principal.getName());
        return user;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public String listFavorites(Principal principal){

        User user = validateUser(principal);
        if(user==null){
            return "redirect:/";
        }
        return "redirect:/users/"+user.getId()+"/favorites";
    }

}
