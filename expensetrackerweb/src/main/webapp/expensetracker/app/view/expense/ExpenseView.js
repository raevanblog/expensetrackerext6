Ext.define('expensetracker.view.expense.ExpenseView', {
	extend : 'Ext.container.Container',
	xtype : 'expenseview',
	alias : 'view.expenseview',
	requires : [ 'expensetracker.view.expense.ExpenseDock', 'expensetracker.view.expense.ExpenseViewController' ],
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
				type : 'hbox'
			},
			items : [ {
				xtype : 'displayfield',
				reference : 'expviewselyear',
				width : 100
			}, {
				xtype : 'sliderfield',
				fieldLabel : 'Select Year',
				labelSeparator : '',
				value : 2016,
				increment : 1,
				minValue : 1990,
				maxValue : 2016,
				flex : 1,
				listeners: {
					changecomplete: 'onYearSelection'
				}
			} ]
		} ]
	}, {
		xtype : 'expensedock',
		border : 1
	} ]
});