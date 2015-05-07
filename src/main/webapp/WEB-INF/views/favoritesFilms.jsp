<%--
  Created by IntelliJ IDEA.
  User: Allu
  Date: 07/05/2015
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Lista de películas favoritas</h2>
<ul>
    <c:if test="${not empty films}">
        <c:forEach var="film" items="${films}">
            <li><a href="/films/${film.getTitle}">${film.getTitle()}</a>: ${fn:escapeXml(greeting.getContent())}</li>
        </c:forEach>
    </c:if>
</ul>
<p><a href="/user">Back to user</a></p>
</body>
</html>
