Ext.define('expensetracker.view.expense.ExpenseCategory', {
	extend : 'Ext.grid.Panel',
	alias : 'view.expensecategory',
	xtype : 'expensecategory',
	controller : 'expensecategorycontroller',
	requires : [ 'expensetracker.store.ExpenseCategory', 'expensetracker.view.expense.ExpenseCategoryController' ],
	store : 'ExpenseCategory',
	listeners : {
		render : 'onRenderCategoryGrid'
	},	
	plugins : {
		ptype : 'cellediting',
		clicksToEdit : 1
	},
	columns : [ {
		xtype : 'rownumberer',
		text : 'S.No',
		width : 75
	}, {
		text : 'Category',
		align : 'left',
		dataIndex : 'category',		
		width : 250
	}, {
		text : 'Example',
		align : 'left',
		dataIndex : 'description',		
		flex : 1
	}]
});