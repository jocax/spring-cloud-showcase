
imageApp.controller('DemoController', function($scope) {
    $scope.images = [1, 2, 3, 4];

    $scope.loadMore = function() {
        var last = $scope.images[$scope.images.length - 1];
        for(var i = 1; i <= 4; i++) {
            $scope.images.push(last + i);
        }
    };
});