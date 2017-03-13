Ext.define('expensetracker.view.expense.ExpenseWindow', {
	extend : 'Ext.window.Window',
	alias : 'view.expensewindow',
	xtype : 'expensewindow',
	scrollable : true,
	controller : 'expensewindowcontroller',
	viewModel : 'expensewindowmodel',
	iconCls : 'x-fa fa-file-text-o',
	closeToolText :  'Close',
	requires : [ 'expensetracker.view.expense.ExpenseWindowController', 'expensetracker.view.expense.ExpenseWindowModel', 'expensetracker.view.expense.ExpenseGrid',
			'expensetracker.view.expense.ExpenseCategory', 'Ext.fx.animation.Slide' ],
	bind : {
		title : '{title}'
	},
	tools : [ {
		type : 'refresh',
		tooltip : 'Reload',
		handler : 'onReload'
	} ],
	layout : {
		type : 'card'
	},
	listeners : {
		beforeclose : 'onCloseExpenseWindow',
		beforerender : 'onRender'
	},
	items : [ {
		xtype : 'expensegrid',
		reference : 'expensegrid',
		listeners : {
			afterrender : 'onRenderExpenseGrid'
		}
	}, {
		xtype : 'expensecategory',
		reference : 'expensecategory',
		dockedItems : [ {
			xtype : 'toolbar',
			dock : 'top',
			overflowHandler : 'menu',
			items : [ {
				xtype : 'button',
				ui : 'toolbar',
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
				ui : 'toolbar',
				text : 'Category',
				iconCls : 'x-fa fa-plus-square',
				handler : 'onAddCategory'
			} ]
		} ]
	} ]
});