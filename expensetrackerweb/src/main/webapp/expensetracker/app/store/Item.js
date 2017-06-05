Ext.define('expensetracker.store.Item', {
	extend : 'Ext.data.Store',
	alias : 'store.item',
	storeId : 'Item',
	model : 'expensetracker.model.Item',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		url : expensetracker.util.Url.getItemService(),
		reader : {
			type : 'json',
			rootProperty : 'result.any'
		}
	},
	autoLoad : false
});