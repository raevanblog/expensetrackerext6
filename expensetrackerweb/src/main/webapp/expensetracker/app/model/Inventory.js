Ext.define('expensetracker.model.Inventory', {
	extend : 'Ext.data.Model',
	alias : 'model.inventory',
	fields : [ {
		name : 'id',
		type : 'int',
		persist : false
	}, {
		name : 'itemName',
		type : 'string'
	}, {
		name : 'category',
		type : 'string'
	}, {
		name : 'qty',
		type : 'float'
	}, {
		name : 'unit',
		type : 'string'
	}, {
		name : 'username',
		type : 'string'
	} ],
	idProperty : 'id',
	validators : {
		itemName : 'presence',
		category : 'presence',
		qty : 'presence'
	}
});