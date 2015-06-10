
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

            this.listFilms = function(){
                this.loading = true;
                console.log("entro aqui");
                $http.get(this.RANDOM_API,config)
                    .success(function (data) {
                        console.log(JSON.stringify(data));
                        randomFilmsCrt.films = data;
                        randomFilmsSuccess = true;
                    });
            };

        }]);
}());