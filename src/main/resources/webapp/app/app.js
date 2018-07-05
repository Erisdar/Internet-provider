(function (angular) {
    let app = angular.module("myApp", ['ui.router', 'ngResource', 'ngAnimate', 'ngStorage']);

    app.controller("myCtrl", function ($scope, $http, $localStorage, $location, $window) {
        $scope.registration = function () {
            $http.get('/encrypt')
                .then(function (response) {
                    let encrypt = new JSEncrypt();
                    encrypt.setPublicKey(response.data);
                    let user = {
                        login: $scope.registrationLogin,
                        email: $scope.registrationEmail,
                        password: encrypt.encrypt($scope.registrationPassword)
                    };
                    $http.post('/registration', user, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
                        .then(
                            function () {
                                $location.path("/");
                            }, function () {
                                alert("Bad registration")
                            });
                });
        };
        $scope.login = function () {
            $http.get('/encrypt')
                .then(function (response) {
                    let encrypt = new JSEncrypt();
                    encrypt.setPublicKey(response.data);
                    let credentials = {
                        login: $scope.authLogin,
                        password: encrypt.encrypt($scope.authPassword)
                    };
                    $http.post('/login', credentials, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
                        .then(
                            function (response) {
                                $localStorage.token = response.headers().token;
                                $window.location.href = '/nord.jsp';
                            }, function () {
                                alert("Bad login or password")
                                $window.location.href = '/';
                            });
                });
        };

        $scope.logout = function () {
            delete $localStorage.token;
            $http.defaults.headers.common = {};
            $window.location.href = '/';
        };
    });

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
