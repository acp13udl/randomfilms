package com.udl.softarch.randomfilms.controllers;

import com.google.common.base.Preconditions;
import com.udl.softarch.randomfilms.Webservice.Webservice;
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
import java.util.Map;

/**
 * Created by Lluís on 13/05/2015.
 */
@Controller
@RequestMapping(value = "/")
public class InitialController {

    //TODO Change repository to service
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
    public ModelAndView listHTML(@RequestParam Map<String, String> parameters, @RequestParam(value = "search",
            required = false) final String search){
        ModelAndView modelAndView = new ModelAndView("initialPage");
        modelAndView.addObject("films",listRandomFilm());

        if(search!= null){
            try {
                //TODO Hay que hacerlo en otro método compo el listRandomFilm?
                List <Film> searchedFilms = Webservice.getInstance().getFilmByTitle(search, 10);
//                for(Film f : searchedFilms){
//                    Film film = filmRepository.findFimlByIdIMDB(f.getIdIMDB());
//                    if(film == null){
//                        filmRepository.save(f);
//                        System.out.println("A::: "+f.toString());
//                    }else {
//                        f = filmRepository.findFimlByIdIMDB(f.getIdIMDB());
//                        System.out.println("B::: "+f.toString());
//                    }
//                }
                modelAndView.addObject("searchFilms",searchedFilms);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (XQException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }

        return modelAndView;
    }

    //BORRAR
    private Film createFilm() {
        Film film = new Film("tt0096895", "Accion asesinato", "89", "Van dos y se cae en el de enmedio", "ffds", 8.5f, 1999, "120m", "POLE", "fafafa", "asfafaf", 1999, "Batman");
        return filmRepository.save(film);
    }
}
