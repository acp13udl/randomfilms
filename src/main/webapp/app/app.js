
(function(){
    var app = angular.module("randomfilmsJS", []);
    console.log("aqui");
    app.controller("RandomFilmsController", ["$location","$scope","$http",
        function($location,$scope,$http) {
            this.RANDOM_API = "../";
            var randomFilmsSuccess = false;
            var randomFilmsCrt = this;
            var config = {headers: {
                'Accept': 'application/json'
            }
            }


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
                        console.log(data.searchList);
                        randomFilmsCrt.films = data;
                        randomFilmsSuccess = true;
                        $('#loading').hide();
                    });
            };

            this.saveUrl = function(param){
                $('#loading').show();
                console.log("../save/"+param+'&isAngular=0');
                $http.get("../save"+param+'&isAngular=0',config)
                    .success(function (data) {
                        console.log(JSON.stringify(data));
                        $('#loading').hide();
                        window.location.assign("/app/films/film.html");
                    });
            }

            this.listFilms();
        }]);

    }());