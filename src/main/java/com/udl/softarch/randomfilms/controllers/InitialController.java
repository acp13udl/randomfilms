package com.udl.softarch.randomfilms.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.utils.GenerateRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;
import javax.xml.xquery.XQException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lluís on 13/05/2015.
 */
@Controller
@RequestMapping(value = "/")
public class InitialController {

    @Autowired
    FilmRepository filmRepository;

    boolean first = true;//borrar

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public List<Film> listRandomFilm(){
        //////// BORRAR
        if(first){
            for(int i = 0; i <10; i++){
                filmRepository.save(createFilm());
            }
            first = false;
        }
        ///////////////
        if(filmRepository.count()< 11){
            return filmRepository.findAll();
        }
        List<Film> res = new ArrayList<>();
        List<Long> random = GenerateRandom.randomLongValues(filmRepository.count());
        for(Long value : random){
            res.add(filmRepository.getOne(value));
        }

        return res;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHTML(){
        return new ModelAndView("initialPage", "films", listRandomFilm());
    }

    //BORRAR
    private Film createFilm() {
        Film film = new Film("tt0096895", "Accion asesinato", "89", "Van dos y se cae en el de enmedio", "ffds", 8.5f, 1999, "120m", "POLE", "fafafa", "asfafaf", 1999, "Batman");
        return filmRepository.save(film);
    }
}
