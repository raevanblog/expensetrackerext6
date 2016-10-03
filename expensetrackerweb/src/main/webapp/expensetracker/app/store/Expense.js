Ext.define('expensetracker.store.Expense', {
	extend : 'Ext.data.Store',
	alias : 'store.expense',
	storeId : 'Expense',
	model : 'expensetracker.model.Expense',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		api : {
			create : expensetracker.util.Url.getExpenseService(),
			read : expensetracker.util.Url.getExpenseService(),
			update : expensetracker.util.Url.getExpenseService(),
			destroy : expensetracker.util.Url.getExpenseService()
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
	autoLoad : false
});