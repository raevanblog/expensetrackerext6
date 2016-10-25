Ext.define('expensetracker.store.Income', {
	extend : 'Ext.data.Store',
	alias : 'store.income',
	storeId : 'Income',
	model : 'expensetracker.model.Income',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		api : {
			create : expensetracker.util.Url.getIncomeService(),
			read : expensetracker.util.Url.getIncomeService(),
			update : expensetracker.util.Url.getIncomeService(),
			destroy : expensetracker.util.Url.getIncomeService()
		},
		actionMethods : {
			create : 'POST',
			read : 'GET',
			update : 'PUT',
			destroy : 'DELETE'
		},
		reader : {
			type : 'json',
			messageProperty : 'exception',
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