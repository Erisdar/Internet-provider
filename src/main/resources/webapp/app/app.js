(function (angular) {
    let app = angular.module("myApp", ['ngResource', 'ngAnimate', 'ngStorage', 'ngCookies']);

    app.config(function ($locationProvider) {
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });
    })
        .run(function ($localStorage, $http, $location, $rootScope, $window, $cookies) {
            if ($localStorage.user) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;
            }
            $rootScope.$on('$locationChangeStart', function () {
                if ($location.path() === "/" && $cookies.get("token")) {
                    $window.location.href = '/main.jsp';
                }
            });
        });
}(angular));
