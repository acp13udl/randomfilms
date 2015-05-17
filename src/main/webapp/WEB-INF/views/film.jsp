<%--
  Created by IntelliJ IDEA.
  User: Allu
  Date: 07/05/2015
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h3>Pelicula:</h3>
<c:choose>

    <c:when test="${not empty film}">
        <img src="${film.getUrlPoster()}" />
        <p>Title: ${fn:escapeXml(film.getTitle())}</p>
        <p>Year: ${fn:escapeXml(film.getYear())}</p>
        <p>Metascore: ${fn:escapeXml(film.getMetascore())}</p>
        <p>Plot: ${fn:escapeXml(film.getPlot())}</p>
        <p>Rated: ${fn:escapeXml(film.getRated())}</p>
        <p>Rating: ${fn:escapeXml(film.getRating())}</p>
        <p>ReleaseDate: ${fn:escapeXml(film.getReleaseDate())}</p>
        <a href="/actors/${film.getId()}">Ver actores</a>
        <p>runTime: ${fn:escapeXml(film.getRunTime())}</p>
        <p>simplePlot: ${fn:escapeXml(film.getSimplePlot())}</p>
        <p>genres: ${fn:escapeXml(film.getGenres())}</p>
        <a href="${film.getUrlIMDB()}">Go to IMDB</a>
    </c:when>
    <c:otherwise>
        <p>User info not available</p>
    </c:otherwise>

</c:choose>

<a href="/">Return to mainpage</a>
</body>
</html>
