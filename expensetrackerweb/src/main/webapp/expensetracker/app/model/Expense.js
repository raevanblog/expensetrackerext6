Ext.define('expensetracker.model.Expense', {
	extend : 'Ext.data.Model',
	alias : 'model.expense',
	fields : [ {
		name : 'id',
		type : 'int',
		persist : false
	}, {
		name : 'itemName',
		type : 'string'
	}, {
		name : 'expdate',
		type : 'date',
		dateFormat : 'Y-m-d'
	}, {
		name : 'category',
		type : 'string'
	}, {
		name : 'exptype',
		type : 'string'
	}, {
		name : 'price',
		type : 'float'
	}, {
		name : 'qty',
		type : 'float'
	}, {
		name : 'pricePerUnit',
		type : 'float'
	}, {
		name : 'username',
		type : 'string'
	} ],
	idProperty : 'id'
});