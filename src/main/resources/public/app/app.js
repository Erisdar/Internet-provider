(function (angular) {

    var app = angular.module("myApp", ['ui.router', 'ngResource', 'ngAnimate', 'ngStorage', 'ngCookies', 'pascalprecht.translate', 'ngMessages']);

    app.config(function ($stateProvider, $urlRouterProvider, $locationProvider, $translateProvider) {
            $urlRouterProvider.otherwise("/");
            $locationProvider.html5Mode({
                enabled: true,
                requireBase: false
            });
            $stateProvider
                .state('content', {
                    url: '/',
                    component: 'content'
                })
                .state('signin', {
                    url: '/signin',
                    component: 'login',
                    
                });
            $translateProvider
                .useStaticFilesLoader({
                    prefix: 'locale/locale-',
                    suffix: '.json'
                })
                .determinePreferredLanguage()
                .useLocalStorage()
                .useSanitizeValueStrategy('escape')
                .useLoaderCache(true);
        })
        .run(function ($localStorage, $http, $location, $rootScope, $window, $cookies) {
            if ($localStorage.user) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;
            }
            $rootScope.$on('$locationChangeStart', function () {
                if ($location.path() === "/login.html" && $cookies.get("token")) {
                    $window.location.href = '/main.html';
                }
            });
            //        $rootScope.$on('$locationChangeStart', function () {
            //            if ($location.path() === "/" && $localStorage.user) {
            //                $window.location.href = '/main.html';
            //            }
            //        });
        });
}(angular));