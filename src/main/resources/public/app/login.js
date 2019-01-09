var app = angular.module("myApp");

app.controller("loginCtrl", function ($scope, $http, $localStorage, $location, $window, $cookies, $translate) {
    $scope.userName = $localStorage.user;
    $scope.role = $localStorage.role;
    $scope.loginCheck = false;
    $scope.loginSpin = false;
    $scope.emailCheck = false;
    $scope.emailSpin = false;

    $scope.changeLanguage = function (langKey) {
        $translate.use(langKey);
    };

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
                        $localStorage.role = response.headers().role;
                        $window.location.href = '/main.html';
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
                        $localStorage.role = response.headers().role;
                        $window.location.href = '/main.html';
                    }, function () {
                        alert("Bad login or password");
                    });
        });
    };
    $scope.logout = function () {
        $cookies.remove("token");
        delete $localStorage.user;
        $http.defaults.headers.common = {};
        $window.location.href = '/';
    };

    $scope.runSpin = function (event, fieldCheck, fieldSpin) {
        if (event.keyCode === 8) {
            $scope[fieldCheck] = false;
            $scope[fieldSpin] = true
        }
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
        scope: {
            matchTarget: '=',
        },
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            var passwordValidator = function (value) {
                ngModelCtrl.$setValidity('match', value === scope.matchTarget);
                return value;
            };

            ngModelCtrl.$parsers.unshift(passwordValidator);

            scope.$watch('matchTarget', function () {
                passwordValidator(ngModelCtrl.$viewValue);
            });
        }
    };
});

app.directive('validateEmail', ['$http', function ($http) {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            ngModelCtrl.$asyncValidators.emailValidator = function (modelValue, viewValue) {
                return $http.get('/users/search/email', {params: {email: modelValue || viewValue}})
                    .then(function (response) {
                    if(response.data){
                        ngModelCtrl.$setValidity('validateEmail', false);
                        scope.emailSpin = false;
                    } else {
                        ngModelCtrl.$setValidity('validateEmail', true);
                        scope.emailSpin = false;
                        scope.emailCheck = true;
                    }
                });
            }
        }
    };
}]);

app.directive('validateLogin', ['$http', function ($http) {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            ngModelCtrl.$asyncValidators.loginValidator = function (modelValue, viewValue) {
                return $http.get('users/search/login', {params: {login: modelValue || viewValue}})
                    .then(function (response) {
                    if(response.data){
                      ngModelCtrl.$setValidity('validateLogin', false);
                      scope.loginSpin = false;
                    } else {
                      ngModelCtrl.$setValidity('validateLogin', true);
                      scope.loginSpin = false;
                      scope.loginCheck = true;
                    }
                });
            }
        }
    };
}]);


