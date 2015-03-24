package com.udl.softarch.springexample.controllers;

import com.udl.softarch.springexample.models.Greeting;
import com.udl.softarch.springexample.repositories.GreetingRepository;
import com.udl.softarch.springexample.utils.GenerateRandom;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Preconditions;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by davidkaste on 16/02/15.
 */

@Controller
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    GreetingRepository greetingRepository;

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Greeting> list(@RequestParam(required = false, defaultValue = "0") int page,
                                   @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest request = new PageRequest(page, size);
        return greetingRepository.findAll(request).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHtml(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "10") int size) {
        return new ModelAndView("greetings", "greetings", list(page, size));
    }

    // RETRIEVE
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Greeting retrieve(@PathVariable("id") Long id) {
        Preconditions.checkNotNull(greetingRepository.findOne(id), "Greeting with id %s not found", id);
        return greetingRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable( "id" ) Long id) {
        return new ModelAndView("greeting", "greeting", retrieve(id));
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Greeting create(@Valid @RequestBody Greeting greeting, HttpServletResponse response) {
        return greetingRepository.save(greeting);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String createHtml(@Valid @ModelAttribute("greeting") Greeting greeting, BindingResult binding, HttpServletResponse response) {
        if(binding.hasErrors()) {
            return "form";
        }
        return "redirect:/greetings/" + create(greeting, response).getId();
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
        Greeting emptyGreeting = new Greeting();
        emptyGreeting.setDate(new Date());
        return new ModelAndView("form", "greeting", emptyGreeting);
    }

    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Greeting update(@PathVariable("id") Long id, @Valid @RequestBody Greeting greeting) {
        Greeting oldGreeting = greetingRepository.findOne(id);
        oldGreeting.setContent(greeting.getContent());
        oldGreeting.setDate(greeting.getDate());
        return greetingRepository.save(oldGreeting);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public String updateHTML(@PathVariable("id") Long id, @Valid @ModelAttribute("greeting") Greeting greeting,
                             BindingResult binding) {
        if (binding.hasErrors()) {
            return "form";
        }
        return "redirect:/greetings/" + update(id, greeting).getId();
    }

    // Update form
    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        return new ModelAndView("form", "greeting", greetingRepository.findOne(id));
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        greetingRepository.delete(greetingRepository.findOne(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHTML(@PathVariable("id") Long id) {
        delete(id);
        return "redirect:/greetings";
    }

    //Random
    @RequestMapping(value = "/random", method = RequestMethod.GET)
    @ResponseBody
    public Greeting retrieveRandomGreeting() {
        Greeting greeting =GenerateRandom.RandomGreeting(greetingRepository.findAll());
        Preconditions.checkNotNull(greeting, "Greeting with id %s not found", greeting.getId());
        return greeting;
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveRandomGreetingHTML() {
        return new ModelAndView("greeting", "greeting", retrieveRandomGreeting());
    }




}
