Ext.define('expensetracker.view.dashboard.Dashboard', {
	extend : 'Ext.container.Container',
	xtype : 'maindashboard',
	alias : 'view.maindashboard',
	requires : [ 'Ext.ux.layout.ResponsiveColumn' ],
	layout : 'responsivecolumn',
	items : [ {
		xtype : 'panel',
		title : 'Total Expense',
		cls : 'big-60 small-100'
	}, {
		xtype : 'panel',
		title : 'Top Expense',
		cls : 'big-40 small-50'
	}, {
		xtype : 'panel',
		title : 'Expense - Previous Month',
		cls : 'big-100 small-100'
	} ]
});