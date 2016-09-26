Ext.define('expensetracker.util.Url', {
	singleton : true,
	config : {
		categoryService : 'http://localhost:9998/exptr-web-api/expensecategory',
		expenseService : 'http://localhost:9998/exptr-web-api/expense',
		expenseTypeService : 'http://localhost:9998/exptr-web-api/expensetype'
	},
	constructor : function(config) {
		var me = this;
		this.initConfig(config);
		return me;
	}
});