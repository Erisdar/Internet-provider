<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tariffs</title>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="style/main.css">
    <script src="app/app.js"></script>
</head>

<body>
<nav class="nav flex-column nav-pills  text-center navbar-dark bg-dark">
    <div>
        <a class="nav-item nav-link" href="main.jsp">Главная</a>
        <a class="nav-item nav-link active" href="#">Тарифы</a>
        <a class="nav-item nav-link" href="#">Личный кабинет</a>
        <a class="nav-item nav-link" href="#">Бонусная программма</a>
        <a class="nav-item nav-link" href="#">Новости</a>
        <a class="nav-item nav-link" href="#">Настройки</a>
        <a class="nav-item nav-link" href="#">О нас</a>
        <a class="nav-item nav-link" href="#">Выход</a>
    </div>
</nav>
<p class="table-caption">Тарифы</p>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Название</th>
        <th scope="col">Скорость приёма/передачи,
            <br> Мбит/с
        </th>
        <th scope="col">Количество траффика,
            <br> ГБ
        </th>
        <th scope="col">Стоимость,
            <br> руб
        </th>
        <th scope="col">Статус</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="tariffDao" class="com.epam.internet_provider.dao.TariffDao" scope="request"/>
    <%--<c:forEach items="${tariffDao.getTariffs()}" var="tariff">--%>
    <%--<tr>--%>
    <%--<td>--%>
    <%--<c:out value="#"/>--%>
    <%--</td>--%>
    <%--<td>--%>
    <%--<c:out value="${tariff.getTitle()}"/>--%>
    <%--</td>--%>
    <%--<td>--%>
    <%--<c:out value="${tariff.downloadSpeed}"/>/--%>
    <%--<c:out value="${tariff.uploadSpeed}"/>--%>
    <%--</td>--%>
    <%--<td>--%>
    <%--<c:out value="${tariff.traffic}"/>--%>
    <%--</td>--%>
    <%--<td>--%>
    <%--<c:out value="${tariff.cost}"/>--%>
    <%--</td>--%>
    <%--<td>--%>
    <%--<input type="button" value="Подписаться">--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--</c:forEach>--%>
    </tbody>
</table>
</body>

</html>