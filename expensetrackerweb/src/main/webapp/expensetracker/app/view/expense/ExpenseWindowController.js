Ext.define('expensetracker.view.expense.ExpenseWindowController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensewindowcontroller',
	onCloseExpenseWindow : function(window) {
		var me = this;
		var expenseGrid = me.lookup('expensegrid');
		var store = expenseGrid.getStore();
		if (store.getModifiedRecords().length > 0 || store.getRemovedRecords().length > 0) {
			Ext.Msg.show({
				title : 'Expense Tracker',
				message : 'You have unsaved changes. Do you want close and discard your changes?',
				buttons : Ext.Msg.YESNO,
				icon : Ext.Msg.QUESTION,
				fn : function(button) {
					if (button === 'yes') {
						store.clearFilter();
						window.purgeListeners();
						window.close();
					}
					if (button === 'no') {
						return false;
					}
				}
			});
		} else {
			store.clearFilter();
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
				username : model.get('username'),
				month : model.get('month'),
				year : model.get('year')
			}
		});
	},
	filterGrid : function(gridsearchtext, newValue, oldValue, options) {
		var me = this;
		var grid = me.lookup('expensegrid');
		grid.getStore().filter('itemName', newValue);
	},
	onAddExpenseRecord : function(addexpenseBtn) {
		var me = this;
		var grid = me.lookup('expensegrid');
		var store = grid.getStore();
		var model = new expensetracker.model.Expense({
			itemName : '',
			price : 0.0,
			qty : 0.0,
			username : 'shyamcse07'
		});
		store.insert(0, model);
		grid.getView().refresh();
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
		var grid = me.lookup('expensegrid');
		grid.setLoading("Saving...")
		grid.getStore().sync({
			success : function(batch) {
				grid.setLoading(false)
			},
			failure : function(batch) {
				grid.setLoading(false);
				grid.getStore().rejectChanges();
			}
		});
	},
	onDeleteExpense : function(view, rowIndex, colIndex, item, e, record, row) {
		var me = this;
		var grid = me.lookup('expensegrid');
		var store = grid.getStore();
		store.remove(record);
	},
	onAddCategory : function(addCategBtn) {
		var me = this;
		var view = me.getView();
		view.getLayout().next();
	},
	onBackCategory : function(backCategBtn) {
		var me = this;
		var view = me.getView();
		var store = Ext.getStore('ExpenseCategory');
		
		if (store.getModifiedRecords().length > 0 || store.getRemovedRecords().length > 0) {
			Ext.Msg.show({
				title : 'Expense Tracker',
				message : 'You have unsaved changes. Do you want close and discard your changes?',
				buttons : Ext.Msg.YESNO,
				icon : Ext.Msg.QUESTION,
				fn : function(button) {
					if (button === 'yes') {
						store.clearFilter();
						window.purgeListeners();
						window.close();
					}
					if (button === 'no') {
						return false;
					}
				}
			});
		} else {
			store.clearFilter();
			return true;
		}
		
		view.getLayout().prev();
	},
});