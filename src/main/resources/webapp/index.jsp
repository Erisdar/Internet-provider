<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" %>
    <meta charset="UTF-8">
    <title>Login</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="index.css">
    <script src="index.js"></script>
</head>
<body>
<div class="container w-25 m-auto">
    <div class="panel panel-login p-4">
        <div class="panel-heading">
            <div class="row d-flex justify-content-around">
                <a href="#" class="active login-form-link" id="login-form-link">Login</a>
                <a href="#" class="register-form-link" id="register-form-link">Register</a>
            </div>
            <hr>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-lg-12">
                    <form id="login-form" role="form" style="display: block;">
                        <div class="form-group">
                            <input type="text" name="username" id="username2" tabindex="1" class="form-control"
                                   placeholder="Username" value="">
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" id="password2" tabindex="2"
                                   class="form-control" placeholder="Password">
                        </div>
                        <div class="form-group text-center">
                            <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                            <label for="remember"> Remember Me</label>
                        </div>
                        <div class="form-group">
                            <div class="d-flex justify-content-center m-auto">
                                <input type="submit" name="login-submit" id="login-submit" tabindex="4"
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
                    <form id="register-form" role="form" action="registration" method = "POST" style="display: none;">
                        <div class="form-group">
                            <input type="text" name="login" id="login" tabindex="1" class="form-control"
                                   placeholder="Username" value="">
                        </div>
                        <div class="form-group">
                            <input type="email" name="email" id="email" tabindex="1" class="form-control"
                                   placeholder="Email Address" value="">
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" id="password" tabindex="2"
                                   class="form-control" placeholder="Password">
                        </div>
                        <div class="form-group">
                            <input type="password" name="confirm-password" id="confirm-password" tabindex="2"
                                   class="form-control" placeholder="Confirm Password">
                        </div>
                        <div class="form-group">
                            <div class="d-flex justify-content-center m-auto">
                                <input type="submit" name="register-submit" id="register-submit"
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