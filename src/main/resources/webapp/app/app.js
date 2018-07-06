(function (angular) {
    let app = angular.module("myApp", ['ui.router', 'ngResource', 'ngAnimate', 'ngStorage']);

    app.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
        $stateProvider
            .state('main', {
                url: '/',
                templateUrl: 'index.jsp'
            })
            .state('nord', {
                url: '/nord',
                templateUrl: 'nord.jsp'
            });

        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });
    })
        .run(function ($localStorage, $http, $location, $rootScope, $window) {
            if ($localStorage.user) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;
            }
            $rootScope.$on('$locationChangeStart', function () {
                if ($location.path() !== "/" && !$localStorage.token) {
                    $window.location.href = '/';
                } else if ($location.path() === "/" && $localStorage.token) {
                    $window.location.href = '/nord.jsp';
                }
            });
        });
}(angular));
