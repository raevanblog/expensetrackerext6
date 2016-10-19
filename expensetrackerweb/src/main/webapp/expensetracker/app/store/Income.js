Ext.define('expensetracker.store.Income', {
	extend : 'Ext.data.Store',
	alias : 'store.income',
	storeId : 'Income',
	model : 'expensetracker.model.Income',
	proxy : {
		type : 'rest',
		useDefaultXhrHeader : false,
		api : {
			create : expensetracker.util.Url.getIncomeService(),
			read : expensetracker.util.Url.getIncomeService(),
			update : expensetracker.util.Url.getIncomeService(),
			destroy : expensetracker.util.Url.getIncomeService()
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