/**
 * Created by Adrian on 12/6/15.
 */

(function(){
    var app = angular.module("reviewForm", [ ]);

    app.directive("reviewForm", function(){
        return {
            restrict: "E",
            templateUrl: "review-form/review-form.html"
        };
    });

})();