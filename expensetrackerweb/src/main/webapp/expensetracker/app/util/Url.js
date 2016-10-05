Ext.define('expensetracker.util.Url', {
	singleton : true,
	config : {
		loginService : '/expensetrackerweb/web/request/login',
		sessionService : '/expensetrackerweb/web/request/session',
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