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
      function validateKey(e){
          $('#searchButton').attr('href',"/?search="+$('#searchInput').val());
          if(e.keyCode == 13){
              window.location.assign("/?search="+$('#searchInput').val());
              return false;
          }
      }
        function checkFields(){
            if($('#pass1').val().length<=5){
                return false;
            }
            if($('#pass1').val().trim()!=$('#pass2').val().trim()){
                return false;
            }
            return true;
        }

      $( document ).ready(function (){
          var password = document.getElementById("pass1")
                  , confirm_password = document.getElementById("pass2");

          function validatePassword(){
              if(password.value != confirm_password.value) {
                  confirm_password.setCustomValidity("Passwords Don't Match");
              } else {
                  confirm_password.setCustomValidity('');
              }
          }

          password.onchange = validatePassword;
          confirm_password.onkeyup = validatePassword;
      });

  </script>
</head>
<body style="padding-top: 70px;">
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">RandomFilms</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">

            </ul>
            <form class="navbar-form navbar-left" onsubmit="return false;">
                <div class="input-group">
                    <a id="FavoritesButton" class="btn btn-danger" href="/validation">Favorites</a>
                </div>
            </form>
            <form class="navbar-form navbar-right" role="search" onsubmit="return false;">
                <div class="input-group">
                    <input type="text" id="searchInput" class="form-control" onkeyup="validateKey(event)" onblur="validateKey(event)" placeholder="Search">
                    <a id="searchButton" class="input-group-addon" href="/">Search</a>
                </div>

                <%--<button type="submit" class="btn btn-default">Submit</button>--%>
            </form>
        </div>
    </div>
</nav>

<div id="container" style="margin-left: 10px; margin-right: 10px;">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div style="text-align: center;">
                <h1 style="font-family: cursive; font-weight: bold;">Register new user</h1>
                </br></br>
            </div>

            <form method="post" action="/users" onsubmit="return checkFields()">
                <div class="form-group">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id='username' name="username" placeholder="Username" required/>
                </div>
                <!--http://www.the-art-of-web.com/javascript/validate-password/-->
                <!--http://codepen.io/diegoleme/pen/surIK-->
                <div class="form-group">
                    <label for="pass1">Password:</label>
                    <input type="password" class="form-control" id="pass1" name="password" placeholder="Password" required
                           minlength=6/>
                </div>
                <div class="form-group">
                    <label for="pass2">Repeat password:</label>
                    <input type="password" class="form-control" id="pass2" name="pass2" placeholder="Repeat password" required/>
                </div>
                <div class="form-group" style="float: right">
                    <input type="submit" class="btn btn-warning" value="Register">
                </div>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>
