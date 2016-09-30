Ext.define('expensetracker.view.expense.ExpenseWindow', {
	extend : 'Ext.window.Window',
	alias : 'view.expensewindow',
	xtype : 'expensewindow',
	controller : 'expensewindowcontroller',
	viewModel : 'expensewindowmodel',
	requires : [ 'expensetracker.view.expense.ExpenseWindowController', 'expensetracker.view.expense.ExpenseWindowModel',
			'expensetracker.view.expense.ExpenseGrid', 'expensetracker.view.expense.ExpenseCategory' ],
	bind: {
		title: '{title}'
	},
	layout : {
		type : 'card',
		animate : true,
		animation : {
			type : 'pop',
			easing : 'ease-in',			
			direction : 'left'
		}
	},
	listeners : {
		beforeclose : 'onCloseExpenseWindow'
	},
	tools: [{
		type: 'refresh',
		tooltip: 'Reload',
		handler: 'onReload'
	}],
	items : [ {
		xtype : 'expensegrid',
		reference : 'expensegrid',
		store : Ext.create('expensetracker.store.Expense'),
		listeners : {
			afterrender : 'onRenderExpenseGrid'
		}
	}, {
		xtype : 'expensecategory',
		reference : 'expensecategory',
		tbar : [{
			xtype : 'button',
			text : 'Back',
			iconCls : 'x-fa fa-backward',
			handler : 'onBackCategory'
		},'->',{
			xtype: 'button',
			text : 'Category',
			iconCls: 'x-fa fa-plus-square',
			handler : 'onAddCategory'
		}]
	} ]
});