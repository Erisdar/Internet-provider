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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="style/main.css">
</head>
<body>
<jsp:include page="header.jspf"/>
<p class="table-caption">Тарифы</p>
<table class="tariffs-table table">
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
    <jsp:useBean id="tariffDao" class="com.epam.internet_provider.dao.impl.TariffDaoImpl"/>
    <c:forEach items="${tariffDao.tariffs}" var="tariff" varStatus="tariffLoop">
        <tr>
            <td>
                <c:out value="${tariffLoop.index+1}"/>
            </td>
            <td>
                <c:out value="${tariff.title}"/>
            </td>
            <td>
                <c:out value="${tariff.downloadSpeed}"/>/
                <c:out value="${tariff.uploadSpeed}"/>
            </td>
            <td>
                <c:out value="${tariff.traffic}"/>
            </td>
            <td>
                <c:out value="${tariff.cost}"/>
            </td>
            <td>
                <input type="button" value="Подписаться + <c:out value="${tariff.id}"/> ">
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>

</html>