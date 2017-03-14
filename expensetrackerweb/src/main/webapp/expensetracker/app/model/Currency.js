Ext.define('expensetracker.model.Currency', {
	extend : 'Ext.data.Model',
	alias : 'model.currency',
	fields : [ {
		name : 'id',
		type : 'int',
		persist : false
	}, {
		name : 'currsymb',
		type : 'string'		
	},{
		name : 'currtxt',
		type : 'string'
	}, {
		name : 'display',
		type : 'string',
		persist : false,
		calculate: function (data) {
         return data.currsymb + ' (' + data.currtxt + ')';
		}
	}],
	idProperty : 'id',
	validators : {
		itemName : 'presence',
		category : 'presence',
		exptype : 'presence'
	}
});