package com.udl.softarch.springexample.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.springexample.models.Director;
import com.udl.softarch.springexample.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by Allu on 21/04/2015.
 */
@Controller
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    DirectorRepository directorsRepository;

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Director> list(@RequestParam(required = false, defaultValue = "0") int page,
                                @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest request = new PageRequest(page, size);
        return directorsRepository.findAll(request).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHtml(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "10") int size) {
        return new ModelAndView("directors", "directors", list(page, size));
    }

    // RETRIEVE
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Director retrieve(@PathVariable("id") Long id) {
        Preconditions.checkNotNull(directorsRepository.findOne(id), "director with id %s not found", id);
        return directorsRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable( "id" ) Long id) {
        return new ModelAndView("director", "director", retrieve(id));
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Director create(@Valid @RequestBody Director director, HttpServletResponse response) {
        return directorsRepository.save(director);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String createHtml(@Valid @ModelAttribute("director") Director director, BindingResult binding, HttpServletResponse response) {
        if(binding.hasErrors()) {
            return "form";
        }
        return "redirect:/directors/" + create(director, response).getId();
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
        Director emptydirector = new Director();
        return new ModelAndView("form", "director", emptydirector);
    }

    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Director update(@PathVariable("id") Long id, @Valid @RequestBody Director director) {
        Director oldDirector = directorsRepository.findOne(id);
        return directorsRepository.save(oldDirector);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public String updateHTML(@PathVariable("id") Long id, @Valid @ModelAttribute("director") Director director,
                             BindingResult binding) {
        if (binding.hasErrors()) {
            return "form";
        }
        return "redirect:/directors/" + update(id, director).getId();
    }

    // Update form
    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        return new ModelAndView("form", "director", directorsRepository.findOne(id));
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        directorsRepository.delete(directorsRepository.findOne(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHTML(@PathVariable("id") Long id) {
        delete(id);
        return "redirect:/directors";
    }
}
