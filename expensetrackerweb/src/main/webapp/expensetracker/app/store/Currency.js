Ext.define('expensetracker.store.Currency', {
	extend : 'Ext.data.Store',
	alias : 'store.currency',
	model : 'expensetracker.model.Currency',
	storeId : 'Currency',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		batchActions : false,
		url : expensetracker.util.Url.getCurrencyTypeService(),
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