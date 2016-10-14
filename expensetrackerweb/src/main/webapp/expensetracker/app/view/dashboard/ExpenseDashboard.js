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
	items : [{
		xtype: 'panel',
		title: 'Expense Sheet',
		height: 180,
		layout : {
			type: 'hbox',
			pack: 'middle'
		},
		cls: 'big-20 small-50 dash-panel shadow',
		iconCls: 'x-fa fa-file-text-o',
		items: [{
			xtype: 'thumnailcontainer',
			reference: 'expsheetdash',
			listeners: {
				itemclick: 'onOpenExpenseSheet'
			}
		}]
	},{
		xtype : 'expensechart',
		reference: 'expensechartpanel',
		title : 'Top Expense',
		height: 300,		
		cls : 'big-60 small-100 dash-panel'
	} ,{
		xtype: 'panel',
		layout: 'fit',
		iconCls: 'x-fa  fa-rupee',
		title: 'Top Expense',
		height: 400,
		items: [{
			xtype: 'expensesummary',
			reference: 'expensesummary'
		}],
		cls : 'big-40 small-50 dash-panel'		 		
	} ]
});