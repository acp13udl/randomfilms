package com.udl.softarch.randomfilms.controllers;

import com.udl.softarch.randomfilms.Webservice.Webservice;
import com.udl.softarch.randomfilms.models.*;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.services.FilmService;
import com.udl.softarch.randomfilms.services.FilmsPersonInvolvedService;
import com.udl.softarch.randomfilms.services.UserFilmsService;
import com.udl.softarch.randomfilms.utils.GenerateRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    UserFilmsService userFilmsService;
    @Autowired
    FilmsPersonInvolvedService filmsPersonInvolvedService;
    @Autowired
    FilmService filmService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,List<Film>> listRandomFilm(Principal principal,@RequestParam Map<String, String> parameters, @RequestParam(value = "search",
            required = false) final String search){
        Map<String,List<Film>> map = new HashMap<>();
        List<Film> randomList = new ArrayList<>();
        List<Film> searchList = new ArrayList<>();

        if(filmRepository.count()< 11){
            randomList.addAll(filmService.getFilmsList());
        }else{
            List<Long> random = GenerateRandom.randomLongValues(filmRepository.count());
            for(Long value : random){
                randomList.add(filmsPersonInvolvedService.getFilmAndPersonInvolved(value));
            }
        }

        map.put("randomList",avoidLazy(randomList));

        if(search!= null){
            try {
                //TODO Hay que hacerlo en otro método compo el listRandomFilm?
                searchList.addAll(Webservice.getInstance().getFilmByTitle(search, 10));
                map.put("searchList",avoidLazy(searchList));
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

        return map;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHTML(Principal principal,@RequestParam Map<String, String> parameters, @RequestParam(value = "search",
            required = false) final String search){

        Map<String,List<Film>> map = listRandomFilm(principal,parameters,search);

        ModelAndView modelAndView = new ModelAndView("initialPage");
        modelAndView.addObject("films", map.get("randomList"));

        if (principal !=null && isAdminUser(principal))
            modelAndView.addObject("isAdminUser",true);

        if (map.containsKey("searchList")){
            modelAndView.addObject("searchFilms",map.get("searchList"));
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
                         @RequestParam(value = "idIMDB",required=true) String idIMDB,
                         @RequestParam(value = "directorsIMDB",required=true) String directorsIMDB) throws UnsupportedEncodingException {

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

        return URLDecoder.decode(decode, "UTF-8");
    }

    private boolean isAdminUser(Principal principal){

        User user = userFilmsService.getUserFilms(principal.getName());

        return user.getAuthorities().toString().equals(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN").toString());
    }

    private List<Film> avoidLazy(List<Film> list){
        List<Film> listWithoutLazy = new ArrayList<>();
        for (Film f: list){
            f.setActors(new ArrayList<Actor>());
            f.setReviews(new ArrayList<Review>());
            f.setDirectors(new ArrayList<Director>());
            listWithoutLazy.add(f);
        }
        return listWithoutLazy;
    }

}
