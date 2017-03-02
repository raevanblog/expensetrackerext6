Ext.define('expensetracker.view.income.IncomeWindowModel', {
	extend : 'Ext.app.ViewModel',
	alias : 'viewmodel.incomemodel',
	data : [ {
		month : '',
		year : '',
		title : '',
		isLatestIncome : false,
		source : null
	} ]
})