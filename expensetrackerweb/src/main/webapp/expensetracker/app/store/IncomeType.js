Ext.define('expensetracker.store.IncomeType', {
	extend : 'Ext.data.Store',
	alias : 'store.incometype',
	model : 'expensetracker.model.IncomeType',
	storeId : 'IncomeType',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		batchActions : false,
		url : expensetracker.util.Url.getIncomeTypeService(),
		reader : {
			type : 'json',
			rootProperty : 'result.any'
		},
		writer : {
			type : 'json'
		}
	},
	autoLoad : false
});