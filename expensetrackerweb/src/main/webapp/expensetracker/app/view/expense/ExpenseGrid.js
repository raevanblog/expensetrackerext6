Ext.define('expensetracker.view.expense.ExpenseGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'view.expensegrid',
	xtype : 'expensegrid',
	scrollable : true,
	requires : [ 'expensetracker.store.Expense', 'Ext.grid.column.Column', 'Ext.grid.column.Widget', 'expensetracker.component.grid.column.CheckColumn', 'Ext.grid.column.Number' ],
	layout : 'fit',
	plugins : {
		ptype : 'cellediting',
		clicksToEdit : 1,
		listeners: {
				beforeedit: function(editor, context, eOpts){
				if (context.column.widget)
				return false;
			}
		}
	},
	initComponent : function() {
		this.store = Ext.create('expensetracker.store.Expense');
		this.callParent();
	},
	features : [ {
		ftype : 'summary',
		dock : 'bottom'
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'top',
		overflowHandler : 'menu',
		items : [ {
			xtype : 'textfield',
			reference : 'expensegridsearch',
			submitEmptyText : false,
			emptyText : 'Search...',
			listeners : {
				change : 'filterGrid'
			}
		}, '-', {
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
	} ],
	bbar : [ '->', {
		xtype : 'button',
		ui : 'soft-green',
		text : 'Save',
		iconCls : 'x-fa fa-save',
		handler : 'onSaveOrUpdateExpense'
	} ],
	columns : [ {
		xtype : 'rownumberer',
		width : 50
	}, {
		xtype : 'widgetcolumn',
		editor: {}, 
		widget : {
			xtype : 'button',
			text : 'Add to Inventory',
			handler : 'addExpenseToInventory'			
		},
		width : 150
	}, {
		text : 'Item Name',
		dataIndex : 'itemName',
		editor : {
			xtype : 'combobox',
			allowBlank : false,
			focusOnToFront : true,
			store : 'ExpenseName',
			displayField : 'itemName',
			valueField : 'itemName',
			typeAhead : true,
			queryMode : 'local',
			triggerAction : 'query',
			selectOnFocus : true,
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
			allowBlank : false,
			typeAhead : true,
			queryMode : 'local',
			selectOnFocus : true,
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
			allowBlank : false,
			store : 'ExpenseType',
			forceSelection : true,
			typeAhead : true,
			queryMode : 'local',
			selectOnFocus : true,
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
			},
			selectOnFocus : true
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
			xtype : 'numberfield',
			hideTrigger : true,
			allowBlank : false,
			reference : 'gridQty',
			selectOnFocus : true,
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
			xtype : 'numberfield',
			hideTrigger : true,
			allowBlank : false,
			selectOnFocus : true,
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