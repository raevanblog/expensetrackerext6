Ext.define('expensetracker.store.Query', {
	extend : 'Ext.data.Store',
	alias : 'store.query',
	storeId : 'Query',
	model : 'expensetracker.model.Message',
	proxy : {
		type : 'rest',
		batchActions : true,
		useDefaultXhrHeader : false,
		api : {
			create : expensetracker.util.Url.getQueryService(),
			read : expensetracker.util.Url.getQueryService(),			
			destroy : expensetracker.util.Url.getQueryService()
		},
		actionMethods : {
			create : 'POST',
			read : 'GET',			
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