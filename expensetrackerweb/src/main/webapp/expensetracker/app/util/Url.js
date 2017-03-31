Ext.define('expensetracker.util.Url', {
	singleton : true,
	config : {
		login : '/expensetrackerweb/web/request/login',
		logout : '/expensetrackerweb/web/request/logout',
		session : '/expensetrackerweb/web/request/session',
		checkAvailability : '/expensetrackerweb/web/request/checkAvailability',
		createUser : '/expensetrackerweb/web/request/user/create',
		activateUser : '/expensetrackerweb/web/request/user/activate',
		activationMail : '/expensetrackerweb/web/request/user/email/activate',
		query : '/expensetrackerweb/web/request/query',
		queryService : '/expensetrackerweb/api/exptr-web/query',
		messageService : '/expensetrackerweb/api/exptr-web/message',
		dashboardService : '/expensetrackerweb/api/exptr-web/dashboard',
		userService : '/expensetrackerweb/api/exptr-web/user',
		userSettings : '/expensetrackerweb/api/exptr-web/user/settings',
		passwordService : '/expensetrackerweb/api/exptr-web/user/password',
		categoryService : '/expensetrackerweb/api/exptr-web/expensecategory',
		expenseService : '/expensetrackerweb/api/exptr-web/expense',
		incomeService : '/expensetrackerweb/api/exptr-web/income',
		incomeTypeService : '/expensetrackerweb/api/exptr-web/application/incometype',
		expenseTypeService : '/expensetrackerweb/api/exptr-web/application/expensetype',
		currencyTypeService : '/expensetrackerweb/api/exptr-web/application/currencytype',
		dictionaryService : '/expensetrackerweb/api/exptr-web/application/dictionary?type=items',
		inventoryService : '/expensetrackerweb/api/exptr-web/inventory',		
		reportingService : '/expensetrackerweb/api/exptr-web/reports',
		graphService : '/expensetrackerweb/api/exptr-web/graph'
	},
	constructor : function(config) {
		var me = this;
		this.initConfig(config);
		return me;
	}
});