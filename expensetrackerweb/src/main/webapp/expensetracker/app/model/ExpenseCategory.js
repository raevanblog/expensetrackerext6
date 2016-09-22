Ext.define('expensetracker.model.ExpenseCategory', {
	extend : 'Ext.data.Model',
	alias : 'model.expensecategory',
	fields : [ {
		name : 'categoryId',
		type : 'int'
	}, {
		name : 'category',
		type : 'string'
	}, {
		name : 'description',
		type : 'string'
	} ],
	idProperty : 'categoryId'
});