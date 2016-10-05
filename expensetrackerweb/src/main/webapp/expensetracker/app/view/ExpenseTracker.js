Ext.define('expensetracker.view.ExpenseTracker', {
	extend : 'Ext.container.Viewport',
	xtype : 'expensetracker',
	id : 'ExpenseTracker',
	requires : [ 'expensetracker.view.main.Main', 'expensetracker.view.login.Login', 'expensetracker.view.ExpenseTrackerController' ],
	controller : 'expensetracker',
	layout : {
		type : 'card'
	},
	items : [ {
		xtype : 'login'
	} ]
});