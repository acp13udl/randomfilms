
(function(){
    var app = angular.module("randomfilmsJS", []);
    console.log("aqui");
    app.controller("RandomFilmsController", ["$scope","$http",
        function($scope,$http) {
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
                console.log("../save/"+param);
                $http.get("../save/"+param,config)
                    .success(function (data) {
                        $('#loading').hide();
                    });
            }

            this.listFilms();
        }]);
}());