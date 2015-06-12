
(function(){
    var app = angular.module("randomfilmsJS", ['filmDirective','reviewForm','favoritesDirective','actorsDirective','directorsDirective']);


    app.controller("RandomFilmsController", ["$location","$http","$rootScope", "$scope",
        function($location,$http,$rootScope,$scope) {


            var isInitialPage = true;
            var isFilmDetailPage = false;
            var isFavoritesPage = false;
            var isDirectorPage = false;
            var isActorPage = false;

            this.newReview={};

            var randomFilmsSuccess = false;

            var randomFilmsCrt = this;

            this.RANDOM_API = "../";
            this.FILMS_URL = "../films/";

            var config = {headers: {
                'Accept': 'application/json'
            }
            };

            var showFilsmDetailPage = function(){
                isInitialPage = false;
                isFilmDetailPage = true;
                isFavoritesPage = false;
                isDirectorPage = false;
                isActorPage = false;
            };

            var showsInitialPage = function(){
                isInitialPage = true;
                isFilmDetailPage = false;
                isFavoritesPage = false;
                isDirectorPage = false;
                isActorPage = false;
            };

            var showsFavoritesPage = function(){
                isInitialPage = false;
                isFilmDetailPage = false;
                isFavoritesPage = true;
                isDirectorPage = false;
                isActorPage = false;
            };

            var showsDirectorsPage = function(){
                isInitialPage = false;
                isFilmDetailPage = false;
                isFavoritesPage = false;
                isDirectorPage = true;
                isActorPage = false;
            };

            var showsActorsPage = function(){
                isInitialPage = false;
                isFilmDetailPage = false;
                isFavoritesPage = false;
                isDirectorPage = false;
                isActorPage = true;
            };

            this.isInitialPage = function(){
                return isInitialPage;
            };

            this.isFilmsDetailPage = function () {
                return isFilmDetailPage;
            };

            this.isFavoritesPage = function () {
                return isFavoritesPage;
            };

            this.isActorsPage = function () {
                return isActorPage;
            };

            this.isDirectorsPage = function () {
                return isDirectorPage;
            };


            this.randomFilmsRequestSuccess = function(){
                return randomFilmsSuccess;
            };

            this.searchFilmsSuccess = function(){
                if(randomFilmsSuccess) {
                    return randomFilmsCrt.films.searchList != undefined;
                }
                return false;
            };

            this.userIsAuthenticated = function (){

                return randomFilmsCrt.user && authenticated;
            };

            this.listFilms = function(){
                $http.get(this.RANDOM_API,config)
                    .success(function (data) {
                        randomFilmsCrt.films = data;
                        randomFilmsSuccess = true;
                    });
            };

            this.listSearchFilms = function(){
                $('#loading').show();
                $http.get(this.RANDOM_API+searchUrl,config)
                    .success(function (data) {
                        randomFilmsCrt.films = data;
                        randomFilmsSuccess = true;
                        showsInitialPage();
                        $('#loading').hide();
                    });
            };

            this.saveUrl = function(param){
                $('#loading').show();
                $http.get("../save"+param,config)
                    .success(function (data) {
                        console.log(JSON.stringify(data));
                        randomFilmsCrt.currentFilm=data;
                        showFilsmDetailPage();
                        $('#loading').hide();
                    });

            };

            this.getFilm = function(param){
                $('#loading').show();
                $http.get("../films/"+param,config)
                    .success(function (data) {
                        console.log(JSON.stringify(data));
                        randomFilmsCrt.currentFilm=data;
                        showFilsmDetailPage();
                        $('#loading').hide();
                    });
            }

            this.addFavorite = function(credentials, callback){
                $('#loading').show();
                $http.get("../validation/add/?filmId="+this.currentFilm.id)
                    .success(function (data) {
                        console.log(JSON.stringify(data));
                        if (data.username) {
                            $rootScope.authenticated = true;
                        } else {
                            $rootScope.authenticated = false;
                        }
                        callback && callback();
                        showsFavoritesPage();
                        randomFilmsCrt.user = data;
                        $('#loading').hide();
                    });
            }

            this.addReview = function(){
                $('#loading').show();
                $http.post(this.FILMS_URL+randomFilmsCrt.currentFilm.id,this.newReview)
                    .success(function (data) {
                        console.log(data);
                        $('#loading').hide();
                        randomFilmsCrt.currentFilm=data; //new films with reviews
                        showFilsmDetailPage();
                    });
            };

            this.getActors = function(){
                $('#loading').show();
                $http.get(this.FILMS_URL+randomFilmsCrt.currentFilm.id+'/actors',config)
                    .success(function (data) {
                        console.log(JSON.stringify(data));
                        randomFilmsCrt.currentFilmActors=data;
                        console.log("ENTRO lluis");
                        showsActorsPage();
                        $('#loading').hide();
                    });
            };

            this.getDirectors = function(){
                $('#loading').show();
                $http.get(this.FILMS_URL+randomFilmsCrt.currentFilm.id+'/directors',config)
                    .success(function (data) {
                        console.log("DIrectors"+JSON.stringify(data));
                        randomFilmsCrt.currentFilmDirectors=data;
                        showsDirectorsPage();
                        $('#loading').hide();
                    });
            };

            var authenticated;
            var authenticate = function(credentials, callback) {
                $('#loading').show();
                $http.get('../validation').success(function(data) {
                    console.log(JSON.stringify(data));
                    if (data.username) {
                        authenticated = true;
                    } else {
                        authenticated = false;
                    }
                    callback && callback();
                    showsFavoritesPage();
                    randomFilmsCrt.user = data;
                    $('#loading').hide();
                }).error(function() {
                    authenticated = false;
                    callback && callback();
                });
            };

            $scope.credentials = {};
            $scope.login = function() {
                authenticate($scope.credentials, function() {
                    if (authenticated) {
                        $scope.error = false;
                    } else {
                        $scope.error = true;
                    }
                });
            };

            this.listFilms();
        }]);

    }());