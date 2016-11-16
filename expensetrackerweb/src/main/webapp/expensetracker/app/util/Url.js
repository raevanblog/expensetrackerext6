Ext.define('expensetracker.util.Url', {
	singleton : true,
	config : {
		login : '/expensetrackerweb/web/request/login',
		logout : '/expensetrackerweb/web/request/logout',
		session : '/expensetrackerweb/web/request/session',
		userNameAvailability : '/expensetrackerweb/web/request/username',
		createUser : '/expensetrackerweb/web/request/user/create',
		dashboardService : '/expensetrackerweb/api/exptr-web/dashboard',
		userService : '/expensetrackerweb/api/exptr-web/user',		
		categoryService : '/expensetrackerweb/api/exptr-web/expensecategory',
		expenseService : '/expensetrackerweb/api/exptr-web/expense',
		incomeService : '/expensetrackerweb/api/exptr-web/income',
		incomeTypeService : '/expensetrackerweb/api/exptr-web/incometype',
		expenseTypeService : '/expensetrackerweb/api/exptr-web/expensetype',
		expenseNameService : '/expensetrackerweb/api/exptr-web/expense/expensenames'
	},
	constructor : function(config) {
		var me = this;
		this.initConfig(config);
		return me;
	}
});