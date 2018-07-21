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
    <script src="app/reward.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
          integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
          integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="style/reward.css">
    <link rel="stylesheet" href="style/header.css">
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cormorant+Infant" rel="stylesheet">
</head>
<body ng-app="myApp" ng-controller="rewardCtrl">
<jsp:include page="header.jspf">
    <jsp:param name="pageTitle" value="reward"/>
</jsp:include>
<div class="rewards-container">
    <h1>Магазин</h1>
    <br>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th class="align-middle" scope="col">Изображение</th>
            <th class="align-middle" scope="col">Название</th>
            <th class="align-middle" scope="col">Количество баллов</th>
            <th class="align-middle" scope="col">Добавить</th>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="rewardDao" class="com.epam.internet_provider.dao.impl.RewardDaoImpl"/>
        <c:forEach items="${rewardDao.rewards}" var="reward" varStatus="rewardLoop">
            <tr>
                <th scope="row" class="p-0 align-middle"><img src="${reward.imgHref}" class="reward-img"
                                                              alt="${reward.title}"></th>
                <td class="align-middle p-0">${reward.title}</td>
                <td class="align-middle p-0">${reward.bonusPoints}</td>
                <td ng-click="addReward(${reward.rewardId}, '${reward.title}', ${reward.bonusPoints})"
                    class="align-middle p-0"><i
                        class="far fa-check-circle check-reward fa-5x"></i></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>