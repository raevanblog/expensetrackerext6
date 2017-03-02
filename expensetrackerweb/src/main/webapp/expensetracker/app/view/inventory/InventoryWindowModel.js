Ext.define('expensetracker.view.inventory.InventoryWindowModel', {
	extend : 'Ext.app.ViewModel',
	alias : 'viewmodel.inventorymodel',
	data : [ {
		month : '',
		year : '',
		title : '',
		source : null
	} ]
})