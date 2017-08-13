'use strict';

angular.module('crudApp').controller('AccountController',
    ['AccountService', '$scope',  function( AccountService, $scope) {

        var self = this;
        self.account = {};
        self.accounts=[];

        self.submit = submit;
        self.getAllAccounts = getAllAccounts;
        self.createAccount = createAccount;
        self.updateAccount = updateAccount;
        self.removeAccount = removeAccount;
        self.editAccount = editAccount;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.account.id === undefined || self.account.id === null) {
                console.log('Saving New Account', self.account);
                createAccount(self.account);
            } else {
                updateAccount(self.account, self.account.id);
                console.log('Account updated with id ', self.account.id);
            }
        }

        function createAccount(account) {
            AccountService.createAccount(account)
                .then(
                    function (response) {
                        self.successMessage = 'Запись успешно добавлена';
                        self.errorMessage='';
                        self.done = true;
                        self.account={};
                        $scope.accountForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Account');
                        self.errorMessage = 'Error while creating Account: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateAccount(account, id){
            console.log('About to update account');
            AccountService.updateAccount(account, id)
                .then(
                    function (response){
                        console.log('Account updated successfully');
                        self.successMessage='Успешно обновлено';
                        self.errorMessage='';
                        self.done = true;
                        $scope.accountForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Account');
                        self.errorMessage='Error while updating Account '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeAccount(id){
            console.log('About to remove Account with id '+id);
            AccountService.removeAccount(id)
                .then(
                    function(){
                        console.log('Account '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing account '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllAccounts(){
            return AccountService.getAllAccounts();
        }

        function editAccount(id) {
            self.successMessage='';
            self.errorMessage='';
            AccountService.getAccount(id).then(
                function (account) {
                    self.account = account;
                },
                function (errResponse) {
                    console.error('Error while removing account ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.account={};
            $scope.accountForm.$setPristine(); //reset Form
        }
    }


    ]);