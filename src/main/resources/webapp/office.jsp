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
    <script src="app/office.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
          integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/office.css">
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cormorant+Infant" rel="stylesheet">
</head>
<body ng-app="myApp" ng-controller="officeCtrl">
<jsp:include page="header.jspf">
    <jsp:param name="pageTitle" value="office"/>
</jsp:include>
<p class="user-name"> Привет, {{login}}</p>
<img class="lis-img" src="img/lis.png" alt="lis">
<div class="user-status" ng-cloak>
    <span>Статус: <span class="main-info ">{{user.status}}</span></span>
</div>
<div class="balance" ng-cloak>
    <p>Состояние счёта</p>
    <span>Текущий баланс: <span class="main-info ">{{user.cash}}р.</span><br></span>
    <span>Бонусы: <span class="main-info ">{{user.bonusAmount}}</span> баллов</span><br>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#payment">Пополнить счёт
    </button>
</div>
<div class="tariff-info" ng-if="user.tariff.title" ng-cloak>
    <span>Подключённый тариф: <span class="main-info ">{{user.tariff.title}}</span></span><br>
    <span>Скорость приёма/передачи: <span class="main-info ">{{user.tariff.downloadSpeed}}/{{user.tariff.uploadSpeed}} Мбит/с</span></span><br>
    <span ng-if="user.tariff.traffic == 0">Траффик: <span class="main-info">Безлимит</span><br></span>
    <span ng-if="user.tariff.traffic != 0">Траффик: <span
            class="main-info ">{{user.tariff.traffic}} ГБ</span><br></span>
    <span>Стоимость: <span class="main-info ">{{user.tariff.cost}} р.</span><br></span>
    <button class="btn btn-secondary" ng-click="deactivate()">Отключить услугу</button>
</div>
<div class="tariff-info" ng-if="!user.tariff.title" ng-cloak>
    <p>Тариф не подключён</p>
    <a href="tariffs.jsp" target="_self">Выбрать тариф</a>
</div>
<div class="carousel-container">
    <div id="carouselIndicators" class="carousel slide" data-ride="carousel" data-interval="3000000">
        <div class="carousel-inner">
            <jsp:useBean id="rewardDao" class="com.epam.internet_provider.dao.impl.RewardDaoImpl"/>
            <c:set var="rewards" value="${rewardDao.rewards}"/>
            <c:forEach items="${rewards}" var="reward" varStatus="rewardLoop">
                <div class="carousel-item" ng-class="{'active': ${rewardLoop.index} == 0}">
                    <a class="img-container" href="reward.jsp" target="_self">
                        <img src="${reward.imgHref}" alt="${reward.title}"></a>
                    <div class="carousel-caption d-none d-md-block">
                        <h3>${reward.title}</h3>
                        <span>${reward.bonusPoints} баллов</span>
                    </div>
                </div>
            </c:forEach>
        </div>
        <ol class="carousel-indicators">
            <c:forEach items="${rewards}" var="reward" varStatus="rewardLoop">
                <li data-target="#carouselIndicators" data-slide-to="${rewardLoop.index}"
                    ng-class="{'active': ${rewardLoop.index} == 0}"></li>
            </c:forEach>
        </ol>
        <a class="carousel-control-prev" href="#carouselIndicators" role="button" data-slide="prev">
            <i class="fas fa-angle-left fa-5x" aria-hidden="true"></i>
        </a>
        <a class="carousel-control-next" href="#carouselIndicators" role="button" data-slide="next">
            <i class="fas fa-angle-right fa-5x"></i>
        </a>
    </div>
    <div class="reward-history-container">
        <button type="button" class="btn" data-toggle="modal" data-target="#rewardModal">
            История покупок
        </button>
    </div>
</div>
<div class="modal fade modal-payment" id="payment" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form ng-submit="pay()">
                <div class="modal-header">
                    <h4 class="modal-title m-auto" id="paymentModal">Belarus Bank</h4>
                    <button type="button" class="close position-absolute" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <label for="inputSum">Сумма, р (минимуму: 1р, максимум: 50р):</label>
                    <input type="number" name="cash" min="1" max="50" required class="form-control" id="inputSum"
                           ng-model="cash" aria-describedby="sumHelp" placeholder="Введите сумму пополнения">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary fa fa-times" data-dismiss="modal"> Отмена</button>
                    <input type="submit" class="btn btn-primary" value="Пополнить"/>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade reward-modal text-center" id="rewardModal" tabindex="-1" role="dialog"
     aria-labelledby="rewardModalTitle"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content" ng-init="total">
            <div class="modal-header d-flex justify-content-center">
                <h5 class="modal-title" id="rewardModalTitle">Совершённые покупки</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div ng-repeat="reward in user.rewards" ng-init="$parent.total = $parent.total + reward.bonusPoints">
                    <h4>{{reward.title}}</h4>
                    <img class="rounded mx-auto d-block" ng-src="{{reward.imgHref}}" alt="{{reward.title}}">
                    <h6>{{reward.bonusPoints}} баллов</h6>
                    <hr ng-if="!$last">
                </div>
            </div>
            <div class="modal-footer d-flex justify-content-between" ng-init="total = 0">
                <h5 class="total-info">Всего потрачено {{total}} баллов</h5>
                <i class="fa fa-times"></i>
                <button type="button" class="btn btn-secondary btn-close" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>