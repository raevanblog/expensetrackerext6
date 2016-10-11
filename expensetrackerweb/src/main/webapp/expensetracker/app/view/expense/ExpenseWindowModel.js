Ext.define('expensetracker.view.expense.ExpenseWindowModel', {
	extend : 'Ext.app.ViewModel',
	alias : 'viewmodel.expensewindowmodel',
	data : [ {
		expenseStartDate : '',
		expenseDate : '',
		expenseEndDate : '',
		username : '',
		month : '',
		year : '',
		title : ''
	} ]
})