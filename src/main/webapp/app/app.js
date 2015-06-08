// principal configuration for randomfilms app
(function(){
    var app = angular.module("randomFilmsJS", ["random-films"]);

    app.controller("InitialController",
        ["$http", function($http) {
            this.INITIALCONTROLLER_API = "../";
            this.loading = false;
            var randomFilmsController = this;

            this.isLoading = function(){
                return this.loading;
            };

            this.randomFilmsEmpty = function(){
                return this.randomFilms === undefined;
            }

            this.listFilms = function(){
                this.loading = true;
                $http.get(this.INITIALCONTROLLER_API)
                    .success(function (data) {
                        randomFilmsController.films = data;
                    });
            };

            //this.addGreeting = function(){
            //    $http.post(this.GREETINGS_API, this.newGreeting)
            //        .then(function(){
            //            greetingCtrl.newGreeting = {'date': Date.now()};
            //            greetingCtrl.listGreetings();
            //        });
            //};
        }]);
}());

