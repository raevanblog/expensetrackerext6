Ext.define('expensetracker.view.income.IncomeWindow', {
	extend : 'Ext.window.Window',
	alias : 'view.incomewindow',
	xtype : 'incomewindow',
	controller : 'incomewindowcontroller',	
	requires : ['expensetracker.view.income.IncomeGrid', 'expensetracker.view.income.IncomeWindowController'],
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
		beforeclose : 'onCloseExpenseWindow'
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
	}]
});