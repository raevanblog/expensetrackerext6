Ext.define('expensetracker.view.expense.ExpenseView', {
	extend : 'Ext.container.Container',
	xtype : 'expenseview',
	alias : 'view.expenseview',
	requires : [ 'expensetracker.view.expense.ExpenseDock', 'expensetracker.view.expense.ExpenseViewController', 'expensetracker.view.expense.ExpenseViewModel',  'expensetracker.view.expense.ExpenseWindow' ],
	controller : 'expenseviewcontroller',
	viewModel : 'expenseviewmodel',
	layout : 'responsivecolumn',
	items : [{
		xtype : 'expensedock',
		cls : 'big-100 small-100 dash-panel shadow',
		reference : 'expensedock',
		listeners : {
			afterrender : 'onRenderExpenseDock'
		}		
	} ]
});