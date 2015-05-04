package com.udl.softarch.randomfilms.Webservice;

/**
 * Created by adrian on 4/5/15.
 */
public class Webservice {


    private final String URL_BASE = "http://myapifilms.com/imdb?format=XML&";

    String filmQuery = "declare variable $doc := doc(\""+URL_BASE+"title=%s&limit=%s\");" +
            "for $movie in $doc/movies\n" +
            "return \n <film>\n <title>{data($movie/@title)}</title>\n"
            + "<year>{data($movie/@year)}</year>\n"
            +"<idIMDB>{data($movie/@idIMDB)}</idIMDB>\n " +
            "<genres>data($movie/@idIMDB)</genres>\n"
            +"<metascore>{data($movie/@metascore)}</metascore>\n"
            +"<plot>{data($movie/@plot)}</plot>\n" +
            " <rated>{data($movie/@rated)}</rated>\n"
            + "<rating> {data($movie/@rating)}</rating>\n"
            +"<releaseDate> {data($movie/@releaseDate)}</releaseDate>\n"
            +"<runtime> {data($movie/@runtime)}</runtime>\n"
            +"<simplePlot>{data($movie/@simpelPlot)}</simplePlot>\n"
            +"<urlIMDB> {data($movie/@urlIMDB)}</urlIMDB>\n"
            +"<urlPoster> {data($movie/@urlPoster)}</urlPoster>\n"
            +""
            + "</film>";

    public static void getFilmByTitle(String title,int limit){





    }
}
