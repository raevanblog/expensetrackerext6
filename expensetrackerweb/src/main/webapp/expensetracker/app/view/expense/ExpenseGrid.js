Ext.define('expensetracker.view.expense.ExpenseGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'view.expensegrid',
	xtype : 'expensegrid',
	scrollable : true,
	requires : [ 'expensetracker.store.Expense', 'Ext.grid.column.Column', 'Ext.grid.column.Widget', 'expensetracker.component.grid.column.CheckColumn', 'Ext.grid.column.Number' ],
	layout : 'fit',
	collapseFirst : false,
	plugins : [ {
		ptype : 'cellediting',
		clicksToEdit : 1,
		listeners : {
			beforeedit : function(editor, context, eOpts) {
				if (context.column.widget)
					return false;
			}
		}
	}, {
		ptype : 'gridfilters'
	} ],
	initComponent : function() {
		this.store = Ext.create('expensetracker.store.Expense');
		this.callParent();
	},
	features : [ {
		ftype : 'summary',
		dock : 'bottom'
	}, {
		ftype : 'grouping',
		startCollapsed : false,
		groupHeaderTpl : '{columnName} : {name} ({rows.length} Item{[values.rows.length > 1 ? "s" : ""]})'
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
		}, {
			xtype : 'button',
			reference : 'expensegridgroupby',
			text : 'Group By',
			ui : 'soft-green',
			menu : [ {
				xtype : 'checkbox',
				boxLabel : 'Date',
				handler : 'groupExpense'
			}, {
				xtype : 'checkbox',
				boxLabel : 'Category',
				handler : 'groupExpense'
			}, {
				xtype : 'checkbox',
				boxLabel : 'Expense Type',
				handler : 'groupExpense'
			} ]
		}, '-', {
			xtype : 'button',
			ui : 'toolbar',
			text : 'Expense',
			iconCls : 'x-fa fa-plus-square',
			handler : 'onAddExpenseRecord',
			tooltip : 'Add Expense'
		}, {
			xtype : 'button',
			ui : 'toolbar',
			text : 'Category',
			iconCls : 'x-fa fa-file-text',
			handler : 'onShowCategory',
			tooltip : 'Expense Category'
		}, {
			xtype : 'button',
			ui : 'toolbar',
			text : 'Income',
			iconCls : 'x-fa fa-plus-square',
			handler : 'onUpdateIncome'
		}, {
			xtype : 'button',
			ui : 'toolbar',
			text : 'Inventory',
			iconCls : 'x-fa fa-codepen',
			handler : 'onOpenInventory'
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
		text : 'S.No',
		width : 75
	}, {
		xtype : 'widgetcolumn',
		widget : {
			xtype : 'button',
			iconCls : 'x-fa fa-plus-square-o',
			tooltip : 'Add to Inventory',
			handler : 'addExpenseToInventory'
		}
	}, {
		text : 'Item Name',
		dataIndex : 'itemName',
		align : 'left',
		editor : {
			xtype : 'combobox',
			allowBlank : false,
			focusOnToFront : true,
			store : 'Item',
			displayField : 'name',
			valueField : 'name',
			typeAhead : true,
			queryMode : 'local',
			triggerAction : 'query',
			selectOnFocus : true,
			hideTrigger : true
		},
		width : 150
	}, {
		text : 'Category',
		align : 'left',
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
		filter : {
			type : 'list'
		},
		width : 150
	}, {
		text : 'Expense Type',
		align : 'left',
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
		filter : {
			type : 'list'
		},
		dataIndex : 'exptype',
		width : 150
	}, {
		xtype : 'datecolumn',
		align : 'left',
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
		text : 'Units',
		align : 'center',
		editor : {
			xtype : 'combobox',
			displayField : 'display',
			valueField : 'unit',
			store : 'Units',
			forceSelection : true,
			allowBlank : false,
			typeAhead : true,
			queryMode : 'local',
			selectOnFocus : true,
			triggerAction : 'all'
		},
		dataIndex : 'unit',
		width : 75
	}, {
		xtype : 'numbercolumn',
		align : 'center',
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
		text : 'Unit Price',
		align : 'center',
		format : '0.00',
		dataIndex : 'pricePerUnit',
		renderer : function(value) {
			return Ext.util.Format.currency(value, expensetracker.util.Session.getCurrencySymbol() + ' ', 2);
		},
		width : 150
	}, {
		xtype : 'actioncolumn',
		align : 'center',
		tooltip : 'Delete',
		handler : 'onDeleteExpense',
		iconCls : 'x-fa  fa-trash-o',
		flex : 1
	} ]
});