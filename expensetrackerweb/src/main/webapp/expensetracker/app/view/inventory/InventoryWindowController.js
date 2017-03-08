Ext.define('expensetracker.view.inventory.InventoryWindowController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.inventorycontroller',
	onCloseInventoryWindow : function(inventoryWindow) {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();
		var component = view.getLayout().getActiveItem();
		var store = component.getStore();
		if (store.getModifiedRecords().length > 0 || store.getRemovedRecords().length > 0) {
			Ext.Msg.show({
				title : 'Expense Tracker',
				message : 'Do you want to save your changes?',
				buttons : Ext.Msg.YESNO,
				icon : Ext.Msg.QUESTION,
				fn : function(button) {
					if (button === 'yes') {
						me.syncData(component, true);
					}
					if (button === 'no') {
						inventoryWindow.clearListeners();
						inventoryWindow.close();
					}
				}
			});
		} else {
			if (store.isFiltered()) {
				store.clearFilter();
			}
			return true;
		}
		return false;		
	},
	onRenderInventoryGrid : function(inventorygrid) {
		var me = this;
		var view = me.getView();
		var viewmodel = view.getViewModel();
		var store = inventorygrid.getStore();
		store.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				month : viewmodel.get('month'),
				year : viewmodel.get('year')
			},
			callback : function(records, operation, success) {
				if (!success) {					
					var response = Ext.JSON.decode(operation.getError().response.responseText)
					expensetracker.util.Message.toast(response.status_Message);
					if (401 === response.status_Code) {
						me.fireEvent('navigatelogin');
						if(view !== null) {
							view.close();
						}
					}
				}
			}
		});	
	},
	onReload : function() {
		var me = this;
		var view = me.getView();
		var grid = view.getLayout().getActiveItem();
		expensetracker.util.Grid.reload(grid);
	},
	onSaveInventory : function(saveBtn) {
		var me = this;
		var model = me.getView().getViewModel();
		var grid = me.lookup('inventorygrid');
		var store = grid.getStore();
		if (store.getModifiedRecords().length > 0 || store.getRemovedRecords().length > 0) {
			me.syncData(grid, false);
		}
	},
	onDeleteInventory : function(view, rowIndex, colIndex, item, e, record, row) {
		var me = this;
		var grid = me.lookup('inventorygrid');
		var store = grid.getStore();
		store.remove(record);
		expensetracker.util.Grid.refresh(grid);
	},
	syncData : function(grid, closeWindow) {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();
		grid.setLoading("Saving...");
		grid.getStore().sync({
			success : function(batch) {
				grid.setLoading(false);				
				if (closeWindow) {
					me.getView().close();
				} else {
					expensetracker.util.Grid.reload(grid);
				}
			},
			failure : function(batch) {
				var isUnauthorizedAccess = false;
				var operations = batch.getOperations();

				grid.setLoading(false);
				expensetracker.util.Grid.refresh(grid);

				for (var i = 0; i < operations.length; i++) {
					var operation = operations[i];
					var response = Ext.JSON.decode(operation.getError().response.responseText);
					if (401 === response.status_Code) {
						isUnauthorizedAccess = true;
						break;
					}
				}
				if (isUnauthorizedAccess) {
					expensetracker.util.Message.toast('Unauthorized Access');
					if(model.get('source') != null) {
						model.get('source').destroy();
					}
					me.fireEvent('navigatelogin');
					if(view !== null) {
						view.clearListeners();
						view.close();
					}					
				} else {
					expensetracker.util.Message.toast('Server Error');
				}
			}
		});
	},
	filterGrid : function(gridsearchtext, newValue, oldValue, options) {
		var me = this;
		var view = me.getView();
		var grid = view.getLayout().getActiveItem();
		var store = grid.getStore();
		if (gridsearchtext.reference === 'inventorygridsearch') {
			store.filter('itemName', newValue);
		}
	},
	onAddInventory : function() {
		var me = this;
		var grid = me.lookup('inventorygrid');
		var store = grid.getStore();
		var view = me.getView();
		var viewmodel = view.getViewModel();
		var model = new expensetracker.model.Inventory({
			itemName : '',
			expId : null,
			qtyAvailable : 0,
			category : '',
			mth : viewmodel.get('month'),
			yr : viewmodel.get('year'),
			username : expensetracker.util.Session.getUsername()
		});		
		store.insert(0, model);
		expensetracker.util.Grid.refresh(grid);
	}
});