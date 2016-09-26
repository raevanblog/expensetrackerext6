Ext.define('expensetracker.store.ExpenseType', {
	extend : 'Ext.data.Store',
	alias : 'store.expensetype',
	model : 'expensetracker.model.ExpenseType',
	storeId : 'ExpenseType',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		batchActions : false,
		url : expensetracker.util.Url.getExpenseTypeService(),
		reader : {
			type : 'json',
			rootProperty : 'result.any'
		},
		writer : {
			type : 'json'
		}
	},
	autoLoad : true
});