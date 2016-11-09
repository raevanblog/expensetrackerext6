Ext.define('expensetracker.view.income.IncomeWindowModel', {
	extend : 'Ext.app.ViewModel',
	alias : 'viewmodel.incomewindowmodel',
	data : [ {
		month : '',
		year : '',
		title : '',
		isLatestIncome : false,
		source : ''
	} ]
})