Ext.define('expensetracker.util.Url', {
	singleton : true,
	config : {
		login : '/expensetrackerweb/web/request/login',
		logout : '/expensetrackerweb/web/request/logout',
		session : '/expensetrackerweb/web/request/session',
		categoryService : '/expensetrackerweb/api/exptr-web/expensecategory',
		expenseService : '/expensetrackerweb/api/exptr-web/expense',
		expenseTypeService : '/expensetrackerweb/api/exptr-web/expensetype',
		expenseNameService : '/expensetrackerweb/api/exptr-web/expense/expensenames'
	},
	constructor : function(config) {
		var me = this;
		this.initConfig(config);
		return me;
	}
});