Ext.define('expensetracker.view.expense.ExpenseCategory', {
	extend : 'Ext.grid.Panel',
	alias : 'view.expensecategory',
	xtype : 'expensecategory',
	controller : 'expensecategorycontroller',
	requires : [ 'expensetracker.store.ExpenseCategory', 'expensetracker.view.expense.ExpenseCategoryController' ],
	store : 'ExpenseCategory',
	bbar : [ '->', {
		xtype : 'button',
		text : 'Save',
		ui : 'soft-green',
		iconCls : 'x-fa fa-save',
		handler : 'onCategorySaveOrUpdate'
	} ],	
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
		align : 'center',
		dataIndex : 'category',
		editor : {
			xtype : 'textfield',
			maxLength : 15,
			allowBlank : false
		},
		width : 200
	}, {
		text : 'Description',
		align : 'left',
		dataIndex : 'description',
		editor : {
			xtype : 'textfield',
			allowBlank : true,
			maxLength : 100
		},
		width : 200
	}, {
		xtype : 'actioncolumn',
		align : 'center',
		tooltip : 'Delete',
		handler : 'onDeleteCategory',
		iconCls : 'x-fa fa-trash-o',
		flex : 1
	} ]
});