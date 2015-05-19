package com.udl.softarch.randomfilms.controllers;

import com.sun.xml.internal.fastinfoset.Encoder;
import com.udl.softarch.randomfilms.Webservice.Webservice;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.utils.GenerateRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;
import javax.xml.xquery.XQException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Lluís on 13/05/2015.
 */
@Controller
@RequestMapping(value = "/")
public class InitialController {

    private static Logger logger = Logger.getLogger(InitialController.class.getName());
    //TODO Change repository to service
    @Autowired
    FilmRepository filmRepository;


    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public List<Film> listRandomFilm(){
        if(filmRepository.count()< 11){
            return filmRepository.findAll();
        }
        List<Film> res = new ArrayList<>();
        List<Long> random = GenerateRandom.randomLongValues(filmRepository.count());
        for(Long value : random){
            res.add(filmRepository.findOne(value));
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



    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String saveFilm(@RequestParam(value = "title",required=true) String title,@RequestParam(value = "year",required=true) String year,
                         @RequestParam(value = "urlPoster",required=true) String urlPoster,@RequestParam(value = "metascore",required=true) String metascore,
                         @RequestParam(value = "plot",required=true) String plot,@RequestParam(value = "rated",required=true) String rated,
                         @RequestParam(value = "rating",required=true) String rating,@RequestParam(value = "releaseDate",required=true) String releaseDate,
                         @RequestParam(value = "runTime",required=true) String runTime,@RequestParam(value = "simplePlot",required=true) String simplePlot,
                         @RequestParam(value = "genres",required=true) String genres,@RequestParam(value = "urlIMDB",required=true) String urlIMDB,
                         @RequestParam(value = "idIMDB",required=true) String idIMDB,@RequestParam(value = "directorsIMDB",required=true) String directorsIMDB) throws UnsupportedEncodingException {

        Film film = new Film(idIMDB,genres,metascore,decode(plot),rated,
                Float.parseFloat(rating),Integer.parseInt(releaseDate),runTime,decode(simplePlot),
                urlIMDB,urlPoster,Integer.parseInt(year),title,directorsIMDB);

        Film filmWithId = filmRepository.findFimlByIdIMDB(idIMDB);

        if (notExistFilm(filmWithId)){

            filmWithId = filmRepository.save(film);
        }

        return "redirect:/films/"+filmWithId.getId();

    }

    private boolean notExistFilm(Film film){
        return film == null;
    }

    private String decode(String decode) throws UnsupportedEncodingException {

        return URLDecoder.decode(decode, Encoder.UTF_8);
    }


}
