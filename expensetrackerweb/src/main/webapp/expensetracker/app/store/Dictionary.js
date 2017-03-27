Ext.define('expensetracker.store.Dictionary', {
	extend : 'Ext.data.Store',
	alias : 'store.dictionary',
	storeId : 'Dictionary',
	model : 'expensetracker.model.Dictionary',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		url : expensetracker.util.Url.getDictionaryService(),
		reader : {
			type : 'json',
			rootProperty : 'result.any'
		}
	},
	autoLoad : false
});