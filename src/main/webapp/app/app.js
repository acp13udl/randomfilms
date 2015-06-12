
(function(){
    var app = angular.module("randomfilmsJS", ['filmDirective','reviewForm']);


    app.controller("RandomFilmsController", ["$location","$http",
        function($location,$http) {


            var isInitialPage = true;
            var isFilmDetailPage = false;

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
            };

            var showsInitialPage = function(){
                isInitialPage = true;
                isFilmDetailPage = false;
            };

            this.isInitialPage = function(){
                return isInitialPage;
            };

            this.isFilmsDetailPage = function () {
                return isFilmDetailPage;
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
                $http.get("../save"+param+'&isAngular=0',config) //QUITAR PARAMETRO NO ES NECESARIO
                    .success(function (data) {
                        console.log(JSON.stringify(data));
                        $('#loading').hide();
                        randomFilmsCrt.currentFilm=data;
                        showFilsmDetailPage();
                        //$location.url('/films/'+data.id);
                    });

            };

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

            this.listFilms();
        }]);

    }());