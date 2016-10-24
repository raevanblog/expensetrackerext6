Ext.define('expensetracker.view.expense.ExpenseWindow', {
	extend : 'Ext.window.Window',
	alias : 'view.expensewindow',
	xtype : 'expensewindow',
	controller : 'expensewindowcontroller',
	viewModel : 'expensewindowmodel',
	iconCls : 'x-fa fa-file-text-o',
	requires : [ 'expensetracker.view.expense.ExpenseWindowController', 'expensetracker.view.expense.ExpenseWindowModel',
			'expensetracker.view.expense.ExpenseGrid', 'expensetracker.view.expense.ExpenseCategory' ],
	bind : {
		title : '{title}'
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
		beforeclose : 'onCloseExpenseWindow',
		beforerender: 'onRender'
	},
	tools : [ {
		type : 'refresh',
		tooltip : 'Reload',
		handler : 'onReload'
	} ],
	items : [ {
		xtype : 'expensegrid',
		reference : 'expensegrid',
		listeners : {
			afterrender : 'onRenderExpenseGrid'
		}
	}, {
		xtype : 'expensecategory',
		reference : 'expensecategory',
		tbar : [ {
			xtype : 'button',
			iconCls : 'x-fa fa-backward',
			handler : 'onBackCategory'
		}, '-', {
			xtype : 'textfield',
			emptyText : 'Search...',
			reference : 'categorygridsearch',
			listeners : {
				change : 'filterGrid'
			}
		}, '->', {
			xtype : 'button',
			text : 'Category',
			iconCls : 'x-fa fa-plus-square',
			handler : 'onAddCategory'
		} ]
	} ]
});