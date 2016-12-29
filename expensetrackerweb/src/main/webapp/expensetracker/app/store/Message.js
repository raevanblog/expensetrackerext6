Ext.define('expensetracker.store.Message', {
	extend : 'Ext.data.Store',
	alias : 'store.message',
	storeId : 'Message',
	model : 'expensetracker.model.Message',
	proxy : {
		type : 'rest',
		batchActions : true,
		useDefaultXhrHeader : false,
		api : {
			create : expensetracker.util.Url.getMessageService(),
			read : expensetracker.util.Url.getMessageService(),			
			destroy : expensetracker.util.Url.getMessageService()
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