var app = angular.module("myApp");

app.controller("rewardCtrl", function ($scope, $http) {
    $scope.addReward = function (id, title, bonusPoints) {
        bootbox.confirm({
            title: "Подтвердить покупку " + title + "?",
            className: 'reward-confirm',
            closeButton: false,
            message: "Будет списано " + bonusPoints + " баллов",
            buttons: {
                cancel: {
                    className: 'confirm-cancel',
                    label: '<i class="fa fa-times"></i> Отмена'
                },
                confirm: {
                    className: 'btn-info',
                    label: '<i class="fa fa-check"></i> Подтвердить'
                }
            },
            callback: function (result) {
                if (result) {
                    $http.post("/reward", {rewardId: id}, {headers: {'Content-Type': 'application/json'}})
                        .then(function () {
                            getUser();
                        }, function () {
                            alert("Not enough bonus points!")
                        });
                }
            }
        });
    };
});