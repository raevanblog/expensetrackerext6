Ext.define('expensetracker.view.expense.ExpenseGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'view.expensegrid',
	xtype : 'expensegrid',
	scrollable: true,
	requires : [ 'expensetracker.store.Expense' ],
	layout : 'fit',	
	plugins : {
		ptype : 'cellediting',
		clicksToEdit : 1
	},
	initComponent : function() {
		this.store = Ext.create('expensetracker.store.Expense');
		this.callParent();
	},
	features : [ {
		ftype : 'summary',
		dock : 'bottom'
	} ],
	dockedItems: [{
		xtype: 'toolbar',
		dock: 'top',
		overflowHandler : 'menu',
		items: [ {
			xtype : 'textfield',
			reference : 'expensegridsearch',
			submitEmptyText : false,
			emptyText : 'Search...',
			listeners : {
				change : 'filterGrid'
			}
		}, '-',{
			xtype : 'button',
			ui : 'toolbar',
			text : 'Expense',
			iconCls : 'x-fa  fa-plus',
			handler : 'onAddExpenseRecord',
			tooltip : 'Add Expense'
		}, {
			xtype : 'button',
			ui : 'toolbar',
			text : 'Category',
			iconCls : 'x-fa fa-plus',
			handler : 'onShowCategory',
			tooltip : 'Add Category'
		}, {
			xtype : 'button',
			ui : 'toolbar',
			text : 'Income',
			iconCls : 'x-fa  fa-plus',			
			handler : 'onUpdateIncome'
		} ]
	}],	
	bbar : [ '->', {
		xtype : 'button',		
		text : 'Save',
		iconCls : 'x-fa fa-save',
		handler : 'onSaveOrUpdateExpense'
	} ],
	columns : [ {
		xtype : 'rownumberer',
		width : 50
	}, {
		text : 'Item Name',
		dataIndex : 'itemName',
		editor : {
			xtype : 'combobox',
			allowBlank : false,
			store : 'ExpenseName',
			displayField : 'itemName',
			valueField : 'itemName',
			typeAhead : true,
			queryMode : 'local',
			triggerAction : 'query',
			hideTrigger : true
		},
		width : 150
	}, {
		text : 'Category',
		editor : {
			xtype : 'combobox',
			displayField : 'category',
			valueField : 'category',
			store : 'ExpenseCategory',
			forceSelection : true,
			typeAhead : true,
			queryMode : 'local',
			triggerAction : 'all'			
		},
		dataIndex : 'category',
		width : 150
	}, {
		text : 'Expense Type',
		editor : {
			xtype : 'combobox',
			displayField : 'exptype',
			valueField : 'exptype',
			store : 'ExpenseType',
			forceSelection : true,
			typeAhead : true,
			queryMode : 'local',
			triggerAction : 'all'
		},
		dataIndex : 'exptype',
		width : 150
	}, {
		xtype : 'datecolumn',
		text : 'Expense Date',
		format : 'M d,Y',
		editor : {
			xtype : 'datefield',
			format : 'd/m/Y',
			bind : {
				minValue : '{expenseStartDate}',
				maxValue : '{expenseEndDate}'
			}
		},
		dataIndex : 'expdate',
		width : 150

	}, {
		xtype : 'numbercolumn',
		align : 'center',
		text : 'Quantity',
		format : '0.00',
		dataIndex : 'qty',
		editor : {
			field : 'numberfield',
			allowBlank : false,
			reference : 'gridQty',
			listeners : {
				change : 'onQtyChange'
			}
		},
		width : 100
	}, {
		xtype : 'numbercolumn',
		text : 'Price',
		format : '0.00',
		currencySymbol : expensetracker.util.Session.getCurrencySymbol(),
		dataIndex : 'price',
		renderer : function(value) {
			return Ext.util.Format.currency(value, expensetracker.util.Session.getCurrencySymbol() + ' ', 2);
		},
		editor : {
			field : 'numberfield',
			allowBlank : false,
			listeners : {
				change : 'onPriceChange'
			}
		},
		summaryType : 'sum',
		summaryRenderer : function(value, summaryData, dataIndex) {
			return 'Total  :' + Ext.util.Format.currency(value, expensetracker.util.Session.getCurrencySymbol() + ' ', 2);
		},
		width : 200
	}, {
		xtype : 'numbercolumn',
		text : 'Price/Unit',
		align : 'center',
		format : '0.00',
		dataIndex : 'pricePerUnit',
		width : 100
	}, {
		xtype : 'actioncolumn',
		align : 'center',
		tooltip : 'Delete',
		handler : 'onDeleteExpense',
		iconCls : 'x-fa  fa-minus-circle',
		flex : 1
	} ]
});