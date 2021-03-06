Ext.define('expensetracker.view.expense.ExpenseDock', {
	extend : 'Ext.panel.Panel',
	xtype : 'expensedock',
	alias : 'view.expensedock',
	iconCls : 'x-fa fa-file-text-o',
	requires : [ 'expensetracker.store.Calendar', 'expensetracker.view.expense.ThumbnailContainer' ],
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
				change : 'filterDock'
			}
		}, '-', {
			xtype : 'numberfield',
			reference : 'expenseyear',
			fieldLabel : 'Select Year',
			minValue : 2008,
			editable : false,
			maxValue : expensetracker.util.Calendar.getCurrentYear(),
			value : expensetracker.util.Calendar.getCurrentYear(),
			listeners : {
				change : 'onYearSelection'
			}
		} ]
	} ],
	items : [ {
		xtype : 'thumnailcontainer',
		store : 'Calendar',
		icontype : 'calendar',
		reference : 'thumbnaildocker',
		listeners : {
			itemclick : 'onThumbnailClick'
		}
	} ]
});