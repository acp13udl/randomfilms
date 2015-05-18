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
        <c:when test="${not empty actors}">
            <form>
                <c:forEach var="actor" items="${actors}">
                    <div class="row">
                        <div class="col-md-3">
                            <img src="${actor.getUrlPhoto()}" />
                        </div>
                        <div class="col-md-9">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="inputActorName">Artistic name:</label>
                                        <input id="inputActorName" type="text" class="form-control" value="${fn:escapeXml(actor.getActorName())}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="inputActorRealName">Real name:</label>
                                        <input id="inputActorRealName" type="text" class="form-control" value="${fn:escapeXml(actor.getBirthName())}" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="inputActorHeight">Height:</label>
                                        <input id="inputActorHeight" type="text" class="form-control" value="${fn:escapeXml(actor.getHeight())}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="inputActorDateOfBirth">Date of birth:</label>
                                        <input id="inputActorDateOfBirth" type="text" class="form-control" value="${fn:escapeXml(actor.getDateOfBirth())}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="inputActorPlaceOfBirth">Place of birth:</label>
                                        <input id="inputActorPlaceOfBirth" type="text" class="form-control" value="${fn:escapeXml(actor.getPlaceOfBirth())}" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label for="inputActorBio">Bio:</label>
                                    <input id="inputActorBio" type="text" class="form-control" value="${fn:escapeXml(actor.getBio())}" readonly>
                                </div>
                            </div>
                            <div class="row">
                                <a href="http://www.imdb.com/name/${actor.getActorId()}" class="btn btn-info">Link IMDB</a>
                                <a class="btn btn-primary" href="/" style="float: right;">Back</a>
                                <a href="/films/${filmId}">Return to film</a>
                                <hr/>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </form>
        </c:when>
        <c:otherwise>
            <p>Actors info not available</p>
        </c:otherwise>

    </c:choose>
</div>
</body>
</html>
