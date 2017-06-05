Ext.define('expensetracker.model.Item', {
	extend : 'Ext.data.Model',
	alias : 'model.item',
	fields : [ {
		name : 'name',
		type : 'string'
	}, {
		name : 'category',
		type : 'string'
	}, {
		name : 'start_year',
		type : 'int'		
	}, {
		name : 'end_year',
		type : 'int'
	} ]
});