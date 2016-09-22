Ext.define('expensetracker.store.ExpenseCategory', {
	extend : 'Ext.data.Store',
	alias : 'store.expensecategory',
	storeId : 'ExpenseCategory',
	model : 'expensetracker.model.ExpenseCategory',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		batchActions : false,
		url: expensetracker.util.Url.getCategoryService(),
		reader : {
			type : 'json',
			rootProperty : 'result.any'
		},
		writer : {
			type: 'json'			
		}
	},
	autoLoad : true
});