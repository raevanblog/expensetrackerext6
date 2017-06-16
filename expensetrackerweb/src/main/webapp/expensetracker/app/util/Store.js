Ext.define('expensetracker.util.Store', {
	singleton : true,
	loadStaticStore : function() {
		var me = this;
		
		Ext.getStore('Calendar').load();
		Ext.getStore('ExpenseType').load();		
		Ext.getStore('IncomeType').load();
		Ext.getStore('Currency').load();
		Ext.getStore('Units').load();
		me.loadStore(Ext.getStore('Item'), { type : 'items' });
	},
	loadStore : function(store, params, callback) {
		store.load({
			params : params,
			callback : callback
		})
		return store;
	},
	createStore : function(name) {
		return Ext.create(name);
	}	
});