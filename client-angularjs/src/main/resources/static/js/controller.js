imageApp.controller('ImageController', function ($scope) {
    $scope.images = [1, 2, 3, 4];

    $scope.loadMore = function () {
        var last = $scope.images[$scope.images.length - 1];
        for (var i = 1; i <= 4; i++) {
            $scope.images.push(last + i);
        }
    };
});



imageApp.factory('ImageLoaderWithHeader', function ($http) {

    var ImageLoaderWithHeader = function () {
        this.items = [];
        this.busy = false;
        this.id = '0';
    };

    //http://tutorials.jenkov.com/angularjs/ajax.html

    ImageLoaderWithHeader.prototype.nextPage = function ($http) {
        if (this.busy) return;
        this.busy = true;

        var url = "http://localhost:8082/service/image/" + this.id + "/?callback=JSON_CALLBACK";

        $http.jsonp(url).
            success(function (data) {
                //alert('Success: ' + data);
                var items = [data];
                for (var i = 0; i <= items.length; i++) {
                    this.items.push(data);
                }
                this.id = this.items[this.items.length - 1].id;
                this.busy = false;
            }.bind(this)).error(function (data) {
                alert('Error: ' + data);
            });
    };


    ImageLoaderWithHeader.prototype.nextPage2 = function ($jquery) {
        if (this.busy) return;
        this.busy = true;

        var url = "http://localhost:8082/service/image/" + this.id + "/?callback=JSON_CALLBACK";

        $http.jsonp(url).
            success(function (data) {
                //alert('Success: ' + data);
                var items = [data];
                for (var i = 0; i <= items.length; i++) {
                    this.items.push(data);
                }
                this.id = this.items[this.items.length - 1].id;
                this.busy = false;
            }.bind(this)).error(function (data) {
                alert('Error: ' + data);
            });
    };


    return ImageLoaderWithHeader;
})
;
