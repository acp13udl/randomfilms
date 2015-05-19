<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Allu
  Date: 07/05/2015
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de películas favorita</title>
</head>
<body>
<h2>Lista de películas favoritas</h2>
<ul>
    <c:if test="${not empty user}">

        <c:forEach var="film" items="${user.getFavoritesList()}">
            <p>ID: ${fn:escapeXml(film.getId())}</p>
            <p>Title: ${fn:escapeXml(film.getTitle())}</p>
            <p>Simple Plot: ${fn:escapeXml(film.getSimplePlot())}</p>
            <p><a href="/films/${film.getId()}">Go to info film</a></p>
        </c:forEach>

    </c:if>
</ul>
<p><a href="/">Back to mainpage</a></p>
</body>
</html>
