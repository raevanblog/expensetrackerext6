Ext.define('expensetracker.view.expense.ExpenseWindowModel', {
	extend : 'Ext.app.ViewModel',
	alias : 'viewmodel.expensewindowmodel',
	data : [ {
		expenseStartDate : '',
		expenseEndDate : '',
		username : '',
		month : '',
		year : ''
	} ]
})