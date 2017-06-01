Ext.define('expensetracker.view.expense.ExpenseWindowController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensewindowcontroller',
	init : function(view) {
		var me = this;
		var grid = me.lookup('expensegrid');
		me.expenseGrouping = grid.view.findFeature('grouping');
	},
	onRender : function(expenseWindow) {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();		
	},
	groupExpense : function(checkbox, isChecked) {
		var me = this;
		var grid = me.lookup('expensegrid');
		var groupByBtn = me.lookup('expensegridgroupby');
		var items = groupByBtn.getMenu().getChildItemsToDisable();
		if (isChecked) {
			me.toggleGroupByMenuItems(items, checkbox, true);
			if ('Date' === checkbox.boxLabel) {
				grid.getStore().setGroupField('expdate');
			} else if ('Category' === checkbox.boxLabel) {
				grid.getStore().setGroupField('category');
			} else if ('Expense Type' === checkbox.boxLabel) {
				grid.getStore().setGroupField('exptype');
			}
			me.expenseGrouping.enable();
		} else {
			me.toggleGroupByMenuItems(items, checkbox, false);
			me.expenseGrouping.disable();
		}
	},
	toggleGroupByMenuItems : function(items, exception, disable) {
		Ext.Array.each(items, function(item) {
			if (item.boxLabel !== exception.boxLabel) {
				item.setDisabled(disable);
			}
		});
	},
	onCloseExpenseWindow : function(expenseWindow) {
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
						expenseWindow.clearListeners();
						expenseWindow.close();
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
	onRenderExpenseGrid : function(expensegrid, options) {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();
		var store = expensegrid.getStore();

		store.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				month : model.get('month'),
				year : model.get('year')
			},
			callback : function(records, operation, success) {
				if (!success) {
					var response = Ext.JSON.decode(operation.getError().response.responseText);
					expensetracker.util.Message.toast(response.message);
					if (401 === response.statusCode) {
						me.fireEvent('navigatelogin');
						if (view !== null) {
							view.close();
						}
					}
				}
			}
		});

		me.keyMap = Ext.create('Ext.util.KeyMap', expensegrid.el, [ {
			key : 'n',
			fn : function() {
				me.onAddExpenseRecord();
			},
			ctrl : true,
			alt : true,
			scope : me
		}, {
			key : 'r',
			fn : function() {
				expensetracker.util.Grid.refresh(expensegrid);
			},
			ctrl : true,
			alt : true,
			scope : me
		}, {
			key : 's',
			fn : function() {
				me.onSaveOrUpdateExpense();
			},
			ctrl : true,
			alt : true,
			scope : me
		}, {
			key : 46,
			ctrl : true,
			fn : function() {
				me.onDeleteExpenseBySelection();
			},
			scope : me
		} ]);
	},
	filterGrid : function(gridsearchtext, newValue, oldValue, options) {
		var me = this;
		var view = me.getView();
		var grid = view.getLayout().getActiveItem();
		var store = grid.getStore();
		if (gridsearchtext.reference === 'expensegridsearch') {
			store.filter('itemName', newValue);
		}
	},
	onAddExpenseRecord : function() {
		var me = this;
		var grid = me.lookup('expensegrid');
		var store = grid.getStore();
		var view = me.getView();
		var viewmodel = view.getViewModel();
		var model = new expensetracker.model.Expense({
			itemName : '',
			price : 0,
			expdate : viewmodel.get('expenseDate'),
			qty : 0,
			username : expensetracker.util.Session.getUsername()
		});
		store.insert(0, model);
		expensetracker.util.Grid.refresh(grid);
	},
	onQtyChange : function(gridqtytext, newValue, oldValue, options) {
		var me = this;
		var grid = me.lookup('expensegrid');
		var selection = grid.getSelectionModel().getSelection();
		var price = selection[0].get('price');
		var pricePerUnit = 0.0;
		if (newValue != 0.0) {
			pricePerUnit = price / newValue;
			selection[0].set('pricePerUnit', pricePerUnit);
		}
	},
	onPriceChange : function(gridqtytext, newValue, oldValue, options) {
		var me = this;
		var grid = me.lookup('expensegrid');
		var selection = grid.getSelectionModel().getSelection();
		var qty = selection[0].get('qty');
		var pricePerUnit = 0.0;
		if (qty != 0.0) {
			pricePerUnit = newValue / qty;
			selection[0].set('pricePerUnit', pricePerUnit);
		}
	},
	onSaveOrUpdateExpense : function() {
		var me = this;
		var model = me.getView().getViewModel();
		var grid = me.lookup('expensegrid');
		var store = grid.getStore();
		if (store.getModifiedRecords().length > 0 || store.getRemovedRecords().length > 0) {
			me.syncData(grid, false);
		}
	},
	onDeleteExpense : function(view, rowIndex, colIndex, item, e, record, row) {
		var me = this;
		var grid = me.lookup('expensegrid');
		var store = grid.getStore();
		store.remove(record);
		expensetracker.util.Grid.refresh(grid);
	},
	onDeleteExpenseBySelection : function() {
		var me = this;
		var expensegrid = me.lookup('expensegrid');
		var view = expensegrid.getView();
		var selectedRecords = view.getSelectionModel().getSelection();

		if (selectedRecords != null && selectedRecords.length > 0) {
			var store = expensegrid.getStore();
			store.remove(selectedRecords);
			expensetracker.util.Grid.refresh(expensegrid);
		}
	},
	onShowCategory : function(addCategBtn) {
		var me = this;
		var view = me.getView();
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
						me.syncData(component, false, function() {
							view.getLayout().next();
						});
					}
					if (button === 'no') {
						view.getLayout().next();
					}
				}
			});
		} else {
			view.getLayout().next();
		}
	},
	onReload : function(event) {
		var me = this;
		var view = me.getView();
		var grid = view.getLayout().getActiveItem();
		expensetracker.util.Grid.reload(grid);
	},
	syncData : function(grid, closeWindow, callback) {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();
		grid.setLoading("Saving...");
		grid.getStore().sync({
			success : function(batch) {
				grid.setLoading(false);
				if (model.get('origin') === 'dashboard') {
					me.fireEvent('updatedashboard');
				}
				if (closeWindow) {
					view.close();
				} else {
					expensetracker.util.Grid.reload(grid);
				}

				if (!closeWindow && callback !== undefined && callback !== null) {
					callback.call(this);
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
					if (401 === response.statusCode) {
						isUnauthorizedAccess = true;
						break;
					}
				}
				if (isUnauthorizedAccess) {
					expensetracker.util.Message.toast('Unauthorized Access');
					me.fireEvent('navigatelogin');
					if (view !== null) {
						view.clearListeners();
						view.close();
					}
				} else {
					expensetracker.util.Message.toast('Server Error');
				}
			}
		});
	},
	onUpdateIncome : function() {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();

		var incomeWindow = Ext.create('expensetracker.view.income.IncomeWindow', {
			height : Ext.Element.getViewportHeight(),
			width : Ext.Element.getViewportWidth(),
			modal : true
		});
		var incomeWindowModel = incomeWindow.getViewModel();
		incomeWindowModel.set('source', view);
		incomeWindowModel.set('month', model.get('month'));
		incomeWindowModel.set('year', model.get('year'));
		incomeWindowModel.set('title', model.get('title'));
		incomeWindow.show();
	},
	onOpenInventory : function() {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();

		var inventoryWindow = Ext.create('expensetracker.view.inventory.InventoryWindow', {
			height : Ext.Element.getViewportHeight(),
			width : Ext.Element.getViewportWidth(),
			modal : true
		});

		var inventoryWindowModel = inventoryWindow.getViewModel();
		inventoryWindowModel.set('month', model.get('month'));
		inventoryWindowModel.set('year', model.get('year'));
		inventoryWindowModel.set('title', 'Inventory ' + model.get('title'));

		inventoryWindow.show();
	},
	addExpenseToInventory : function(btn) {
		var me = this;
		var record = btn.getWidgetRecord();

		if (undefined === record.get('id') || null === record.get('id') || !Number.isInteger(record.get('id'))) {
			expensetracker.util.Message.toast('Save item before adding to Inventory.');
		} else {
			var grid = me.lookup('expensegrid');
			var view = me.getView();
			var viewModel = view.getViewModel();
			var requestArray = new Array();
			requestArray.push({
				itemName : record.get('itemName'),
				username : record.get('username'),
				category : record.get('category'),
				qty : record.get('qty'),
				unit : record.get('unit')
			});
			view.setLoading('Adding to Inventory...');
			Ext.Ajax.request({
				url : expensetracker.util.Url.getInventoryService(),
				method : 'POST',
				jsonData : Ext.JSON.encode(requestArray),
				success : function(response, opts) {
					view.setLoading(false);
					var response = Ext.decode(response.responseText);
					if (200 === response.statusCode) {
						grid.getStore().reload();
						expensetracker.util.Message.toast(record.get('itemName') + ' added to Inventory');
					}
				},
				failure : function(response, opts) {
					view.setLoading(false);
					expensetracker.util.Message.toast('Server Error');
				}
			});
		}
	}
});