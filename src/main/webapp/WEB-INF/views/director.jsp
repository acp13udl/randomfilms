<%--
  Created by IntelliJ IDEA.
  User: Allu
  Date: 17/05/2015
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h3>Directores:</h3>
<c:choose>

    <c:when test="${not empty directors}">
        <c:forEach var="director" items="${directors}">
        <img src="${director.getUrlPhoto()}" />
        <p>Nombre artistico: ${fn:escapeXml(director.getDirectorName())}</p>
        <p>Nombre real: ${fn:escapeXml(director.getBirthName())}</p>
        <p>Fecha de nacimiento: ${fn:escapeXml(director.getDateOfBirth())}</p>
        <p>Lugar de nacimiento: ${fn:escapeXml(director.getPlaceOfBirth())}</p>
        <p>Estatura: ${fn:escapeXml(director.getHeight())}</p>
        <p>Biografia: ${fn:escapeXml(director.getBio())}</p>
            <hr/>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>User info not available</p>
    </c:otherwise>

</c:choose>

<a href="/films/${filmId}">Return to film</a>
</body>
</html>
