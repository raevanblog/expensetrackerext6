Ext.define('expensetracker.view.income.IncomeGrid', {
	extend : 'Ext.grid.Panel',
	xtype : 'incomegrid',
	alias : 'view.incomegrid',
	requires : [ 'expensetracker.store.Income' ],
	initComponent : function() {
		this.store = Ext.create('expensetracker.store.Income');
		this.callParent();
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
		width : 50
	}, {
		text : 'Income Type',
		dataIndex : 'incometype',
		align : 'center',
		flex : 1
	}, {
		xtype : 'numbercolumn',
		align : 'center',
		text : 'Income',
		format : '0.00',
		flex : 1,
		dataIndex : 'income',
		editor : {
			xtype : 'numberfield',
			hideTrigger : true,
			selectOnFocus : true,
			allowBlank : false
		},
		summaryType : 'sum',
		summaryRenderer : function(value, summaryData, dataIndex) {
			return 'Total  :' + Ext.util.Format.currency(value, '₹ ', 2);
		},
		flex : 1
	} ]
});