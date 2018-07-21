var app = angular.module("myApp");

app.controller("loginCtrl", function ($scope, $http, $localStorage, $location, $window, $cookies) {
    $scope.registration = function (login, email, password) {
        getEncryption(function (encrypt) {
            let user = {
                login: login,
                email: email,
                password: encrypt.encrypt(password)
            };
            $http.post('/registration', user, {headers: {'Content-Type': 'application/json'}})
                .then(
                    function (response) {
                        $localStorage.user = response.headers().user;
                        $window.location.href = '/main.jsp';
                    }, function () {
                        alert("Bad registration")
                    });
        });
    };
    $scope.login = function (login, password) {
        getEncryption(function (encrypt) {
            let credentials = {
                login: login,
                password: encrypt.encrypt(password)
            };
            $http.post('/login', credentials, {headers: {'Content-Type': 'application/json'}})
                .then(
                    function (response) {
                        $localStorage.user = response.headers().user;
                        $window.location.href = '/main.jsp';
                    }, function () {
                        alert("Bad login or password");
                        $window.location.href = '/';
                    });
        });
    };
    $scope.logout = function () {
        $cookies.remove("token");
        delete $localStorage.user;
        $http.defaults.headers.common = {};
        $window.location.href = '/';
    };

    let getEncryption = function (callback) {
        $http.get('/encrypt')
            .then(function (response) {
                let encrypt = new JSEncrypt();
                encrypt.setPublicKey(response.data);
                callback(encrypt);
            })
    };
});

app.directive('validateEquals', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            function validateEqual(value) {
                var valid = (value === scope.$eval(attrs.validateEquals));
                ngModelCtrl.$setValidity('equal', valid);
                return valid ? value : 'undefined';
            }

            ngModelCtrl.$parsers.push(validateEqual);
        }
    };
});

app.directive('validateLogin', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            function validateLogin(value) {
                var valid = function (value) {
                    $http.post('/checkData', value)
                        .then(function (response) {
                            return response.data.isValid;
                        });
                };
                return valid ? value : 'undefined';
            }

            ngModelCtrl.$parsers.push(validateLogin);
        }
    };
});


