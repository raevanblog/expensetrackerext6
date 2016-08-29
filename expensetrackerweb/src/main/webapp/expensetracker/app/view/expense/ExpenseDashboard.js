Ext.define('expensetracker.view.expense.ExpenseDashboard', {
	extend : 'Ext.Container',
	xtype : 'expensedashboard',
	alias : 'view.expensedashboard',
	layout : 'vbox',
	controller: 'expensedbcontroller',
	requires: ['expensetracker.view.expense.ExpenseDBController'],
	items : [ {
		xtype : 'fieldset',
		title : 'Add/Remove Expense',
		items : [ {
			xtype : 'container',
			layout : 'hbox',
			items : [ {
				xtype : 'datepickerfield',
				label : 'Choose Year',
				usePicker : 'auto',
				dateFormat : 'Y',
				margin: '10 5 10 5',
				flex: 0.5
			},{
				xtype: 'button',
				text: 'Add',
				margin: '10 5 10 5',
				flex: 0.5,
				listeners: {
					tap: 'onAddExpenseSheet'
				}
			}]
		} ]
	} ]
});