<%--
  Created by IntelliJ IDEA.
  User: adrian
  Date: 27/5/15
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Random Films</title>
  <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script type="text/javascript">
    function checkFields(){
      if($('#username').val().trim()==''){
        return false;
      }
      if($('#pass1').val().trim()==''){
        return false;
      }
      if($('#pass1').val().length<=5){
        return false;
      }
      if($('#pass1').val().trim()!=$('#pass2').val().trim()){
        return false;
      }
      return true;
    }
  </script>
</head>
<body>
<form method="get" action="/users" onsubmit="checkFields()">
  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
    <input type="text" id='username' name="username" placeholder="Username" required/>
    <input type="password" id="pass1" name="password" placeholder="Password" required/>
    <input type="password" id="pass2" placeholder="Repeat password" required/>
    <input type="submit" value="Register">

</form>
</body>
</html>
