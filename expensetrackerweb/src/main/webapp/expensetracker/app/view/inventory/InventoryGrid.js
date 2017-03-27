Ext.define('expensetracker.view.inventory.InventoryGrid', {
	extend : 'Ext.grid.Panel',
	xtype : 'inventorygrid',
	alias : 'view.inventorygrid',
	requires : ['expensetracker.store.Inventory'],
	initComponent : function() {
		var me = this;
		me.store = Ext.create('expensetracker.store.Inventory');
		me.callParent();
	},
	plugins : {
		ptype : 'cellediting',
		clicksToEdit : 1,
		listeners : {
			beforeedit : function(editor , context , options) {
				var expenseId = context.record.get('expId');
				if(expenseId === undefined || expenseId === 0 || expenseId === null) {
					return true;
				}
				return false;
			}
		}
	},
	bbar : [ '->', {
		xtype : 'button',
		text : 'Save',
		ui : 'soft-green',
		iconCls : 'x-fa fa-save',
		handler : 'onSaveInventory'
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'top',
		overflowHandler : 'menu',
		items : [ {
			xtype : 'textfield',
			reference : 'inventorygridsearch',
			submitEmptyText : false,
			emptyText : 'Search...',
			listeners : {
				change : 'filterGrid'
			}
		}, '-', {
			xtype : 'button',
			ui : 'toolbar',
			text : 'Inventory',
			iconCls : 'x-fa fa-plus-square',
			handler : 'onAddInventory',
			tooltip : 'Add Inventory'
		}]
	} ],
	columns : [{
		xtype : 'rownumberer',
		text : 'S.No',
		width : 75
	}, {
		text : 'Item Name',
		dataIndex : 'itemName',
		align : 'left',
		editor : {
			xtype : 'combobox',
			allowBlank : false,
			focusOnToFront : true,
			store : 'Dictionary',
			displayField : 'itemName',
			valueField : 'itemName',
			typeAhead : true,
			queryMode : 'local',
			triggerAction : 'query',
			selectOnFocus : true,
			hideTrigger : true
		},
		width : 200
	}, {
		text : 'Category',
		align : 'left',
		dataIndex : 'category',		
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
		width : 200
	},{
		xtype : 'numbercolumn',
		text : 'Available Units',
		dataIndex : 'qty',
		align : 'center',
		format : '0.00',
		editor : {
			xtype : 'numberfield',
			hideTrigger : true,
			allowBlank : false,			
			selectOnFocus : true			
		},
		width : 200
	}, {
		xtype : 'actioncolumn',
		align : 'center',
		tooltip : 'Delete',
		handler : 'onDeleteInventory',
		iconCls : 'x-fa fa-trash-o',
		flex : 1
	}]
});