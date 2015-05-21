<%--
  Created by IntelliJ IDEA.
  User: Allu
  Date: 07/05/2015
  Time: 14:20
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
            <c:when test="${not empty film}">
                <form>
                    <div class="row">
                        <div class="col-md-3">
                            <img src="${film.getUrlPoster()}" />
                        </div>
                        <div class="col-md-9">
                            <div class="row">
                                <div class="col-md-9">
                                    <div class="form-group">
                                        <label for="inputTitle">Title:</label>
                                        <input id="inputTitle" type="text" class="form-control" value="${fn:escapeXml(film.getTitle())}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="inputYear">Year:</label>
                                        <input id="inputYear" type="text" class="form-control" value="${fn:escapeXml(film.getYear())}" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="inputMetascore">Metascore:</label>
                                        <input id="inputMetascore" type="text" class="form-control" value="${fn:escapeXml(film.getMetascore())}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="inputRated">Rated:</label>
                                        <input id="inputRated" type="text" class="form-control" value="${fn:escapeXml(film.getRated())}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="inputRating">Rating:</label>
                                        <input id="inputRating" type="text" class="form-control" value="${fn:escapeXml(film.getRating())}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="inputRunTime">Run time:</label>
                                        <input id="inputRunTime" type="text" class="form-control" value="${fn:escapeXml(film.getRunTime())}" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-9">
                                    <div class="form-group">
                                        <label for="inputGenres">Genres:</label>
                                        <input id="inputGenres" type="text" class="form-control" value="${fn:escapeXml(film.getGenres())}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="inputRelesedate">Release date:</label>
                                        <input id="inputRelesedate" type="text" class="form-control" value="${fn:escapeXml(film.getReleaseDate())}" readonly>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputSimplePlot">Simple plot:</label>
                                <textarea id="inputSimplePlot" class="form-control" readonly>${fn:escapeXml(film.getSimplePlot())}</textarea>
                            </div>

                        </div>
                    </div>
                    <div class="row" style="margin-left: 5px; margin-right: 5px;">
                        <div class="form-group">
                            <label for="inputPlot">Plot:</label>
                            <textarea id="inputPlot" class="form-control" rows="5" readonly>${fn:escapeXml(film.getPlot())}</textarea>
                        </div>
                    </div>
                    <div class="row" style="margin-left: 5px; margin-right: 5px;">
                        <a class="btn btn-success" href="${film.getId()}/actors">Actores</a>
                        <a class="btn btn-success" href="${film.getId()}/directors">Directores</a>

                        <a class="btn btn-info" href="${film.getUrlIMDB()}">Link IMDB</a>

                        <a class="btn btn-primary" href="/" style="float: right;">Back</a>
                        <a class="btn btn-warning" href="/validation/add?filmId=${film.getId()}" style="float: right; margin-right: 5px;">Add Favorites</a>
                    </div>
                </form>
                <hr/>
                <c:if test="${not empty username}">
                    <form action="/films/${film.getId()}" method="post">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="form-group">
                                    <label for="inputComment">Comment:</label>
                                    <textarea id="inputComment" class="form-control" type="text" name="comment" rows="4" required></textarea>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="inputRat">Rating:</label>
                                    <input type="hidden"
                                           name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                    <input id="inputRat" name="rating" class="form-control" type="number" max="10" min="0" value="5"/></br>
                                    <input type="hidden" name="author" value="${username}"/>
                                    <button class="btn btn-warning" type="submit" style="float: right;">Send</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <hr/>
                </c:if>
                <ul>
                    <c:if test="${not empty film.getReviews()}">
                        <c:forEach var="review" items="${film.getReviews()}">
                            <div class="row">
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label for="inputReviewAuthor">Author:</label>
                                        <input id="inputReviewAuthor" type="text" class="form-control" value="${review.getAuthor()}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label for="inputReviewRate">Rating:</label>
                                        <input id="inputReviewRate" type="text" class="form-control" value="${review.getRating()}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-8"></div>
                            </div>
                            <div class="row" style="margin-right: 5px; margin-left: 1px">
                                <label for="inputReviewComment">Comment:</label>
                                <textarea id="inputReviewComment" class="form-control" readonly>${review.getComment()}</textarea>
                            </div>
                            <hr/>
                        </c:forEach>
                    </c:if>
                </ul>
            </c:when>
            <c:otherwise>
                <p>User info not available</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
