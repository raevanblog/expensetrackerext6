Ext.define('expensetracker.model.ExpenseCategory', {
	extend : 'Ext.data.Model',
	alias : 'model.expensecategory',
	fields : [ {
		name : 'categoryId',
		type : 'int',
		persist: false
	}, {
		name : 'category',
		type : 'string',
		convert: function(value, record) {
			return value.toUpperCase();
		}
	}, {
		name : 'description',
		type : 'string'
	} ],
	validators: {
		category:{
			type: 'presence',			
			message: 'Category cannot be empty'		
		}
	},
	idProperty : 'categoryId'
});