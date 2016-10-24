Ext.define('expensetracker.view.income.IncomeWindow', {
	extend : 'Ext.window.Window',
	alias : 'view.incomewindow',
	xtype : 'incomewindow',
	controller : 'incomewindowcontroller',
	iconCls : 'x-fa fa-file-text-o',
	viewModel : 'incomewindowmodel',
	requires : [ 'expensetracker.view.income.IncomeGrid', 'expensetracker.view.income.IncomeWindowController',
			'expensetracker.view.income.IncomeWindowModel' ],
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
		beforeclose : 'onCloseIncomeWindow'
	},
	tools : [ {
		type : 'refresh',
		tooltip : 'Reload',
		handler : 'onReload'
	} ],
	items : [ {
		xtype : 'incomegrid',
		reference : 'incomegrid',
		listeners : {
			afterrender : 'onRenderIncomeGrid'
		}
	} ]
});