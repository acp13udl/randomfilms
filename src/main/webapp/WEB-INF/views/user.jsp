<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: adrian
  Date: 31/3/15
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<html>
  <head>
      <title>User greetings</title>
  </head>
  <body>
    <h2><b>User name: </b>${fn:escapeXml(usergreeting.getUsername())}</h2>
    <h2><b>User email: </b>${fn:escapeXml(user.getEmail())}</h2>
    <h3>Perfil de usuario:</h3>
    <c:choose>

      <c:when test="${not empty user}">
        <p>Username: ${fn:escapeXml(user.getUsername)}</p>
        <p>Username: ${fn:escapeXml(user.getEmail)}</p>
      </c:when>
      <c:otherwise>
        <p>User info not available</p>
      </c:otherwise>

    </c:choose>

    <a href="/users">Return to list</a>
  </body>
</html>