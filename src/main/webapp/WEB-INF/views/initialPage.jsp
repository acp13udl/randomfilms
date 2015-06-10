<%--
  Created by IntelliJ IDEA.
  User: Lluís
  Date: 13/05/2015
  Time: 16:25
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
        function validateKey(e){
            $('#searchButton').attr('href',"/?search="+$('#searchInput').val());
            if(e.keyCode == 13){
                window.location.assign("/?search="+$('#searchInput').val());
                return false;
            }
        }
    </script>
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
                    <c:choose>
                        <c:when test="${not empty isAdminUser}">
                            <div class="input-group" style="margin-right: 10px;">
                                <a id="adminButton" class="btn btn-warning" href="/register">Register new user</a>
                            </div>
                        </c:when>
                    </c:choose>
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
            <div class="col-md-6">
                <div style="text-align: center;">
                    <h1 style="font-family: cursive; font-weight: bold;">Random Films</h1>
                    </br></br>
                </div>
                <table id="randomFilmsTable" class="table table-striped">
                    <thead>
                    <tr>
                        <th class="col-md-1">ID</th>
                        <th class="col-md-2">Title</th>
                        <th class="col-md-2">Year</th>
                        <th >Description</th>
                        <th class="col-md-1"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty films}">
                            <c:forEach var="film" items="${films}">
                                <tr>
                                    <td>${film.getId()}</td>
                                    <td>${film.getTitle()}</td>
                                    <td>${film.getYear()}</td>
                                    <td>${film.getSimplePlot()}</td>
                                    <td><a class="btn btn-success btn-sm" href="/films/${film.getId()}">Link</a></td>
                                </tr>
                                <%--<a href="/films/${film.getId()}">${film.getId()} - ${film.getTitle()}</a></br>--%>
                                </li>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                    </tbody>
                </table>
            </div>
            <div class="col-md-6">
                <c:choose>
                    <c:when test="${not empty searchFilms}">
                        <div style="text-align: center;">
                            <h1 style="font-family: cursive; font-weight: bold;">Search Result</h1>
                            </br></br>
                        </div>
                        <table id="searchFilmsTable" class="table table-striped">
                            <thead>
                            <tr>
                                <th class="col-md-2">Title</th>
                                <th class="col-md-2">Year</th>
                                <th>Description</th>
                                <th class="col-md-1"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="film" items="${searchFilms}">
                                <tr>
                                    <td>${film.getTitle()}</td>
                                    <td>${film.getYear()}</td>
                                    <td>${film.getSimplePlot()}</td>
                                    <td><a class="btn btn-success btn-sm" href="/save/${film.getUrlParams()}&isAngular=0">Link</a></td>
                                </tr>
                                </li>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>
