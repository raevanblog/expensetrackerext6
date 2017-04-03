Ext.define('expensetracker.store.Units', {
	extend : 'Ext.data.Store',
	alias : 'store.units',
	storeId : 'Units',
	model : 'expensetracker.model.Units',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		api : {
			create : expensetracker.util.Url.getUnitService(),
			read : expensetracker.util.Url.getUnitService(),
			update : expensetracker.util.Url.getUnitService(),
			destroy : expensetracker.util.Url.getUnitService()
		},
		batchActions : true,
		actionMethods : {
			create : 'POST',
			read : 'GET',
			update : 'PUT',
			destroy : 'DELETE'
		},
		reader : {
			type : 'json',
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