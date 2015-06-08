
<%--
  Created by IntelliJ IDEA.
  User: adrian
  Date: 31/3/15
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Random Films</title>
    <%--<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>--%>
    <%--<link rel="stylesheet" href="../css/foundation.min.css">--%>
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script type="text/javascript">
        /*$('document').ready(function(){
            $('#usersTable tr').each(function(idx){
                $(this).children().first().html(idx + 1);
            });
        });*/

        function validateKey(e){
            $('#searchButton').attr('href',"/?search="+$('#searchInput').val());
            if(e.keyCode == 13){
                window.location.assign("/?search="+$('#searchInput').val());
                return false;
            }
        }

    </script>
    <style>
        table {
            counter-reset: rowNumber - 1;
        }

        table tr {
            counter-increment: rowNumber;
        }

        table tr td:first-child::before {
            content: counter(rowNumber);
            min-width: 1em;
            margin-right: 0.5em;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default">
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
                <h1 style="font-family: cursive; font-weight: bold;">Users</h1>
                </br></br>
            </div>
            <div>
                <c:if test="${not empty users}">
                    <table id="usersTable" class="table table-striped">
                        <thead>
                            <tr>
                                <th class="col-md-2">#</th>
                                <th class="col-md-4">Username</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td scope="row"></td>
                                <td>${fn:escapeXml(user.getUsername())}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>

            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</body>
</html>
