Ext.define('expensetracker.util.Url', {
	singleton : true,
	config : {
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