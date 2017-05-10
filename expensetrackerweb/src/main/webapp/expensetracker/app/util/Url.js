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
		queryService : '/expensetrackerweb/web/api/query',
		messageService : '/expensetrackerweb/web/api/message',
		dashboardService : '/expensetrackerweb/web/api/dashboard',
		userService : '/expensetrackerweb/web/api/user',
		userSettings : '/expensetrackerweb/web/api/user/settings',
		passwordService : '/expensetrackerweb/web/api/user/password',
		categoryService : '/expensetrackerweb/web/api/expensecategory',
		unitService : '/expensetrackerweb/web/api/expenseunits',
		expenseService : '/expensetrackerweb/web/api/expense',
		incomeService : '/expensetrackerweb/web/api/income',
		incomeTypeService : '/expensetrackerweb/web/api/application/incometype',
		expenseTypeService : '/expensetrackerweb/web/api/application/expensetype',
		currencyTypeService : '/expensetrackerweb/web/api/application/currencytype',
		dictionaryService : '/expensetrackerweb/web/api/application/dictionary?type=items',
		inventoryService : '/expensetrackerweb/web/api/inventory',
		reportingService : '/expensetrackerweb/web/api/reports',
		graphService : '/expensetrackerweb/web/api/graph'
	},
	constructor : function(config) {
		var me = this;
		this.initConfig(config);
		return me;
	}
});