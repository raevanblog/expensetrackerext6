Ext.define('expensetracker.store.ExpenseCategory', {
	extend : 'Ext.data.Store',
	alias : 'store.expensecategory',
	storeId : 'ExpenseCategory',
	model : 'expensetracker.model.ExpenseCategory',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		api : {
			create : expensetracker.util.Url.getCategoryService(),
			read : expensetracker.util.Url.getCategoryService(),
			update : expensetracker.util.Url.getCategoryService(),
			destroy : expensetracker.util.Url.getCategoryService()
		},
		batchActions : true,
		actionMethods : {
			create : 'POST',
			read : 'GET',
			update : 'PUT',
			destroy : 'DELETE'
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