Ext.define('expensetracker.util.Store', {
	singleton : true,
	loadStaticStore : function() {
		Ext.getStore('ExpenseSheet').load();
		Ext.getStore('ExpenseType').load();
		Ext.getStore('ExpenseName').load();
		Ext.getStore('IncomeType').load();
	},
	loadStore : function(store, params, callback) {
		store.load({
			params : params,
			callback : callback
		})
	},
	createStore : function(name) {
		return Ext.create(name);
	}	
});