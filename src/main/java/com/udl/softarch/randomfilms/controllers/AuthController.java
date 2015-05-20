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
    public User validateUser(String username,String password){
        User user = userRepository.findUserByUsername(username);
        if(user==null){
            user = new User(username,password);
        }else if (!user.getPassword().equals(password)){
            return null;
        }
        return user;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public String listFavorites(){
        String username = "Allu";
        String password = "alluesan";
        User user = validateUser(username,password);
        if(user==null){
            return "redirect:/";
        }
        return "redirect:/users/"+user.getId()+"/favorites";
    }

}
