/**
 * Created by Lluís on 12/6/15.
 */
(function(){
    var app = angular.module("favoritesDirective", [ ]);

    app.directive('favoritesDirective', function(){
        console.log("favorites")
        return {
            restrict: 'E',
            templateUrl: 'favorites/favorites.html',
            controller: function() {

                var favoritesController = this;
                //console.log(window.currentFilm);
                //filmController.film = window.currentFilm
            }
        };
    });

}());