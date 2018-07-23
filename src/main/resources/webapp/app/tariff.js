var app = angular.module("myApp");

app.controller("tariffCtrl", function ($scope, $http, $window) {

    $scope.user = null;
    $scope.tariffs = null;

    let getUser = function () {
        $http.get("/user")
            .then(function (response) {
                $scope.user = response.data
            }, function () {
                alert("Server is not responding")
            })
    };

    let getTarrifs = function () {
        $http.get("/tariff")
            .then(function (response) {
                $scope.tariffs = response.data
            }, function () {
                alert("Server is not responding")
            })
    };

    $scope.activate = function (id, cost, title) {
        bootbox.confirm({
            title: "Подключить тариф " + title + "?",
            className: 'tariff-confirm',
            closeButton: false,
            message: "С вашего счёта будет списано " + cost + " рублей",
            buttons: {
                cancel: {
                    className: 'confirm-cancel',
                    label: '<i class="fa fa-times"></i> Отмена'
                },
                confirm: {
                    className: 'btn-info',
                    label: '<i class="fa fa-check"></i> Подключить'
                }
            },
            callback: function (result) {
                if (result) {
                    $http.put("/user", {tariff_id: id}, {headers: {'Content-Type': 'application/json'}})
                        .then(function () {
                            getUser();
                        }, function () {
                            alert("You are Blocked! Ha-ha!")
                        });
                }
            }
        });
    };

    $scope.runChangeModal = function (title, downloadSpeed, uploadSpeed, traffic, cost) {
        $scope.currentTariffTitle = title;
        $scope.changingTitle = title;
        $scope.changingDownloadSpeed = downloadSpeed;
        $scope.changingUploadSpeed = uploadSpeed;
        $scope.changingTraffic = traffic;
        $scope.changingCost = cost;
        $('#changeTariffModal').modal('show');
    };

    $scope.createTariff = function (title, downloadSpeed, uploadSpeed, traffic, cost) {
        let tariff = {
            title: title,
            downloadSpeed: downloadSpeed,
            uploadSpeed: uploadSpeed,
            traffic: traffic,
            cost: cost
        };
        $http.post('/tariff', tariff, {headers: {'Content-Type': 'application/json'}})
            .then(function () {
                $('#createTariffModal').modal('hide')
                getTarrifs();
            }, function () {
                alert("Тариф " + title + " уже существует");
            })
    };

    $scope.changeTariff = function (title, downloadSpeed, uploadSpeed, traffic, cost) {
        let tariff = {
            title: title,
            downloadSpeed: downloadSpeed,
            uploadSpeed: uploadSpeed,
            traffic: traffic,
            cost: cost
        };
        $http.put("/tariff", tariff, {headers: {'Content-Type': 'application/json'}})
            .then(function () {
                getTarrifs();
                $('#changeTariffModal').modal('hide')
            }, function () {
                alert("Server is not responding")
            });
    };

    $scope.deleteTariff = function (id) {
        bootbox.confirm({
            title: "Удалить тариф?",
            className: 'tariff-confirm',
            closeButton: false,
            message: "Вернуть это изменение будет невозможно.",
            buttons: {
                cancel: {
                    label: '<i class="fa fa-times"></i> Отмена'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> Подтвердить'
                }
            },
            callback: function (result) {
                if (result) {
                    $http.delete('/tariff', {params: {tariff_id: id}}, {headers: {'Content-Type': 'application/json'}})
                        .then(
                            function () {
                                getTarrifs();
                            },
                            function (error) {
                                alert("Ошибка:" + error)
                            });
                }
            }
        });
    };

    angular.element(document).ready(function () {
        getUser();
        getTarrifs();
    });
});