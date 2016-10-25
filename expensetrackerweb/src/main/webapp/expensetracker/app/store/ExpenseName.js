Ext.define('expensetracker.store.ExpenseName', {
	extend : 'Ext.data.Store',
	alias : 'store.expensename',
	storeId : 'ExpenseName',
	model : 'expensetracker.model.ExpenseName',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		url : expensetracker.util.Url.getExpenseNameService(),
		reader : {
			type : 'json',
			rootProperty : 'result.any'
		}
	},
	autoLoad : false
});