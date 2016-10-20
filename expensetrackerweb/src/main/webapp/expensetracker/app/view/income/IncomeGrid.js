Ext.define('expensetracker.view.income.IncomeGrid', {
	extend: 'Ext.grid.Panel',
	xtype: 'incomegrid',
	alias: 'view.incomegrid',
	requires : [ 'expensetracker.store.Income' ],
	plugins : {
		ptype : 'cellediting',
		clicksToEdit : 1
	},
	initComponent: function() {
		this.store = Ext.create('expensetracker.store.Income');
		this.callParent();
	},
	columns : [ {
		xtype : 'rownumberer',
		width : 50
	}, {
		text : 'Income Type',
		dataIndex : 'incometype',		
		flex : 1
	}, {
		text : 'Month',		
		flex : 1
	}, {
		xtype : 'numbercolumn',
		text : 'Year'
		flex : 1		
	},{
		xtype : 'numbercolumn',
		align : 'center',
		text : 'Income',
		format : '0.00',
		flex : 1
		dataIndex : 'qty',
		editor : {
			field : 'numberfield',
			allowBlank : false					
		},
		flex : 1
	}]
});