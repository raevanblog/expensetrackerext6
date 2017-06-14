Ext.define('expensetracker.model.Graph', {
	extend : 'Ext.data.Model',
	alias : 'model.Graph',
	fields : [ {
		name : 'month',
		type : 'int'
	}, {
		name : 'monthname',
		type : 'string',
		persist : false,
		calculate: function (data) {
         return data.month != null ? expensetracker.util.Calendar.getName(data.month) : null;
		}
	}, {
		name : 'year',
		type : 'int'
	}, {
		name : 'income',
		type : 'float'
	}, {
		name : 'expense',
		type : 'float'
	}, {
		name : 'type',
		type : 'string'
	},{
		name : 'name',
		type : 'string'		
	}, {
		name : 'avgprice',
		type : 'float'
	} ]
});