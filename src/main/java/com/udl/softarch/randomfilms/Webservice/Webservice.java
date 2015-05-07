package com.udl.softarch.randomfilms.Webservice;

import com.udl.softarch.randomfilms.models.Film;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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

    String genresQuery = "for $g in $m//genres/genre\n"
            +"return\n"
            +"<genre> {$g/text()}</genre>";

    String filmQuery = "declare variable $doc external;\n"
            +"for $m in $doc//movies/movie\n"
            +"return\n"
            +"<film>\n"
            +"<title>{$m/title/text()}</title>\n"
            +"<genres>{"+genresQuery+"}</genres>"
            +"</film>";

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

    private ArrayList<Film> recoveryFilms(){
        ArrayList<Film> films = new ArrayList<>();

        try {
            XQResultSequence xqResultSequence = preparedExpression.executeQuery();
            while (xqResultSequence.next()){
                XQItem  item = xqResultSequence.getItem();
                Film film = (Film) unmarshaller.unmarshal(item.getNode());
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

    private void connectionToAPI(String url,Class type) throws XQException, JAXBException, ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {

        URLConnection urlConnection = new URL(url).openConnection();

        XQDataSource xqDataSource =(XQDataSource) Class.forName("net.sf.saxon.xqj.SaxonXQDataSource").newInstance();
        xqConnection = xqDataSource.getConnection();
        preparedExpression = xqConnection.prepareExpression(filmQuery);
        preparedExpression.bindDocument(new javax.xml.namespace.QName("doc"),urlConnection.getInputStream(),null,null);
        context = JAXBContext.newInstance(type);
        unmarshaller = context.createUnmarshaller();
    }


}
