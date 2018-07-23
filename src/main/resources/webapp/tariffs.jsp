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
<div class="create-tariff" ng-if="user.role == 'Admin'" data-toggle="modal" data-target="#createTariffModal" ng-cloak>
    Создать
    новый тариф
</div>
<div class="table-container">
    <p class="table-caption">Тарифы</p>
    <table class="tariffs-table table" ng-cloak>
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
            <th ng-if="user.role == 'Admin'" colspan="2">Действие</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="tariff in tariffs">
            <td>
                {{$index + 1}}
            </td>
            <td>
                {{tariff.title}}
            </td>
            <td>
                {{tariff.downloadSpeed}}/
                {{tariff.uploadSpeed}}
            </td>
            <td>
                <div ng-if="tariff.traffic == 0">
                    Безлимит
                </div>
                <div ng-if="tariff.traffic != 0">
                    {{tariff.traffic}}
                </div>
            </td>
            <td>
                {{tariff.cost}}
            </td>
            <td>
                <button ng-click="activate(tariff.id, tariff.cost, tariff.title)"
                        ng-disabled="user.tariff.id == tariff.id || user.status == 'Blocked'"
                        ng-class="{'current-tariff btn-info' : user.tariff.id == tariff.id,
                        'not-current-tariff btn-dark' : user.tariff.id != tariff.id}"
                        type="button" class="btn btn-lg">
                </button>
            </td>
            <td ng-if="user.role == 'Admin'">
                <button class="btn btn-lg" data-toggle="modal" ng-click="runChangeModal(tariff.title,
                        tariff.downloadSpeed, tariff.uploadSpeed, tariff.traffic, tariff.cost)">
                    Изменить
                </button>
            </td>
            <td ng-if="user.role == 'Admin'">
                <button class="btn btn-lg" ng-click="deleteTariff(tariff.id)">Удалить</button>
            </td>
        </tr>
        </tbody>
    </table>
    <h3 ng-if="user.status == 'Blocked'" ng-cloak>Пополните баланс для продолжения использования наших услуг</h3>
</div>
<!-- Create Modal -->
<div class="modal fade" ng-cloak ng-if="user.role == 'Admin'" id="createTariffModal" tabindex="-1" role="dialog"
     aria-labelledby="createTariffModal"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <form ng-submit="createTariff(title, downloadSpeed, uploadSpeed, traffic, cost)">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="createTariffModalLabel">Создать тариф</h5>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <label for="inputTitle" class="col-auto col-form-label">Название</label>
                        <div class="col-auto">
                            <input type="text" ng-model="title" class="form-control" id="inputTitle"
                                   placeholder="Название">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="downloadSpeed" class="col-auto col-form-label">Скорость приёма, Мбит/с</label>
                        <div class="col-auto">
                            <input type="number" ng-model="downloadSpeed" class="form-control" id="downloadSpeed"
                                   placeholder="Скорость приёма">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="uploadSpeed" class="col-auto col-form-label">Скорость передачи, Мбит/с</label>
                        <div class="col-auto">
                            <input type="number" ng-model="uploadSpeed" class="form-control" id="uploadSpeed"
                                   placeholder="Скорость передачи">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="traffic" class="col-auto col-form-label">Траффик, ГБ</label>
                        <div class="col-auto">
                            <input type="number" ng-model="traffic" class="form-control" id="traffic"
                                   placeholder="Траффик">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="cost" class="col-auto col-form-label">Стоимость, р</label>
                        <div class="col-auto">
                            <input type="number" ng-model="cost" class="form-control" id="cost"
                                   placeholder="Стоимость">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- Change Modal -->
<div class="modal fade" ng-cloak ng-if="user.role == 'Admin'" id="changeTariffModal" tabindex="-1" role="dialog"
     aria-labelledby="changeTariffModal"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <form ng-submit="changeTariff(changingTitle, changingDownloadSpeed, changingUploadSpeed,
        changingTraffic, changingCost)">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="changeTariffModalLabel">Изменить тариф {{currentTariffTitle}}</h5>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <label class="col-auto col-form-label">Название</label>
                        <div class="col-auto">
                            <input type="text" id="changingTitle"
                                   ng-model="changingTitle" ng-model-options="updateOn: 'default"
                                   class="form-control"
                                   placeholder="Название" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-auto col-form-label">Скорость приёма, Мбит/с</label>
                        <div class="col-auto">
                            <input type="number" id="changingDownloadSpeed" ng-model="changingDownloadSpeed"
                                   class="form-control"
                                   placeholder="Скорость приёма" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-auto col-form-label">Скорость передачи, Мбит/с</label>
                        <div class="col-auto">
                            <input type="number" id="changingUploadSpeed" ng-model="changingUploadSpeed"
                                   class="form-control"
                                   placeholder="Скорость передачи" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-auto col-form-label">Траффик, ГБ</label>
                        <div class="col-auto">
                            <input type="number" id="changingTraffic" ng-model="changingTraffic" class="form-control"
                                   placeholder="Траффик" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-auto col-form-label">Стоимость, р</label>
                        <div class="col-auto">
                            <input type="number" id="changingCost" ng-model="changingCost" class="form-control"
                                   placeholder="Стоимость" required>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>