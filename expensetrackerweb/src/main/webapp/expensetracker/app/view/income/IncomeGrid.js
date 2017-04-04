Ext.define('expensetracker.view.income.IncomeGrid', {
	extend : 'Ext.grid.Panel',
	xtype : 'incomegrid',
	alias : 'view.incomegrid',
	requires : [ 'expensetracker.store.Income' ],
	initComponent : function() {
		var me = this;
		me.store = Ext.create('expensetracker.store.Income');
		me.callParent();
	},
	plugins : {
		ptype : 'cellediting',
		clicksToEdit : 1
	},
	features : [ {
		ftype : 'summary',
		dock : 'bottom'
	} ],
	bbar : [ '->', {
		xtype : 'button',
		text : 'Save',
		ui : 'soft-green',
		iconCls : 'x-fa fa-save',
		handler : 'onSaveIncome'
	} ],
	columns : [ {
		xtype : 'rownumberer',
		text : 'S.No',
		width : 75
	}, {
		text : 'Income Type',
		dataIndex : 'incometype',
		align : 'center',
		flex : 1
	}, {
		xtype : 'numbercolumn',
		align : 'center',
		text : 'Income',
		flex : 1,
		dataIndex : 'income',
		renderer : function(value) {
			return Ext.util.Format.currency(value, expensetracker.util.Session.getCurrencySymbol() + ' ', 2);
		},
		editor : {
			xtype : 'numberfield',
			hideTrigger : true,
			selectOnFocus : true,
			allowBlank : false
		},
		summaryType : 'sum',
		summaryRenderer : function(value, summaryData, dataIndex) {
			return 'Total  :' + Ext.util.Format.currency(value, expensetracker.util.Session.getCurrencySymbol() + ' ', 2);
		},
		flex : 1
	} ]
});