<%--
  Created by IntelliJ IDEA.
  User: Lluís
  Date: 05/05/2015
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>

<!--http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier-->

<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
<head>
    <link rel="stylesheet" href="../css/foundation.min.css">
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"/>
    <!--<link rel="stylesheet" href="app.css"/>-->
    <title>Random Films</title>
</head>
<body>
<div id="pageheader">
    <jsp:invoke fragment="header"/>
</div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="pagefooter">
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>