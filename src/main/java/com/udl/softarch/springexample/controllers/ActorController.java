package com.udl.softarch.springexample.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.springexample.models.Actor;
import com.udl.softarch.springexample.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Allu on 21/04/2015.
 */
@Controller
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    ActorRepository actorsRepository;

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Actor> list(@RequestParam(required = false, defaultValue = "0") int page,
                                   @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest request = new PageRequest(page, size);
        return actorsRepository.findAll(request).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHtml(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "10") int size) {
        return new ModelAndView("actors", "actors", list(page, size));
    }

    // RETRIEVE
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Actor retrieve(@PathVariable("id") Long id) {
        Preconditions.checkNotNull(actorsRepository.findOne(id), "actor with id %s not found", id);
        return actorsRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable( "id" ) Long id) {
        return new ModelAndView("actor", "actor", retrieve(id));
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Actor create(@Valid @RequestBody Actor actor, HttpServletResponse response) {
        return actorsRepository.save(actor);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String createHtml(@Valid @ModelAttribute("actor") Actor actor, BindingResult binding, HttpServletResponse response) {
        if(binding.hasErrors()) {
            return "form";
        }
        return "redirect:/actors/" + create(actor, response).getId();
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
        Actor emptyActor = new Actor();
        return new ModelAndView("form", "actor", emptyActor);
    }

    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Actor update(@PathVariable("id") Long id, @Valid @RequestBody Actor actor) {
        Actor oldActor = actorsRepository.findOne(id);
        return actorsRepository.save(oldActor);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public String updateHTML(@PathVariable("id") Long id, @Valid @ModelAttribute("actor") Actor actor,
                             BindingResult binding) {
        if (binding.hasErrors()) {
            return "form";
        }
        return "redirect:/actors/" + update(id, actor).getId();
    }

    // Update form
    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        return new ModelAndView("form", "actor", actorsRepository.findOne(id));
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        actorsRepository.delete(actorsRepository.findOne(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHTML(@PathVariable("id") Long id) {
        delete(id);
        return "redirect:/actors";
    }
}
