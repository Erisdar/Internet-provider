<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Login</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="//code.angularjs.org/snapshot/angular.min.js"></script>
    <script src="//code.angularjs.org/snapshot/angular-animate.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.7.2/angular-cookies.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/ngStorage/0.3.11/ngStorage.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-resource/1.6.9/angular-resource.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jsencrypt/2.3.1/jsencrypt.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-translate/2.18.1/angular-translate.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-translate/2.18.1/angular-translate-storage-local/angular-translate-storage-local.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-translate/2.18.1/angular-translate-storage-cookie/angular-translate-storage-cookie.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-translate/2.18.1/angular-translate-loader-static-files/angular-translate-loader-static-files.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="style/login.css">
    <script src="app/app.js"></script>
    <script src="app/login.js"></script>
</head>
<body ng-app="myApp" ng-controller="loginCtrl">
<div class="login-container">
    <div class="panel panel-login p-4" ng-cloak>
        <div class="panel-heading">
            <div class="row d-flex justify-content-around" ng-init="active=true">
                <a href="#" ng-class="{'active': active === true}" ng-click="checked=false; active=!active"
                   class="login-form-link form-link">{{'LOGIN'| translate}}</a>
                <a href="#" ng-class="{'active': active === false}" ng-click="checked=true; active=!active"
                   class="register-form-link form-link">{{'REGISTRATION'| translate}}</a>
            </div>
            <hr>
        </div>
        <div class="panel-body" ng-cloak>
            <div class="row">
                <div class="col-lg-12">
                    <form name="signin" ng-submit="login(authLogin, authPassword)" ng-hide="checked"
                          class="animate-show-hide" role="form">
                        <div class="form-group">
                            <input type="text" name="login" ng-model="authLogin" tabindex="1" class="form-control"
                                   placeholder="{{'USERNAME'| translate}}" required>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" ng-model="authPassword" tabindex="2"
                                   class="form-control" placeholder="{{'PASSWORD'| translate}}" required>
                        </div>
                        <div class="form-group text-center">
                            <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                            <label for="remember" translate="REMEMBER_ME"></label>
                        </div>
                        <div class="form-group">
                            <div class="d-flex justify-content-center m-auto">
                                <button type="submit" name="login-submit" tabindex="4"
                                        class="form-control btn btn-login"
                                        translate="LOGIN"></button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="text-center">
                                        <a href="#" tabindex="5" class="forgot-password"
                                           translate="FORGOT_PASSWORD"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <form name="register"
                          ng-submit="registration(registrationLogin, registrationEmail, registrationPassword)"
                          ng-show="checked" class="animate-show-hide" role="form">
                        <div class="form-group">
                            <input type="text" name="login" ng-model="registrationLogin" tabindex="1"
                                   class="form-control" placeholder="{{'USERNAME'| translate}}" required
                                   validate-login>
                        </div>
                        <div class="form-group">
                            <input type="email" name="email" ng-model="registrationEmail" tabindex="1"
                                   class="form-control" placeholder="{{'EMAIL_ADDRESS'| translate}}" required>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" ng-model="registrationPassword" tabindex="2"
                                   class="form-control" placeholder="{{'PASSWORD'| translate}}" required>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password_confirm" ng-model="password_confirm" tabindex="2"
                                   class="form-control" placeholder="{{'CONFIRM_PASSWORD'| translate}}" required
                                   validate-equals="registrationPassword">
                        </div>
                        <div class="form-group">
                            <div class="d-flex justify-content-center m-auto">
                                <button type="submit" name="register-submit" tabindex="4"
                                        class="form-control btn btn-register"
                                        ng-disabled="register.$invalid" translate="REGISTER_NOW"></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<img src="img/bird.png" class="bird-img" alt="Bird">
</body>
</html>