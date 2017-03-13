Ext.define('expensetracker.view.inventory.InventoryWindow', {
	extend : 'Ext.window.Window',
	alias : 'view.inventorywindow',
	xtype : 'inventorywindow',
	controller : 'inventorycontroller',
	iconCls : 'x-fa fa-file-text-o',
	viewModel : 'inventorymodel',
	closeToolText : 'Close',
	requires : [ 'expensetracker.view.inventory.InventoryGrid', 'expensetracker.view.inventory.InventoryWindowController', 'expensetracker.view.inventory.InventoryWindowModel' ],
	bind : {
		title : '{title}'
	},
	layout : {
		type : 'card',
		animate : true,
		animation : {
			type : 'pop',
			easing : 'ease-in',
			direction : 'left'
		}
	},
	listeners : {
		beforeclose : 'onCloseInventoryWindow'		
	},
	tools : [ {
		type : 'refresh',
		tooltip : 'Reload',
		handler : 'onReload'
	} ],
	items : [ {
		xtype : 'inventorygrid',
		reference : 'inventorygrid',
		listeners : {
			afterrender : 'onRenderInventoryGrid'
		}
	} ]
});