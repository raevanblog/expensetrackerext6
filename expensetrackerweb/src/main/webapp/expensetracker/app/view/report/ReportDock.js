Ext.define('expensetracker.view.report.ReportDock', {
	extend : 'Ext.panel.Panel',
	xtype : 'reportdock',	
	alias : 'view.reportdock',
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
			reference : 'reportdocksearch',
			submitEmptyText : false,
			emptyText : 'Search...',
			listeners : {
				change : 'filterDock'
			}
		}, '-', {
			xtype : 'numberfield',
			reference : 'reportyear',
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
		icontype : 'pdf',
		reference : 'reportthumbdocker',
		listeners : {
			itemclick : 'onThumbnailClick'
		}
	} ]
});