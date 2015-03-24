<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: davidkaste
  Date: 23/02/15
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Greeting list</title>
</head>
<body>
    <h2>Greeting list</h2>
    <ul>
        <c:if test="${not empty greetings}">
            <c:forEach var="greeting" items="${greetings}">
                <li>
                    <a href="/greetings/${greeting.getId()}">${greeting.getId()}</a>: ${fn:escapeXml(greeting.getContent())} <a href="/greetings/${greeting.getId()}/form">Edit greeting</a>
                </li>
            </c:forEach>
        </c:if>
    </ul>
    <a href="/greetings/form">New greeting</a>
    <a href="/greetings/random">Random greeting</a>
</body>
</html>
