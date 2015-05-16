package com.udl.softarch.randomfilms.Webservice;

import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.models.Director;
import com.udl.softarch.randomfilms.models.Film;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.xquery.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by adrian on 4/5/15.
 */
public class Webservice {

    private static Webservice INSTANCE;

    private static Logger logger = Logger.getLogger(Webservice.class.getName());
    private final String URL_BASE = "http://myapifilms.com/";
    private JAXBContext context;
    private Unmarshaller unmarshaller;
    private XQConnection xqConnection;
    private XQPreparedExpression preparedExpression;

    String genresQuery = "string-join($m//genres/genre, \", \")";

    String directorsIMDBQuery = "string-join($m//directors/director/nameId, \", \")";

    String filmQuery = "declare variable $doc external;\n"
            +"for $m in $doc//movies/movie\n"
            +"return\n"
            +"<film>\n"
            +"<title>{$m/title/text()}</title>\n"
            +"<idIMBD>{$m/idIMDB/text()}</idIMBD>\n"
            +"<metascore>{$m/metascore/text()}</metascore>\n"
            +"<plot>{$m/plot/text()}</plot>\n"
            +"<rated>{$m/rated/text()}</rated>\n"
            +"<rating>{$m/rating/text()}</rating>\n"
            +"<releaseDate>{$m/releaseDate/text()}</releaseDate>\n"
            +"<runTime>{$m/runTime/text()}</runTime>\n"
            +"<simplePlot>{$m/simplePlot/text()}</simplePlot>\n"
            +"<urlIMDB>{$m/urlIMBD/text()}</urlIMDB>\n"
            +"<urlPoster>{$m/title/text()}</urlPoster>\n"
            +"<year>{$m/year/text()}</year>\n"
            +"<genres>{"+genresQuery+"}</genres>"
            //+"<directorsIMDB>{"+directorsIMDBQuery+"}</directorsIMDB>"
            +"</film>";


    String actorQuery = "declare variable $doc external;\n"
                        +"for $a in $doc//movie/actors/actor\n"
                        +"return\n"
                        +"<actor>\n"
                        +"<bio>{$a/biography/bio/text()}</bio>\n"
                        +"<actorName>{$a/actorName/text()}</actorName>\n"
                        +"<birthName>{$a/biography/birthName/text()}</birthName>\n"
                        +"<dateOfBirth>{$a/biography/dateOfBirth/text()}</dateOfBirth>\n"
                        +"<height>{$a/biography/height/text()}</height>\n"
                        +"<placeOfBirth>{$a/biography/placeOfBirth/text()}</placeOfBirth>\n"
                        +"<urlPhoto>{$a/biography/urlPhoto/text()}</urlPhoto>\n"
                        +"</actor>";

    String directorQuery = "declare variable $doc external;\n"
            +"for $d in $doc//biography\n"
            +"return\n"
            +"<director>\n"
            +"<bio>{$d/bio/text()}</bio>\n"
            +"<directorName>{$d/name/text()}</directorName>\n"
            +"<birthName>{$d/birthName/text()}</birthName>\n"
            +"<dateOfBirth>{$d/dateOfBirth/text()}</dateOfBirth>\n"
            +"<height>{$d/height/text()}</height>\n"
            +"<placeOfBirth>{$d/placeOfBirth/text()}</placeOfBirth>\n"
            +"<urlPhoto>{$d/urlPhoto/text()}</urlPhoto>\n"
            +"</director>";


    private Webservice(){}

    public static Webservice getInstance(){

        if (INSTANCE == null){
            INSTANCE = new Webservice();
        }

        return INSTANCE;
    }

    public List<Film> getFilmByTitle(String title, int limit) throws IOException, ClassNotFoundException,InstantiationException
                                                        ,IllegalAccessException,XQException,JAXBException {

        final String url  = URL_BASE+"imdb?format=XML&title="+title+"&filter=M&limit="+limit;
        connectionToAPI(url,Film.class);

        return recoveryFilms();
    }

    private List<Film> recoveryFilms(){
        List<Film> films = new ArrayList<>();

        try {
            XQResultSequence xqResultSequence = preparedExpression.executeQuery();
            while (xqResultSequence.next()){
                XQItem  item = xqResultSequence.getItem();
                Film film = (Film) unmarshaller.unmarshal(item.getNode());
                //System.out.println("::: "+film.toString());
                films.add(film);
            }
        } catch (XQException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        try {
            preparedExpression.close();
            xqConnection.close();
        } catch (XQException e) {
            e.printStackTrace();
        }

        return films;

    }


    private List<Actor> recoveryActors(){
        List<Actor> actors = new ArrayList<>();

        try {
            XQResultSequence xqResultSequence = preparedExpression.executeQuery();
            while (xqResultSequence.next()){
                XQItem  item = xqResultSequence.getItem();
                Actor actor = (Actor) unmarshaller.unmarshal(item.getNode());
                actors.add(actor);
            }
        } catch (XQException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        try {
            preparedExpression.close();
            xqConnection.close();
        } catch (XQException e) {
            e.printStackTrace();
        }

        return actors;

    }

    public List<Actor> getActorsByIMDBId(String IMDBId) throws XQException, IOException, IllegalAccessException, JAXBException, InstantiationException, ClassNotFoundException {

        final String url  = URL_BASE+"imdb?idIMDB="+IMDBId+"&format=XML&actors=S&biography=1&bornDied=1";
        connectionToAPI(url, Actor.class);
        return recoveryActors();

    }

    public List<Director> getDirectorsByIMDBId(String IMDBId) throws XQException, IOException, IllegalAccessException, JAXBException, InstantiationException, ClassNotFoundException {

        final String url  = URL_BASE+"imdb?idIMDB="+IMDBId+"&format=XML&bornDied=1";
        connectionToAPI(url, Director.class);
        return recoveryDirectors();

    }

    private List<Director> recoveryDirectors(){
        List<Director> directors = new ArrayList<>();

        try {
            XQResultSequence xqResultSequence = preparedExpression.executeQuery();
            while (xqResultSequence.next()){
                XQItem  item = xqResultSequence.getItem();
                Director director = (Director) unmarshaller.unmarshal(item.getNode());
                directors.add(director);
            }
        } catch (XQException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        try {
            preparedExpression.close();
            xqConnection.close();
        } catch (XQException e) {
            e.printStackTrace();
        }

        return directors;

    }


    private void connectionToAPI(String url,Class type) throws XQException, JAXBException, ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {

        URLConnection urlConnection = new URL(url).openConnection();

        XQDataSource xqDataSource =(XQDataSource) Class.forName("net.sf.saxon.xqj.SaxonXQDataSource").newInstance();
        xqConnection = xqDataSource.getConnection();
        String fake = type.getName();
        if (type.getName().equalsIgnoreCase("com.udl.softarch.randomfilms.models.Film")){
            preparedExpression = xqConnection.prepareExpression(filmQuery);
        }else if (type.getName().equalsIgnoreCase("com.udl.softarch.randomfilms.models.Actor")){
            preparedExpression = xqConnection.prepareExpression(actorQuery);
        }
        preparedExpression.bindDocument(new javax.xml.namespace.QName("doc"),urlConnection.getInputStream(),null,null);
        context = JAXBContext.newInstance(type);
        unmarshaller = context.createUnmarshaller();
    }


}
