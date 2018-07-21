var app = angular.module("myApp");

app.controller("tariffCtrl", function ($scope, $http) {

    let getUser = function () {
        $http.get("/user")
            .then(function (response) {
                $scope.user = response.data
            }, function () {
                alert("Server is not responding")
            })
    };

    $scope.user = null;

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
                    $http.put("/tariff", {tariff_id: id}, {headers: {'Content-Type': 'application/json'}})
                        .then(function () {
                            getUser();
                        }, function () {
                            alert("You are Blocked! Ha-ha!")
                        });
                }
            }
        });
    };

    angular.element(document).ready(function () {
        getUser();
    });


})
;