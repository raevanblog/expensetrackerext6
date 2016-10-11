Ext.define('expensetracker.view.dashboard.ExpenseDashboardController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensedashboardcontroller',
	onRender: function(dashboard) {
		var me = this;
		var topExpense = me.lookup('topexpensechart');
		 var store = Ext.create('expensetracker.store.Expense');
		 store.load({
			params : {
				username : 'shyamcse07',
				month : 9,
				year : 2016
			}
		});
		topExpense.bindStore(store);
	}
	
});