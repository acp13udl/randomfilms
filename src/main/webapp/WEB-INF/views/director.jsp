<%--
  Created by IntelliJ IDEA.
  User: Allu
  Date: 07/05/2015
  Time: 16:49
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
    <c:choose>
        <c:when test="${not empty directors}">
            <form>
                <c:forEach var="director" items="${directors}">
                    <div class="row">
                        <div class="col-md-3">
                            <img src="${director.getUrlPhoto()}" />
                        </div>
                        <div class="col-md-9">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="inputActorName">Artistic name:</label>
                                        <input id="inputActorName" type="text" class="form-control" value="${fn:escapeXml(director.getDirectorName())}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label for="inputActorRealName">Real name:</label>
                                    <div class="input-group">
                                        <input id="inputActorRealName" type="text" class="form-control" value="${fn:escapeXml(director.getBirthName())}" readonly>
                                        <a href="http://www.imdb.com/name/${director.getDirectorId()}" class="input-group-addon">Link IMDB</a>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="inputActorHeight">Height:</label>
                                        <input id="inputActorHeight" type="text" class="form-control" value="${fn:escapeXml(director.getHeight())}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="inputActorDateOfBirth">Date of birth:</label>
                                        <input id="inputActorDateOfBirth" type="text" class="form-control" value="${fn:escapeXml(director.getDateOfBirth())}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="inputActorPlaceOfBirth">Place of birth:</label>
                                        <input id="inputActorPlaceOfBirth" type="text" class="form-control" value="${fn:escapeXml(director.getPlaceOfBirth())}" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin-left: 5px; margin-right: 5px;">
                                <div class="form-group">
                                    <label for="inputActorBio">Bio:</label>
                                    <textarea id="inputActorBio" type="text" class="form-control" rows="6" readonly>${fn:escapeXml(director.getBio())}</textarea>
                                </div>
                            </div>
                            <div class="row">

                            </div>
                        </div>
                    </div>
                    <hr/>
                </c:forEach>
                <a class="btn btn-primary" href="/films/${filmId}" style="float: right;">Return to film</a>
            </form>
        </c:when>
        <c:otherwise>
            <p>Actors info not available</p>
        </c:otherwise>

    </c:choose>
</div>
</body>
</html>
