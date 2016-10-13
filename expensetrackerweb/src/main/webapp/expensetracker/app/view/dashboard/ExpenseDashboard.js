Ext.define('expensetracker.view.dashboard.ExpenseDashboard', {
	extend : 'Ext.container.Container',
	xtype : 'expensedashboard',
	alias : 'view.expensedashboard',
	requires : [ 'Ext.ux.layout.ResponsiveColumn', 'expensetracker.view.charts.Expense', 'expensetracker.view.dashboard.ExpenseDashboardController', 'expensetracker.view.dashboard.ExpenseSummary' ],
	layout : 'responsivecolumn',
	controller: 'expensedashboardcontroller',
	listeners: {
		afterrender: 'onRender'
	},
	items : [ {
		xtype : 'panel',
		title : 'Expense Summary',
		cls : 'big-60 small-100',
		items: [{
			xtype: 'expensesummary',
			reference: 'expensesummary',
			height: 400
		}]
	}, {
		xtype : 'expensechart',
		reference: 'expensechartpanel',
		title : 'Top Expense',
		height: 300,
		cls : 'big-40 small-50'
	}, {
		xtype : 'panel',
		title : 'Expense - Previous Month',
		cls : 'big-100 small-100'
	} ]
});