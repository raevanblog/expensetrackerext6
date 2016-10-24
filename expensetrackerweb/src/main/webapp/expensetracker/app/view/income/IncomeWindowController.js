Ext.define('expensetracker.view.income.IncomeWindowController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.incomewindowcontroller',
	id: 'incomewindowcontroller',
	onCloseIncomeWindow : function(window) {
		var me = this;
		console.log(me);
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
						component.setLoading('Saving...');
						if (store.isFiltered()) {
							store.clearFilter();
						}
						store.sync({
							success : function(batch) {
								component.setLoading(false);
								window.clearListeners();
								me.fireEvent('updatesummary');
								window.close();
							},
							failure : function(batch) {
								component.setLoading(false);
								me.refreshGridView(component);
							}
						})

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
		var store = incomegrid.getStore();
		store.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				month : expensetracker.util.Calendar.getCurrentMonthNo(),
				year : expensetracker.util.Calendar.getCurrentYear()
			},
			callback : function(records, operation, success) {
				if (success) {
					if (records.length === 0) {
						var typeStore = Ext.getStore('IncomeType');
						typeStore.each(function(record) {
							var incomeType = record.get('incometype');
							var model = new expensetracker.model.Income({
								incometype : incomeType,
								mth : expensetracker.util.Calendar.getCurrentMonthNo(),
								yr : expensetracker.util.Calendar.getCurrentYear(),
								income : 0.0,
								username : expensetracker.util.Session.getUsername()
							});
							store.add(model);
						});
						incomegrid.getView().refresh();
					}
				}
			}
		});

	},
	onSaveIncome : function(saveBtn) {
		var me = this;
		var grid = me.lookup('incomegrid');
		var store = grid.getStore();
		if (store.getModifiedRecords().length > 0 || store.getRemovedRecords().length > 0) {
			grid.setLoading("Saving...");
			store.sync({
				success : function(batch) {
					grid.setLoading(false);
					me.fireEvent('updatesummary');					
					me.refreshGridView(grid);
				},
				failure : function(batch) {
					grid.setLoading(false);
					me.refreshGridView(grid);
				}
			});
		}
	},
	onReload : function() {
		var me = this;
		var view = me.getView();
		var component = view.getLayout().getActiveItem();
		component.getStore().reload();
	},
	refreshGridView : function(grid) {
		grid.getView().refresh();
	}
});