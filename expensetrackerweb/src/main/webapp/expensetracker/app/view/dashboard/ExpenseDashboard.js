Ext.define('expensetracker.view.dashboard.ExpenseDashboard', {
	extend : 'Ext.container.Container',
	xtype : 'expensedashboard',
	alias : 'view.expensedashboard',
	requires : [ 'Ext.ux.layout.ResponsiveColumn', 'expensetracker.view.charts.Expense', 'expensetracker.view.dashboard.ExpenseDashboardController', 'expensetracker.view.dashboard.TopExpense',
			'expensetracker.view.dashboard.Summary', 'expensetracker.view.charts.SummaryPie', 'expensetracker.view.charts.LineChart', 'expensetracker.view.income.IncomeWindow',
			'expensetracker.view.inventory.InventoryWindow', 'expensetracker.store.Graph', 'expensetracker.view.dashboard.ExpenseDashboardModel'],
	layout : 'responsivecolumn',
	controller : 'expensedashboardcontroller',
	viewModel : 'expensedashboard',
	listeners : {
		afterrender : 'onRender'
	},
	items : [ {
		xtype : 'container',
		cls : 'big-100 small-100',
		platformConfig : {
			desktop : {
				layout : {
					type : 'hbox',
					pack : 'end'		
				}
			},

			'!desktop' : {
				layout : {
					type : 'vbox',
					align : 'stretch'					
				}
			}
		},		
		items : [{
			xtype : 'combobox',
			forceSelection : true,
			queryMode : 'local',
			displayField : 'month',
			valueField : 'monthNo',
			editable : false,
			allowBlank : false,
			store: 'Calendar',
			margin : '5 5 5 5',
			reference : 'expensemonth',
			value : expensetracker.util.Session.getExpenseMonth()			
		}, {
			xtype : 'combobox',
			forceSelection : true,
			displayField : 'year',			
			valueField : 'year',
			queryMode : 'local',
			allowBlank : false,
			editable : false,
			store : 'Year',
			margin : '5 5 5 5',
			reference : 'expenseyear',
			value : expensetracker.util.Session.getExpenseYear()
		}, {
			xtype : 'button',
			text : 'View',
			iconCls : 'x-fa fa-eye',
			margin : '5 5 5 5',
			ui : 'soft-green',
			handler : 'onLoadExpenseData'
		}]
	},{
		xtype : 'summary',
		reference : 'summary',
		height : 100,
		cls : 'big-100 small-100 dash-panel shadow'
	}, {
		xtype : 'container',
		height : 150,
		layout : {
			type : 'fit'
		},
		cls : 'big-20 small-100 dash-panel shadow',
		items : [ {
			xtype : 'summarypie',
			reference : 'summarypie'
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
			reference : 'incomesheetdash',
			icontype : 'calendar',
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
			icontype : 'calendar',
			listeners : {
				itemclick : 'onOpenExpenseSheet'
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
			reference : 'inventorydash',
			icontype : 'inventory',
			listeners : {
				itemclick : 'onOpenInventory'
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
			reference : 'reportdash',
			icontype : 'pdf',
			listeners : {
				itemclick : 'onOpenReport'
			}
		} ]
	}, {
		xtype : 'linechart',
		reference : 'expensevsincome',
		title : 'Expense vs Income',
		height : 500,
		iconCls : 'x-fa fa-line-chart',
		listeners : {
			afterrender : 'onRenderExpenseVsIncome'
		},
		cls : 'big-100 small-100 dash-panel'
	}, {
		xtype : 'expensechart',
		reference : 'categorychartpanel',		
		title : 'Expense',		
		height : 500,
		iconCls : 'x-fa fa-line-chart',
		listeners : {
			afterrender : 'onRenderCategoryExpense'
		},
		cls : 'big-100 small-100 dash-panel'
	} ]
});