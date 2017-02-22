Ext.define('expensetracker.store.ExpenseSheet', {
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
		title : 'January',
		month : 'January',
		monthNo : 1
	}, {
		title : 'February',
		month : 'February',
		monthNo : 2
	}, {
		title : 'March',
		month : 'March',
		monthNo : 3
	}, {
		title : 'April',
		month : 'April',
		monthNo : 4
	}, {
		title : 'May',
		month : 'May',
		monthNo : 5
	}, {
		title : 'June',
		month : 'June',
		monthNo : 6
	}, {
		title : 'July',
		month : 'July',
		monthNo : 7
	}, {
		title : 'August',
		month : 'August',
		monthNo : 8
	}, {
		title : 'September',
		month : 'September',
		monthNo : 9
	}, {
		title : 'October',
		month : 'October',
		monthNo : 10
	}, {
		title : 'November',
		month : 'November',
		monthNo : 11
	}, {
		title : 'December',
		month : 'December',
		monthNo : 12
	} ],
	autoLoad : false
});