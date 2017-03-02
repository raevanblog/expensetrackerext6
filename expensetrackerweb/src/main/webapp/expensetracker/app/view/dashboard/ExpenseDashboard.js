Ext.define('expensetracker.view.dashboard.ExpenseDashboard', {
	extend : 'Ext.container.Container',
	xtype : 'expensedashboard',
	alias : 'view.expensedashboard',
	requires : [ 'Ext.ux.layout.ResponsiveColumn', 'expensetracker.view.charts.Expense', 'expensetracker.view.dashboard.ExpenseDashboardController', 'expensetracker.view.dashboard.TopExpense',
			'expensetracker.view.dashboard.Summary', 'expensetracker.view.charts.SummaryPie', 'expensetracker.view.charts.LineChart', 'expensetracker.view.income.IncomeWindow',
			'expensetracker.view.inventory.InventoryWindow',
			'expensetracker.store.Graph', 'expensetracker.view.dashboard.ExpenseDashboardModel' ],
	layout : 'responsivecolumn',
	controller : 'expensedashboardcontroller',
	viewModel : 'expensedashboard',
	listeners : {
		afterrender : 'onRender'
	},
	items : [ {
		xtype : 'summary',				
		reference : 'summary',		
		height : 100,		
		cls : 'big-100 small-100 dash-panel shadow'		
	}, {
		xtype : 'container',		
		height : 150,
		layout : {
			type : 'hbox',
			pack : 'middle'
		},
		cls : 'big-20 small-100 dash-panel shadow',		
		items : [ {
			xtype : 'thumnailcontainer',
			reference : 'incomesheetdash',
			listeners : {
				itemclick : 'onOpenIncomeSheet'
			}
		} ]
	}, {
		xtype : 'container',		
		height : 150,
		layout : {
			type : 'hbox',
			pack : 'middle'
		},
		cls : 'big-20 small-100 dash-panel shadow',		
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
		cls : 'big-40 small-100 dash-panel'
	}, {
		xtype : 'linechart',
		reference : 'expensevsincome',
		title : 'Expense vs Income',
		height : 400,
		iconCls : 'x-fa fa-line-chart',
		listeners : {
			afterrender : 'onRenderExpenseVsIncome'
		},
		cls : 'big-100 small-100 dash-panel'
	}, {
		xtype : 'expensechart',
		reference : 'categorychartpanel',
		title : 'Expense',
		height : 400,
		iconCls : 'x-fa fa-line-chart',
		listeners : {
			afterrender : 'onRenderCategoryExpense'
		},
		cls : 'big-100 small-100 dash-panel'
	} ]
});