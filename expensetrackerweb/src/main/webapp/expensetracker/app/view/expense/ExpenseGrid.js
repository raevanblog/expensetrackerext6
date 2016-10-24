Ext.define('expensetracker.view.expense.ExpenseGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'view.expensegrid',
	xtype : 'expensegrid',
	requires : [ 'expensetracker.store.Expense' ],
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
	tbar : [ {
		xtype : 'textfield',
		reference : 'expensegridsearch',
		submitEmptyText : false,
		emptyText : 'Search...',
		listeners : {
			change : 'filterGrid'
		}
	}, '-', {
		xtype : 'numberfield',
		emptyText : 'Monthly Income',
		labelStyle : 'font-weight: bold',
		labelSeparator : ''
	}, '->', {
		xtype : 'button',
		text : 'Expense',
		iconCls : 'x-fa  fa-plus-square',
		handler : 'onAddExpenseRecord',
		tooltip : 'Add Expense'
	}, {
		xtype : 'button',
		text : 'Category',
		iconCls : 'x-fa fa-book',
		handler : 'onShowCategory',
		tooltip : 'Add Category'
	} ],
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
		flex : 1
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
		flex : 1
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
		flex : 1
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
		flex : 1

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
		flex : 1
	}, {
		xtype : 'numbercolumn',
		text : 'Price',
		format : '0.00',
		currencySymbol : '₹',
		dataIndex : 'price',
		renderer : function(value) {
			return Ext.util.Format.currency(value, '₹', 2);
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
			return 'Total  :' + Ext.util.Format.currency(value, '₹ ', 2);
		},
		flex : 1
	}, {
		xtype : 'numbercolumn',
		text : 'Price/Unit',
		align : 'center',
		format : '0.00',
		dataIndex : 'pricePerUnit',
		flex : 1
	}, {
		xtype : 'actioncolumn',
		align : 'center',
		tooltip : 'Delete',
		handler : 'onDeleteExpense',
		iconCls : 'x-fa  fa-minus-circle'
	} ]
});