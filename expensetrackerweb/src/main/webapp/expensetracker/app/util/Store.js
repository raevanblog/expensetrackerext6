Ext.define('expensetracker.util.Store', {
	singleton : true,
	loadStaticStore : function() {
		Ext.getStore('Thumbnail').load();
		Ext.getStore('ExpenseType').load();
		Ext.getStore('ExpenseName').load();
		Ext.getStore('IncomeType').load();
		Ext.getStore('Currency').load();
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