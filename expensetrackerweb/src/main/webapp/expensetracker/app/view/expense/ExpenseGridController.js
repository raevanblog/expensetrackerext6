Ext.define('expensetracker.view.expense.ExpenseGridController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensegridcontroller',
	filterGrid : function(gridsearchtext, newValue, oldValue, options) {
		var grid = this.lookup('expensegrid');
		grid.getStore().filter('itemName', newValue);
	},
	onAddExpenseRecord : function(addexpenseBtn) {
		var me = this;
		var grid = me.getView();
		var store = grid.getStore();
		var model = new expensetracker.model.Expense({
			itemName : '',
			expdate : new Date(),
			price : 0.0,
			qty : 0.0,
			username : 'shyamcse07'
		});
		store.insert(0, model);
		grid.getView().refresh();
	},
	onQtyChange : function(gridqtytext, newValue, oldValue, options) {
		var me = this;
		var grid = me.getView();
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
		var grid = me.getView();
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
		var grid = me.getView();
		grid.setLoading("Saving...")
		grid.getStore().sync({
			success: function(batch) {
				grid.setLoading(false)
			},
			failure: function(batch) {
				grid.setLoading(false);				
				grid.getStore().rejectChanges();
			}
		});
	},
	onDeleteExpense : function(view, rowIndex, colIndex, item, e, record, row) {
		var me = this;
		var grid = me.getView();
		var store = grid.getStore();
		store.remove(record);		
	}
});