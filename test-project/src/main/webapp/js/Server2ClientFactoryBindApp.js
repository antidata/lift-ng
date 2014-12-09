angular.module('Server2ClientFactoryBindingApp', ['lift-ng', 'S2cBindServices', 'S2cCountFactoryBind'])
.controller('Controller', ['$scope', '$window', 'counterService', 'arrSvc', 's2cCountFactory', function($scope, $window, counterService, arrSvc, s2cCount) {
  $scope.counter = counterService;
  $scope.arrSvc  = arrSvc;
  $scope.count = s2cCount.val;
}]);
