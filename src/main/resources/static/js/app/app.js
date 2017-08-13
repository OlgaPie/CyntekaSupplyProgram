var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/CyntekaSupplyProgram',
    ACCOUNT_SERVICE_API : 'http://localhost:8080/CyntekaSupplyProgram/api/account/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'AccountController',
                controllerAs:'ctrl',
                resolve: {
                    accounts: function ($q, AccountService) {
                        console.log('Load all accounts');
                        var deferred = $q.defer();
                        AccountService.loadAllAccounts().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

