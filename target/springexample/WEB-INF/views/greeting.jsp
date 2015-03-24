<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Greetings</title>
    </head>
    <body>
        <h3>Greeting:</h3>
        <p>${greeting.getContent()}</p>
        <a href="/greetings">Return to list</a>
    </body>
</html>