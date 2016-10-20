Ext.define('expensetracker.model.IncomeType', {
	extend : 'Ext.data.Model',
	alias : 'model.incometype',
	fields : [ {
		name : 'id',
		type : 'int',
		persist : false
	}, {
		name : 'incometype',
		type : 'string'
	}, {
		name : 'description',
		type : 'string'
	} ],
	idProperty : 'id'
});