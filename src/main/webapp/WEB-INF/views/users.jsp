<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: adrian
  Date: 31/3/15
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Users list</title>
</head>
  <body>
    <h2>User list</h2>
    <ul>
      <c:if test="${not empty users}">
        <c:forEach var="user" items="${users}">
           <li>
            <p>${fn:escapeXml(user.getUsername())}</p>
          </li>
        </c:forEach>
      </c:if>
    </ul>
  </body>
</html>
