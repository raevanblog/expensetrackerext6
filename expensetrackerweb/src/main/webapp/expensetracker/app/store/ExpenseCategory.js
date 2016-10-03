Ext.define('expensetracker.store.ExpenseCategory', {
	extend : 'Ext.data.Store',
	alias : 'store.expensecategory',
	storeId : 'ExpenseCategory',
	model : 'expensetracker.model.ExpenseCategory',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		batchActions : false,
		api : {
			create : expensetracker.util.Url.getCategoryService(),
			read : expensetracker.util.Url.getCategoryService(),
			update : expensetracker.util.Url.getCategoryService(),
			destroy : expensetracker.util.Url.getCategoryService()
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
		listeners : {
			exception : function(proxy, response, operation) {
				var response = Ext.JSON.decode(operation.getError().response.responseText);
				Ext.MessageBox.show({
					title : 'ExpenseTracker',
					msg : response.status_Message,
					icon : Ext.MessageBox.ERROR,
					buttons : Ext.Msg.OK
				});
			}
		},
		writer : {
			type : 'json',
			allowSingle : false
		},
		appendId : false
	},
	autoLoad : true
});