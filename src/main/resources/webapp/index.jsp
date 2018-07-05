<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" %>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/ngStorage/0.3.11/ngStorage.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.3/angular-ui-router.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-resource/1.6.9/angular-resource.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jsencrypt/2.3.1/jsencrypt.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="style/login.css">
    <script src="app/app.js"></script>
</head>
<body ng-app="myApp" ng-controller="myCtrl">
<div class="container w-25 m-auto">
    <div class="panel panel-login p-4">
        <div class="panel-heading">
            <div class="row d-flex justify-content-around" ng-init="active=true">
                <a href="#" ng-class="{'active': active === true}" ng-click="checked=false; active=!active"
                   class="login-form-link">Login</a>
                <a href="#" ng-class="{'active': active === false}" ng-click="checked=true; active=!active"
                   class="register-form-link">Register</a>
            </div>
            <hr>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-lg-12">
                    <form ng-hide="checked" class="animate-show-hide" role="form">
                        <div class="form-group">
                            <input type="text" name="login" ng-model="authLogin" tabindex="1" class="form-control"
                                   placeholder="Username" value="">
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" ng-model="authPassword" tabindex="2"
                                   class="form-control"
                                   placeholder="Password">
                        </div>
                        <div class="form-group text-center">
                            <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                            <label for="remember"> Remember Me</label>
                        </div>
                        <div class="form-group">
                            <div class="d-flex justify-content-center m-auto">
                                <input type="submit" ng-click="login()" name="login-submit" tabindex="4"
                                       class="form-control btn btn-login" value="Log In">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="text-center">
                                        <a href="https://phpoll.com/recover" tabindex="5"
                                           class="forgot-password">Forgot Password?</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <form ng-show="checked" class="animate-show-hide" role="form">
                        <div class="form-group">
                            <input type="text" name="login" ng-model="registrationLogin" tabindex="1"
                                   class="form-control" placeholder="Username" value="">
                        </div>
                        <div class="form-group">
                            <input type="email" name="email" ng-model="registrationEmail" tabindex="1"
                                   class="form-control" placeholder="Email Address" value="">
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" ng-model="registrationPassword" tabindex="2"
                                   class="form-control" placeholder="Password">
                        </div>
                        <div class="form-group">
                            <input type="password" name="confirm-password" tabindex="2" class="form-control"
                                   placeholder="Confirm Password">
                        </div>
                        <div class="form-group">
                            <div class="d-flex justify-content-center m-auto">
                                <input type="submit" ng-click="registration()" name="register-submit"
                                       tabindex="4" class="form-control btn btn-register"
                                       value="Register Now">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>