'use strict';

angular.module('crudApp').factory('AccountService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllAccounts: loadAllAccounts,
                getAllAccounts: getAllAccounts,
                getAccount: getAccount,
                createAccount: createAccount,
                updateAccount: updateAccount,
                removeAccount: removeAccount
            };

            return factory;

            function loadAllAccounts() {
                console.log('Fetching all accounts');
                var deferred = $q.defer();
                $http.get(urls.ACCOUNT_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all accounts');
                            $localStorage.accounts = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading accounts');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllAccounts(){
                return $localStorage.accounts;
            }

            function getAccount(id) {
                console.log('Fetching Account with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.ACCOUNT_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Account with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading account with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createAccount(account) {
                console.log('Creating Account');
                var deferred = $q.defer();
                $http.post(urls.ACCOUNT_SERVICE_API, account)
                    .then(
                        function (response) {
                            loadAllAccounts();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Account : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateAccount(account, id) {
                console.log('Updating Account with id '+id);
                var deferred = $q.defer();
                $http.put(urls.ACCOUNT_SERVICE_API + id, account)
                    .then(
                        function (response) {
                            loadAllAccounts();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Account with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeAccount(id) {
                console.log('Removing Account with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.ACCOUNT_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllAccounts();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Account with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);