Ext.define('expensetracker.view.income.IncomeWindowController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.incomecontroller',	
	onRender : function(window) {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();
		if (expensetracker.util.Calendar.isCurrentMonth(model.get('month')) && expensetracker.util.Calendar.isCurrentYear(model.get('year'))) {
			model.set('isLatestIncome', true);
		}
	},
	onCloseIncomeWindow : function(incomeWindow) {
		var me = this;
		var view = me.getView();
		var model = me.getViewModel();
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
						incomeWindow.clearListeners();
						incomeWindow.close();
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
	onRenderIncomeGrid : function(incomegrid) {
		var me = this;
		var view = me.getView();
		var viewmodel = view.getViewModel();
		var store = incomegrid.getStore();
		store.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				month : viewmodel.get('month'),
				year : viewmodel.get('year')
			},
			callback : function(records, operation, success) {
				if (success) {
					if (records.length === 0) {
						var typeStore = Ext.getStore('IncomeType');
						typeStore.each(function(record) {
							var incomeType = record.get('incometype');
							var model = new expensetracker.model.Income({
								incometype : incomeType,
								mth : viewmodel.get('month'),
								yr : viewmodel.get('year'),
								income : 0.0,
								username : expensetracker.util.Session.getUsername()
							});
							store.add(model);
						});
						incomegrid.getView().refresh();
					}
				} else {
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
	onSaveIncome : function(saveBtn) {
		var me = this;
		var model = me.getView().getViewModel();
		var grid = me.lookup('incomegrid');
		var store = grid.getStore();
		if (store.getModifiedRecords().length > 0 || store.getRemovedRecords().length > 0) {
			me.syncData(grid, false);
		}
	},
	onReload : function() {
		var me = this;
		var view = me.getView();
		var grid = view.getLayout().getActiveItem();
		expensetracker.util.Grid.reload(grid);
	},
	syncData : function(grid, closeWindow) {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();
		grid.setLoading("Saving...");
		grid.getStore().sync({
			success : function(batch) {
				grid.setLoading(false);
				if (model.get('isLatestIncome')) {
					me.fireEvent('updatedashboard');
				}
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
	}	
});