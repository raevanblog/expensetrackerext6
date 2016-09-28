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
				message : 'Do you want to save your changes?',
				buttons : Ext.Msg.YESNO,
				icon : Ext.Msg.QUESTION,
				fn : function(button) {
					if (button === 'yes') {
						expenseGrid.setLoading('Saving...');
						if(store.isFiltered()){
							store.clearFilter();							
						}
						store.sync({
							success: function(batch) {
								expenseGrid.setLoading(false);
								window.purgeListeners();
								window.close();
							},
							failure: function(batch) {
								expenseGrid.setLoading(false);								
								store.rejectChanges();
								me.refreshGridView(expenseGrid);
							}
						})
						
					}
					if (button === 'no') {
						return false;
					}
				}
			});
		} else {
			if(store.isFiltered()){
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
		var grid = me.lookup('expensegrid');
		var store = grid.getStore();
		if (store.getModifiedRecords().length > 0 || store.getRemovedRecords().length > 0) {
			grid.setLoading("Saving...");
			store.sync({
				success : function(batch) {
					grid.setLoading(false);
					me.refreshGridView(grid);
				},
				failure : function(batch) {
					grid.setLoading(false);					
					store.rejectChanges();
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
	onReload: function(event) {
		var me = this;
		var view = me.getView();
		var component = view.getLayout().getActiveItem();
		component.getStore().reload();				
	},
	refreshGridView(grid) {
		grid.getView().refresh();
	}
});