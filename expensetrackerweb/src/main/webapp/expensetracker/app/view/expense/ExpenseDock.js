Ext.define('expensetracker.view.expense.ExpenseDock', {
	extend : 'Ext.panel.Panel',
	xtype : 'expensedock',
	storeId : 'ExpenseDock',
	alias : 'view.expensedock',
	requires : [ 'expensetracker.store.ExpenseDock', 'expensetracker.view.expense.ThumbnailContainer' ],
	layout : {
		type : 'vbox',
		align : 'stretch',
		padding : '10 3 10 3'
	},	
	items : [ {
		xtype : 'thumnailcontainer',
		store : Ext.create('expensetracker.store.ExpenseDock'),
		listeners : {
			itemclick : 'onThumbnailClick'
		}
	} ]
});