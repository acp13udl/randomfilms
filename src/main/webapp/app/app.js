
(function(){
    var app = angular.module("randomfilmsJS", []);
    console.log("aqui");
    app.controller("RandomFilmsController", ["$http",
        function($http) {
            this.RANDOM_API = "../";
            var randomFilmsSuccess = false;
            var randomFilmsCrt = this;
            var config = {headers: {
                'Accept': 'application/json'
            }
            }
            console.log("entre medio");

            this.randomFilmsRequestSuccess = function(){
                return randomFilmsSuccess;
            };

            this.searchFilmsSuccess = function(){
                if(randomFilmsSuccess) {
                    console.log('undefined ' + randomFilmsCrt.films.searchList);
                    return randomFilmsCrt.films.searchList != undefined;
                }
                return false;
            };

            this.listFilms = function(){
                $http.get(this.RANDOM_API,config)
                    .success(function (data) {
                        console.log(JSON.stringify(data.randomList));
                        randomFilmsCrt.films = data;
                        randomFilmsSuccess = true;
                    });
            };

            this.listFilms();

            this.searchFilm = function () {

            }

        }]);
}());