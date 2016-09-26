Ext.define('expensetracker.store.Expense', {
	extend : 'Ext.data.Store',
	alias : 'store.expense',
	storeId : 'Expense',
	model : 'expensetracker.model.Expense',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		url : expensetracker.util.Url.getExpenseService(),
		api : {
			create : expensetracker.util.Url.getExpenseService(),
			read : expensetracker.util.Url.getExpenseService(),
			update : expensetracker.util.Url.getExpenseService()
		},
		batchActions : true,
		actionMethods : {
			create : 'POST',
			read : 'GET',
			update : 'PUT'
		},
		reader : {
			type : 'json',
			rootProperty : 'result.any'
		},
		writer : {
			type : 'json',
			allowSingle : false
		},
		appendId : false
	},
	autoLoad : false
});