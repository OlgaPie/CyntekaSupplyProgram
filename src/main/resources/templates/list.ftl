<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Счета </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="accountForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.account.id" />

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="account">номер счета</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.account.account" id="account" class="accountname form-control input-sm" placeholder="введите номер счета" required ng-minlength="1"/>
                            </div>
                        </div>
                    </div>

	                 <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="project">проект</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.account.project" id="project" class="projectname form-control input-sm" placeholder="введите название проекта" required ng-minlength="1"/>
                        </div>
                    </div>
                </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="supplier">поставщик</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.account.supplier" id="supplier" class="suppliername form-control input-sm" placeholder="введите поставщика" required ng-minlength="1"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="payer">плательщик</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.account.payer" id="payer" class="payername form-control input-sm" placeholder="введите плательщика" required ng-minlength="1"/>
                            </div>
                        </div>
                    </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="sum">сумма</label>

                            <div class="col-md-7 form-group" ng-class="{'has-error': accountForm.myDecimal.$invalid}">
                                <div class="col-sm-10">
                                    <input type="number" class="form-control input-sm form-control" name="input-sum" placeholder="введите сумму в формате 0.00"
										   ng-model="ctrl.account.sum" ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />

                                </div>
                            </div>

	                    </div>
	                </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="status">статус</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.account.status" id="status" class="form-control input-sm" placeholder="введите статус" required ng-minlength="1"/>
                            </div>
                        </div>
                    </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.account.id ? 'Добавить' : 'Обновить'}}" class="btn btn-primary btn-sm" ng-disabled="accountForm.$invalid || accountForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="accountForm.$pristine">Cбросить</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Счета:</span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>идентификатор</th>
		                <th>номер счета</th>
		                <th>проект</th>
		                <th>поставщик</th>
		                <th>плательщик</th>
		                <th>сумма</th>
		                <th>статус</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllAccounts()">
		                <td>{{u.id}}</td>
		                <td>{{u.account}}</td>
		                <td>{{u.project}}</td>
		                <td>{{u.supplier}}</td>
		                <td>{{u.payer}}</td>
		                <td>{{u.sum}}</td>
		                <td>{{u.status}}</td>
		                <td><button type="button" ng-click="ctrl.editAccount(u.id)" class="btn btn-success">редактировать</button></td>
		                <td><button type="button" ng-click="ctrl.removeAccount(u.id)" class="btn btn-danger custom-width">удалить</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>