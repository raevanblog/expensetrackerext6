Ext.define('expensetracker.view.dashboard.ExpenseDashboard', {
	extend : 'Ext.container.Container',
	xtype : 'expensedashboard',
	alias : 'view.expensedashboard',
	requires : [ 'Ext.ux.layout.ResponsiveColumn', 'expensetracker.view.dashboard.TopExpense', 'expensetracker.view.dashboard.ExpenseDashboardController' ],
	layout : 'responsivecolumn',
	controller: 'expensedashboardcontroller',
	listeners: {
		afterrender: 'onRender'
	},
	items : [ {
		xtype : 'panel',
		title : 'Total Expense',
		cls : 'big-60 small-100'
	}, {
		xtype : 'topexpense',
		reference: 'topexpense',
		title : 'Top Expense',
		height: 300,
		cls : 'big-40 small-50'
	}, {
		xtype : 'panel',
		title : 'Expense - Previous Month',
		cls : 'big-100 small-100'
	} ]
});