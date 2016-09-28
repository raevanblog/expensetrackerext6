Ext.define('expensetracker.view.expense.ExpenseCategory', {
	extend : 'Ext.panel.Panel',
	alias : 'view.expensecategory',
	xtype : 'expensecategory',
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	bbar : [ {
		xtype : 'button',
		text : 'Save/Update',
		handler : 'onCategorySaveOrUpdate'
	} ],
	items : [ {
		xtype : 'grid',
		store : 'ExpenseCategory',
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
				maxLength : 100
			},
			flex : 0.3
		}, {
			text : 'Description',
			dataIndex : 'description',
			editor : {
				xtype : 'textfield',
				maxLength : 100
			},
			flex : 0.7
		} ]
	} ]
});