
(function(){
    var app = angular.module("randomfilmsJS",["randomFilmsModule"]);
    console.log("paso inicio");
    app.controller("InitialController", ["$http",
        function($http) {
            this.INITIALCONTROLLER_API = "https://randomfilms.herokuapp.com/";
            this.loading = false;
            var randomFilmsController = this;
            console.log("initialcontroller");
            this.isLoading = function(){
                return this.loading;
            };

            this.randomFilmsEmpty = function(){
                console.log("entro?");
                return undefined;
            };

            this.listFilms = function(){
                this.loading = true;
                $http.get(this.INITIALCONTROLLER_API)
                    .success(function (data) {
                        randomFilmsController.films = data;
                        console.log("Print");
                    });
            };
        }]);
});
