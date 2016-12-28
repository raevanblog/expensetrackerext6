Ext.define('expensetracker.model.Graph', {
	extend : 'Ext.data.Model',
	alias : 'model.Graph',
	fields : [ {
		name : 'month',
		type : 'int'
	}, {
		name : 'year',
		type : 'int'
	}, {
		name : 'income',
		type : 'float'
	}, {
		name : 'expense',
		type : 'float'
	}, {
		name : 'type',
		type : 'string'
	} ]
});