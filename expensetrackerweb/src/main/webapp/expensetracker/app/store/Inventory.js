Ext.define('expensetracker.store.Inventory', {
	extend : 'Ext.data.Store',
	alias : 'store.inventory',
	storeId : 'Inventory',
	model : 'expensetracker.model.Inventory',
	proxy : {
		type : 'rest',
		batchActions : true,
		useDefaultXhrHeader : false,
		api : {
			create : expensetracker.util.Url.getInventoryService(),
			read : expensetracker.util.Url.getInventoryService(),
			update : expensetracker.util.Url.getInventoryService(),
			destroy : expensetracker.util.Url.getInventoryService()
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