<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Office</title>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-resource/1.6.9/angular-resource.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.7.2/angular-cookies.js"></script>
    <script src="app/app.js"></script>
    <script src="app/login.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="style/main.css">
</head>
<body>
<jsp:include page="header.jspf">
    <jsp:param name="pageTitle" value="office"/>
</jsp:include>
<jsp:useBean id="userDao" class="com.epam.internet_provider.dao.impl.UserDaoImpl"/>
<c:set var="user" scope="session" value="${userDao.getUser(login)}"/>
<div class="balance">
    <p>Баланс:</p>
    <c:out value="Остаток счёта: ${user.cash}"/>
    <br>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#payment">Пополнить счёт
    </button>
</div>
<div class="modal fade" id="payment" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title m-auto" id="paymentModal">Belarus Bank</h5>
                <button type="button" class="close position-absolute" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <label for="inputSum">Сумма, р (минимуму: 1р, максимум: 50р):</label>
                <input type="number" min="1" max="50" required class="form-control" id="inputSum"
                       aria-describedby="sumHelp"
                       placeholder="Введите сумму пополнения">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                <button type="button" class="btn btn-primary">Подтвердить</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>