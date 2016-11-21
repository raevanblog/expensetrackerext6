Ext.define('expensetracker.view.income.IncomeWindowController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.incomewindowcontroller',
	id : 'incomewindowcontroller',
	onRender : function(window) {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();
		if (expensetracker.util.Calendar.isCurrentMonth(model.get('month')) && expensetracker.util.Calendar.isCurrentYear(model.get('year'))) {
			model.set('isLatestIncome', true);
		}
	},
	onCloseIncomeWindow : function(window) {
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
						window.clearListeners();
						window.close();
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
	onRenderIncomeGrid : function(incomegrid, options) {
		var me = this;
		var viewmodel = me.getView().getViewModel();
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
						me.getView().close();
						me.fireEvent('navigatelogin');
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
		var component = view.getLayout().getActiveItem();
		component.getStore().reload();
	},
	syncData : function(grid, closeWindow) {
		var me = this;
		var model = me.getView().getViewModel();
		grid.setLoading("Saving...");
		grid.getStore().sync({
			success : function(batch) {
				grid.setLoading(false);
				if (model.get('isLatestIncome')) {
					me.fireEvent('updatesummary');
				}
				if (closeWindow) {
					me.getView().close();
				} else {
					me.refreshGridView(grid);
				}
			},
			failure : function(batch) {
				var isUnauthorizedAccess = false;
				var operations = batch.getOperations();

				grid.setLoading(false);
				me.refreshGridView(grid);

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
					model.get('source').destroy();
					me.getView().clearListeners();
					me.getView().close();					
					me.fireEvent('navigatelogin');
				} else {
					expensetracker.util.Message.toast('Server Error');
				}
			}
		});
	},
	refreshGridView : function(grid) {
		grid.getView().refresh();
	}
});