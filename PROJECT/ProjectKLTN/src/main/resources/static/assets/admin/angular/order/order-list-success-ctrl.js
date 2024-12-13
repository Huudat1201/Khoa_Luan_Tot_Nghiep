angular.module("order-success-app", ["order-success-app.controllers", "datatables"]);
angular
  .module("order-success-app.controllers", [])
  .controller(
    "order-success-ctrl",
    function (
      $scope,
      DTOptionsBuilder,
      DTColumnBuilder,
      DTColumnDefBuilder,
      $http
    ) {
      $scope.items = [];

      $scope.info = {};
      $scope.initialize = function () {
        $http.get("/rest/order/success").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

      $scope.formDetail = [];
      $scope.modalDetail = function (detail) {
        $http.get("/rest/order/pending/" + detail.id).then((resp) => {
          $scope.formDetail = resp.data;
          
          // Thêm đoạn mã JavaScript để so sánh và đưa ra giá trị phù hợp
          if ($scope.formDetail.province === 'Hồ Chí Minh') {
            $scope.shippingFee = '18,000';
          } else {
            $scope.shippingFee = '30,000';
          }
        });
        $("#modalDetail").modal("show");
      };

      $scope.formCancel = {};
      $scope.showModal = function (item) {
        $scope.formCancel = item;
        $("#modal").modal("show");
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(6).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
