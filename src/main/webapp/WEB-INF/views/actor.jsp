<%--
  Created by IntelliJ IDEA.
  User: Allu
  Date: 07/05/2015
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h3>Actores:</h3>
<c:choose>

    <c:when test="${not empty actor}">
        <img src="${actor.getUrlPhoto()}" />
        <p>Nombre artistico: ${fn:escapeXml(actor.getActorName())}</p>
        <p>Nombre real: ${fn:escapeXml(actor.getBirthName())}</p>
        <p>Fecha de nacimiento: ${fn:escapeXml(actor.getDateOfBirth())}</p>
        <p>Lugar de nacimiento: ${fn:escapeXml(actor.getPlaceOfBirth())}</p>
        <p>Estatura: ${fn:escapeXml(actor.getHeight())}</p>
        <p>Biografia: ${fn:escapeXml(actor.getBio())}</p>
    </c:when>
    <c:otherwise>
        <p>User info not available</p>
    </c:otherwise>

</c:choose>

<a href="/users/${user.getId()}/films">Return to film</a>
</body>
</html>
