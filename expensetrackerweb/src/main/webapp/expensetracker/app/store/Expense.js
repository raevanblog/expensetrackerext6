Ext.define('expensetracker.store.Expense', {
	extend : 'Ext.data.Store',
	alias : 'store.expense',
	storeId : 'Expense',
	model : 'expensetracker.model.Expense',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		url : expensetracker.util.Url.getExpenseService(),
		reader : {
			type : 'json',
			rootProperty : 'result.any'
		}
	},
	autoLoad : false
});