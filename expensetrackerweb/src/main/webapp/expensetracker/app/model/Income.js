Ext.define('expensetracker.model.Income', {
	extend : 'Ext.data.Model',
	alias : 'model.income',
	fields : [ {
		name : 'id',
		type : 'int',
		persist : false
	}, {
		name : 'incometype',
		type : 'string'
	}, {
		name : 'mth',
		type : 'int'		
	}, {
		name : 'yr',
		type : 'int'
	}, {
		name : 'income',
		type : 'float'
	},{
		name : 'username',
		type : 'string'
	} ],
	idProperty : 'id'
});