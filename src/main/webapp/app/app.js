
(function(){
    var app = angular.module("randomfilmsJS", ['filmDirective','reviewForm','favoritesDirective']);


    app.controller("RandomFilmsController", ["$location","$http","$rootScope", "$scope",
        function($location,$http,$scope,$rootScope) {


            var isInitialPage = true;
            var isFilmDetailPage = false;
            var isFavoritesPage = false;

            this.newReview={};

            var randomFilmsSuccess = false;

            var randomFilmsCrt = this;

            this.RANDOM_API = "../";
            this.POST_REVIEW = "../films/"

            var config = {headers: {
                'Accept': 'application/json'
            }
            }

            var showFilsmDetailPage = function(){
                isInitialPage = false;
                isFilmDetailPage = true;
                isFavoritesPage = false;
            };

            var showsInitialPage = function(){
                isInitialPage = true;
                isFilmDetailPage = false;
                isFavoritesPage = false;
            };

            var showsFavoritesPage = function(){
                isInitialPage = false;
                isFilmDetailPage = false;
                isFavoritesPage = true;
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

            this.randomFilmsRequestSuccess = function(){
                return randomFilmsSuccess;
            };

            this.searchFilmsSuccess = function(){
                if(randomFilmsSuccess) {
                    return randomFilmsCrt.films.searchList != undefined;
                }
                return false;
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
                $http.post(this.POST_REVIEW+randomFilmsCrt.currentFilm.id,this.newReview)
                    .success(function (data) {
                        console.log(data);
                        $('#loading').hide();
                        randomFilmsCrt.currentFilm=data; //new films with reviews
                        showFilsmDetailPage();
                    });
            };


            var authenticate = function(credentials, callback) {
                $('#loading').show();
                $http.get('../validation').success(function(data) {
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
                }).error(function() {
                    $rootScope.authenticated = false;
                    callback && callback();
                });

            };

            $scope.credentials = {};
            $scope.login = function() {
                authenticate($scope.credentials, function() {
                    if ($rootScope.authenticated) {
                        $scope.error = false;
                    } else {
                        $scope.error = true;
                    }
                });
            };

            this.listFilms();
        }]);

    }());