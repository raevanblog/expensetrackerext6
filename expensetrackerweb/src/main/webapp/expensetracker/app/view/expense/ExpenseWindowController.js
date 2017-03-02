Ext.define('expensetracker.view.expense.ExpenseWindowController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensewindowcontroller',
	onRender : function(expenseWindow) {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();
		if (expensetracker.util.Calendar.isCurrentMonth(model.get('month')) && expensetracker.util.Calendar.isCurrentYear(model.get('year'))) {
			model.set('isLatestExpense', true);
		}
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
					expensetracker.util.Message.toast(response.status_Message);
					if (401 === response.status_Code) {
						me.fireEvent('navigatelogin');
						if (view !== null) {
							view.close();
						}
					}
				}
			}
		});
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
	onAddExpenseRecord : function(addexpenseBtn) {
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
			inventoryInd: 'N',
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
	onSaveOrUpdateExpense : function(saveBtn) {
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
				if (model.get('isLatestExpense')) {
					me.fireEvent('updatedashboard');
				}
				if (closeWindow) {
					view.close();
				} else {					
					expensetracker.util.Grid.reload(grid);
				}
				
				if(!closeWindow && callback !== undefined && callback !== null) {
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
					if (401 === response.status_Code) {
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
	onOpenInventory	:  function() {
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
		
		if(undefined === record.get('id') || null === record.get('id') || !Number.isInteger(record.get('id'))) {
			expensetracker.util.Message.toast('Save expense before adding to Inventory.');
		} else if('Y' === record.get('inventoryInd')) {
			expensetracker.util.Message.toast(record.get('itemName') + " is already in Inventory");
		} else {
			var grid = me.lookup('expensegrid');
			var view = me.getView();
			var viewModel = view.getViewModel();
			var requestArray = new Array();		
			requestArray.push({
				expId : record.get('id'),
				itemName : record.get('itemName'),
				username : record.get('username'),
				category : record.get('category'),
				qty : record.get('qty'),
				yr : viewModel.get('year'),
				mth : viewModel.get('month')
			});
			view.setLoading('Adding to Inventory...');
			Ext.Ajax.request({
				url : expensetracker.util.Url.getInventoryService(),
				method : 'POST',
				jsonData : Ext.JSON.encode(requestArray),
				success : function(response, opts) {
					view.setLoading(false);
					var response = Ext.decode(response.responseText);
					if(200 === response.status_Code) {						
						grid.getStore().reload();
						expensetracker.util.Message.toast( record.get('itemName') + ' added to Inventory');
					}
				},
				failure : function(response, opts) {
					view.setLoading(false);
					expensetracker.util.Message.alert('Expense Tracker', 'Server Error');
				}
			});
		}
	},
	refreshGridView : function(grid) {
		grid.getView().refresh();
	}
});