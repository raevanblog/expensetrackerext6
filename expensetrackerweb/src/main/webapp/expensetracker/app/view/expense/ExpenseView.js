Ext.define('expensetracker.view.expense.ExpenseView', {
	extend : 'Ext.container.Container',
	xtype : 'expenseview',
	alias : 'view.expenseview',
	requires : [ 'expensetracker.view.expense.ExpenseDock', 'expensetracker.view.expense.ExpenseViewController',
			'expensetracker.view.expense.ExpenseWindow' ],
	controller : 'expenseviewcontroller',
	layout : {
		type : 'vbox',
		align : 'stretch',
		padding : '10 20 10 10'
	},
	items : [ {
		xtype : 'fieldset',
		title : 'Expense Book',
		layout : 'fit',
		items : [ {
			xtype : 'fieldcontainer',
			layout : {
				type : 'hbox',
				padding : '0 0 0 15'
			},
			items : [ {
				xtype : 'component',
				reference : 'expviewselyear',
				html : '',
				margin : '10 10 10 10',
				cls : 'year-square'
			}, {
				xtype : 'sliderfield',
				fieldLabel : 'Select Year',
				labelSeparator : '',
				value : new Date().getFullYear(),
				padding : '20 10 0 10',
				increment : 1,
				minValue : 1990,
				maxValue : 2016,
				flex : 0.80,
				reference : 'yearslider',
				platformConfig : {
					'!desktop' : {
						width : '100%'
					}
				},
				listeners : {
					changecomplete : 'onYearSelection',
					afterrender : 'onRenderSlider'
				}
			} ]
		} ]
	}, {
		xtype : 'expensedock',
		reference : 'expensedock',
		listeners : {
			afterrender : 'onRenderExpenseDock'
		},
		border : 1
	} ]
});