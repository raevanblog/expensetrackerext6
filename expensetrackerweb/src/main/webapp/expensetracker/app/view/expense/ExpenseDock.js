Ext.define('expensetracker.view.expense.ExpenseDock', {
	extend : 'Ext.panel.Panel',
	xtype : 'expensedock',
	storeId : 'ExpenseDock',
	alias : 'view.expensedock',
	iconCls : 'x-fa fa-file-text-o',
	requires : [ 'expensetracker.store.ExpenseDock', 'expensetracker.view.expense.ThumbnailContainer' ],
	layout : {
		type : 'vbox',
		align : 'stretch',
		padding : '10 3 10 3'
	},
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'top',
		overflowHandler : 'menu',
		items : [ {
			xtype : 'textfield',
			reference : 'expensedocksearch',
			submitEmptyText : false,
			emptyText : 'Search...',
			listeners : {
				change : 'filterGrid'
			}
		}, '-', {
			xtype : 'numberfield',
			fieldLabel : 'Select Year',
			minValue : 2008,
			maxValue : expensetracker.util.Calendar.getCurrentYear(),
			value : expensetracker.util.Calendar.getCurrentYear()
		} ]
	} ],
	items : [ {
		xtype : 'thumnailcontainer',
		store : 'ExpenseDock',
		listeners : {
			itemclick : 'onThumbnailClick'
		}
	} ]
});