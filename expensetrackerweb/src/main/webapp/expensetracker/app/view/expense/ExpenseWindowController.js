Ext.define('expensetracker.view.expense.ExpenseWindowController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensewindowcontroller',
	onRender : function(window) {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();
		if(expensetracker.util.Calendar.isCurrentMonth(model.get('month')) && expensetracker.util.Calendar.isCurrentYear(model.get('year'))) {
			model.set('isLatestExpense', true);
		}
	},
	onCloseExpenseWindow : function(window) {
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
						component.setLoading('Saving...');
						if (store.isFiltered()) {
							store.clearFilter();
						}
						store.sync({
							success : function(batch) {
								component.setLoading(false);
								if(model.get('isLatestExpense')) {
									me.fireEvent('updatesummary');
								}
								window.clearListeners();								
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
		var model = view.getViewModel();
		var model = new expensetracker.model.Expense({
			itemName : '',
			price : 0.0,
			expdate : model.get('expenseDate'),
			qty : 0.0,
			username : expensetracker.util.Session.getUsername()
		});
		store.insert(0, model);
		me.refreshGridView(grid);
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
			grid.setLoading("Saving...");
			store.sync({
				success : function(batch) {
					grid.setLoading(false);	
					if(model.get('isLatestExpense')){
						me.fireEvent('updatesummary');
					}
					me.refreshGridView(grid);
				},
				failure : function(batch) {
					grid.setLoading(false);
					me.refreshGridView(grid);
				}
			});
		}
	},
	onDeleteExpense : function(view, rowIndex, colIndex, item, e, record, row) {
		var me = this;
		var grid = me.lookup('expensegrid');
		var store = grid.getStore();
		store.remove(record);
		me.refreshGridView(grid);
	},
	onShowCategory : function(addCategBtn) {
		var me = this;
		var view = me.getView();
		view.getLayout().next();
	},
	onReload : function(event) {
		var me = this;
		var view = me.getView();
		var component = view.getLayout().getActiveItem();
		component.getStore().reload();
	},
	refreshGridView : function(grid) {
		grid.getView().refresh();
	}
});