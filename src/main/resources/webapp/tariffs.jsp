<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tariffs</title>
    <meta charset="utf-8">
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
    <script src="app/app.js"></script>
    <script src="app/login.js"></script>
    <script src="app/tariff.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
          integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="style/tariffs.css">
    <link rel="stylesheet" href="style/header.css">
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cormorant+Infant" rel="stylesheet">
</head>
<body ng-app="myApp" ng-controller="tariffCtrl">
<jsp:include page="header.jspf">
    <jsp:param name="pageTitle" value="tariffs"/>
</jsp:include>
<div class="table-container">
    <p class="table-caption">Тарифы</p>
    <table class="tariffs-table table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Название</th>
            <th scope="col">Скорость приёма/передачи,
                <br> Мбит/с
            </th>
            <th scope="col">Количество
                <br> траффика, ГБ
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
                    <c:if test="${tariff.traffic == 0}">
                        <c:out value="Безлимит"/>
                    </c:if>
                    <c:if test="${tariff.traffic != 0}">
                        <c:out value="${tariff.traffic}"/>
                    </c:if>
                </td>
                <td>
                    <c:out value="${tariff.cost}"/>
                </td>
                <td>
                    <button ng-click="activate(${tariff.id}, ${tariff.cost}, '${tariff.title}')"
                            ng-disabled="user.tariff.id == ${tariff.id} || user.status == 'Blocked'"
                            ng-class="{'current-tariff btn-info' : user.tariff.id == ${tariff.id},
                        'not-current-tariff btn-dark' : user.tariff.id != ${tariff.id}}"
                            type="button" class="btn btn-lg">
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h3 ng-if="user.status == 'Blocked'" ng-cloak>Пополните баланс для продолжения использования наших услуг</h3>
</div>
</body>
</html>