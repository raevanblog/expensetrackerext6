Ext.define('expensetracker.view.dashboard.ExpenseDashboardController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensedashboardcontroller',
	onRender: function(dashboard) {
		var me = this;
		var topExpense = me.lookup('expensechart');
		var summary = me.lookup('expensesummary');
		 var store = Ext.create('expensetracker.store.Expense');
		 store.load({
			params : {
				username : 'shyamcse07',
				month : 9,
				year : 2016
			}
		});
		summary.bindStore(store);
		topExpense.bindStore(store);
	}
	
});