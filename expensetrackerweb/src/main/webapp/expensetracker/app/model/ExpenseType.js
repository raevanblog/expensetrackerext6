Ext.define('expensetracker.model.ExpenseType', {
	extend : 'Ext.data.Model',
	alias : 'model.expensetype',
	fields : [ {
		name : 'id',
		type : 'int',
		persist : false
	}, {
		name : 'exptype',
		type : 'string'
	}, {
		name : 'description',
		type : 'string'
	} ],
	idProperty : 'id'
});