let app = angular.module("myApp");

app.controller("myCtrl", function ($scope, $http, $localStorage, $location, $window, $cookies) {
    $scope.registration = function () {
        getEncryption(function (encrypt) {
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
        getEncryption(function (encrypt) {
            let credentials = {
                login: $scope.authLogin,
                password: encrypt.encrypt($scope.authPassword)
            };
            $http.post('/login', credentials, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
                .then(
                    function (response) {
                        $localStorage.token = response.headers().token;
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
        delete $localStorage.token;
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


