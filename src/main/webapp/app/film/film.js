/**
 * Created by Adrian on 11/6/15.
 */
(function(){
    var app = angular.module("filmDirective", [ ]);


    app.directive('filmDirective', function(){
        console.log("directive")
        return {
            restrict: 'E',
            templateUrl: 'film/film.html',
            controller: function() {

                var filmController = this;
                console.log(window.currentFilm);
                filmController.film = window.currentFilm
            }
        };
    });

}());