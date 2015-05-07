<%--
  Created by IntelliJ IDEA.
  User: Allu
  Date: 07/05/2015
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Película</title>
</head>
<body>

<p><a href="/film">Film</a></p>

<c:if test="${not empty film}">
    <h2>Título: ${film.getTitle()}</h2>

</c:if>

</body>
</html>
