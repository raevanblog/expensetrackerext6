Ext.define('expensetracker.store.Graph', {
	extend : 'Ext.data.Store',
	alias : 'store.graph',
	model : 'expensetracker.model.Graph',
	storeId : 'Graph',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		batchActions : false,
		url : expensetracker.util.Url.getGraphService(),
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