Ext.define('expensetracker.view.dashboard.ExpenseDashboard', {
	extend : 'Ext.container.Container',
	xtype : 'expensedashboard',
	alias : 'view.expensedashboard',
	requires : [ 'Ext.ux.layout.ResponsiveColumn', 'expensetracker.view.charts.Expense', 'expensetracker.view.dashboard.ExpenseDashboardController', 'expensetracker.view.dashboard.TopExpense',
			'expensetracker.view.dashboard.Summary', 'expensetracker.view.charts.SummaryPie', 'expensetracker.view.income.IncomeWindow' ],
	layout : 'responsivecolumn',
	controller : 'expensedashboardcontroller',
	listeners : {
		afterrender : 'onRender'
	},
	items : [ {
		xtype : 'panel',
		title : 'Summary',
		reference : 'summarypanel',
		tools : [ {
			type : 'refresh',
			tooltip : 'Reload',
			handler : 'updateDashBoardSummary'
		} ],
		height : 250,
		iconCls : 'x-fa fa-sticky-note-o',
		cls : 'big-40 small-100 dash-panel shadow',
		layout : {
			type : 'hbox'
		},
		items : [ {
			xtype : 'summary',
			bodyPadding : '10 0 0 10',
			flex : 0.8
		}, {
			xtype : 'summarypie',
			reference : 'summarypiepanel',
			height : '100%',
			padding : '0 0 0 20',
			flex : 1.2
		} ]
	}, {
		xtype : 'panel',
		title : 'Expense Sheet',
		height : 250,
		layout : {
			type : 'hbox',
			pack : 'middle'
		},
		cls : 'big-20 small-100 dash-panel shadow',
		iconCls : 'x-fa fa-file-text-o',
		items : [ {
			xtype : 'thumnailcontainer',
			reference : 'expsheetdash',
			listeners : {
				itemclick : 'onOpenExpenseSheet'
			}
		} ]
	}, {
		xtype : 'panel',
		layout : 'fit',
		iconCls : 'x-fa  fa-rupee',
		title : 'Top Expense',
		height : 250,
		items : [ {
			xtype : 'topexpense',
			reference : 'topexpense'
		} ],
		cls : 'big-33 small-100 dash-panel'
	} ]
});