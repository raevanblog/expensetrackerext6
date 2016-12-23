Ext.define('expensetracker.view.expense.ExpenseCategory', {
	extend : 'Ext.grid.Panel',
	alias : 'view.expensecategory',
	xtype : 'expensecategory',
	controller : 'expensecategorycontroller',
	requires : [ 'expensetracker.store.ExpenseCategory', 'expensetracker.view.expense.ExpenseCategoryController' ],
	bbar : ['->', {
		xtype : 'button',
		text : 'Save',
		iconCls : 'x-fa fa-save',
		handler : 'onCategorySaveOrUpdate'
	} ],
	listeners : {
		activate : 'onActivateCategoryGrid'
	},
	plugins : {
		ptype : 'cellediting',
		clicksToEdit : 1
	},
	columns : [ {
		xtype : 'rownumberer',
		width : 50
	}, {
		text : 'Category',
		dataIndex : 'category',
		editor : {
			xtype : 'textfield',
			maxLength: 15,
			allowBlank : false			
		},
		width : 200
	}, {
		text : 'Description',
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
		iconCls : 'x-fa  fa-minus-circle',
		flex :1 
	} ]
});