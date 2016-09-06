Ext.define('expensetracker.store.ExpenseDock', {
	extend : 'Ext.data.Store',
	alias : 'store.expensedock',		
	fields : [ {
		name : 'month'
	} ],
	data : [ {
		month : 'January'
	}, {
		month : 'February'
	}, {
		month : 'March'
	}, {
		month : 'April'
	}, {
		month : 'May'
	}, {
		month : 'June'
	}, {
		month : 'July'
	}, {
		month : 'August'
	}, {
		month : 'September'
	}, {
		month : 'October'
	}, {
		month : 'November'
	}, {
		month : 'December'
	} ]
});