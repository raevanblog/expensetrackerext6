Ext.define('expensetracker.model.Units', {
	extend : 'Ext.data.Model',
	alias : 'model.units',
	fields : [ {
		name : 'id',
		type : 'int',
		persist : false
	}, {
		name : 'unit',
		type : 'string'
	}, {
		name : 'description',
		type : 'string'
	}, {
		name : 'username',
		type : 'string'
	}, {
		name : 'display',
		type : 'string',
		persist : false,
		calculate : function(data) {
			return data.unit + ' (' + (data.description !== null ? data.description : 'NA') + ')';
		}
	} ],
	idProperty : 'id'
});