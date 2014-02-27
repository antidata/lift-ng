angular.module('SnipApp', ['SnipServices1', 'SnipServices2'])
.controller('Test1Controller', ['$scope', '$window', 'snipServices1', function($scope, $window, snipServices1) {
  $scope.output1 = "";
  $scope.clickButton1a = function() {
    snipServices1.call1().then(
      function (str) {
        $scope.output1 = str;
      },
      function (err) {
        $window.alert("Something broke, and we don't know why");
      },
      function (progress) {
        // Not used
        $window.alert("Something REALLY broke, and we REALLY don't know why");
      }
    );
  };
  $scope.clickButton1b = function() {
    snipServices1.call2($scope.input1).then(
      function (str) {
        $scope.output1 = str;
      },
      function (err) {
        $window.alert("Something broke, and we don't know why");
      },
      function (progress) {
        // Not used
        $window.alert("Something REALLY broke, and we REALLY don't know why");
      }
    );
  };
  }])
.controller('Test2Controller', ['$scope', '$window', 'snipServices2', function($scope, $window, snipServices2) {
  $scope.output2a = "";
  $scope.output2b = "";
  $scope.clickButton2 = function() {
    snipServices2.call({}).then(
      function (obj) {
        $scope.output2a = obj.str1;
        $scope.output2b = obj.str2;
      },
      function (err) {
        $window.alert("Something broke, and we don't know why");
      },
      function (progress) {
        // Not used
        $window.alert("Something REALLY broke, and we REALLY don't know why");
      }
    );
  };
}]);