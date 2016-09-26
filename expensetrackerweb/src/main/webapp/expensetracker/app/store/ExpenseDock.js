Ext.define('expensetracker.store.ExpenseDock', {
	extend : 'Ext.data.Store',
	alias : 'store.expensedock',
	fields : [ {
		name : 'month',
		type : 'string'
	}, {
		name : 'monthNo',
		type : 'int'
	} ],
	data : [ {
		month : 'January',
		monthNo : 1
	}, {
		month : 'February',
		monthNo : 2
	}, {
		month : 'March',
		monthNo : 3
	}, {
		month : 'April',
		monthNo : 4
	}, {
		month : 'May',
		monthNo : 5
	}, {
		month : 'June',
		monthNo : 6
	}, {
		month : 'July',
		monthNo : 7
	}, {
		month : 'August',
		monthNo : 8
	}, {
		month : 'September',
		monthNo : 9
	}, {
		month : 'October',
		monthNo : 10
	}, {
		month : 'November',
		monthNo : 11
	}, {
		month : 'December',
		monthNo : 12
	} ]
});