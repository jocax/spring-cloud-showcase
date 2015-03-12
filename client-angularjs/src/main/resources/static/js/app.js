'use strict';

var imageApp = angular.module('imageApp', ['infinite-scroll']);

imageApp.controller('ImageController', function($scope, ImageLoader) {
    $scope.imageLoader = new ImageLoader();
});

imageApp.controller('ImageLoaderWithHeaderController', function($scope, ImageLoaderWithHeader) {
    $scope.imageLoaderWithHeader = new ImageLoaderWithHeader();
});
