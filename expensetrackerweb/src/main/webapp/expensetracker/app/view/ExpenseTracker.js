Ext.define('expensetracker.view.ExpenseTracker', {
	extend: 'Ext.container.Viewport',
	xtype: 'expensetracker',
	id: 'ExpenseTracker',
	requires: ['expensetracker.view.main.Main', 'expensetracker.view.login.Login'],
	layout: {
		type: 'card'
	},
	items: [{
		xtype: 'login'
	}, {
		xtype: 'app-main'
	}]
});