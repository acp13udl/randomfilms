
(function(){
    var app = angular.module("randomfilmsJS", []);
    console.log("aqui");
    app.controller("RandomFilmsController", ["$http",
        function($http) {
            this.RANDOM_API = "../";
            this.loading = false;
            var randomFilmsCrt = this;
            var config = {headers: {
                'Accept': 'application/json'
            }
            }
            randomFilmsCrt.films = []
            console.log("entre medio");
            this.isLoading = function(){
                return this.loading;
            };

            this.randomFilmsEmpty = function(){

                return randomFilmsCrt.films == [];
            };

            this.getFilms = function(){
                return randomFilmsCrt.films;
            };

            this.listFilms = function(){
                this.loading = true;
                console.log("entro aqui");
                $http.get(this.RANDOM_API,config)
                    .success(function (data) {
                        console.log(JSON.stringify(data));
                        //randomFilmsCrt.films.push(data);
                    });
                return true;
            };

        }]);
}());