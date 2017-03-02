Ext.define('expensetracker.model.Inventory', {
	extend : 'Ext.data.Model',
	alias : 'model.inventory',
	fields : [ {
		name : 'id',
		type : 'int',
		persist : false
	}, {
		name : 'expId',
		allowNull : true,
		type : 'int'
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
		name : 'mth',
		type : 'int'
	},{
		name : 'yr',
		type : 'int'
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