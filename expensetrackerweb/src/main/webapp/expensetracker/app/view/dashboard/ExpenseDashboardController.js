Ext.define('expensetracker.view.dashboard.ExpenseDashboardController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensedashboardcontroller',
	onRender: function(dashboard) {
		var me = this;
		var topexpense = me.lookup('topexpense');
		var sheet = me.lookup('expsheetdash');
		var piePanel = me.lookup('spie');
		var pie= piePanel.down('[itemId=summaryPolar]');
		var expSheetStore = Ext.create('expensetracker.store.ExpenseDock');
		expSheetStore.removeAll();
		
		var pieStore = Ext.create('Ext.data.Store');
		pieStore.add({item: 'Cash in Hand', value: 50000}, {item: 'Expense', value: 25000});
		pie.bindStore(pieStore);
		expSheetStore.add({
			month: expensetracker.util.Calendar.getCurrentMonth(),
			monthNo: expensetracker.util.Calendar.getCurrentMonthNo()
		});
		
		sheet.bindStore(expSheetStore);
		
		 var store = Ext.create('expensetracker.store.Expense');
		 store.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				month : expensetracker.util.Calendar.getCurrentMonthNo(),
				year : expensetracker.util.Calendar.getCurrentYear(),
				fetchTopExpense: true
			}
		});
		topexpense.bindStore(store);		
	},
	
	onOpenExpenseSheet: function(thumbnailcont, record, item, index, e) {
		
		var me = this;
		var currentYear = expensetracker.util.Calendar.getCurrentYear();
		var currentMonth = expensetracker.util.Calendar.getCurrentMonthNo();
		var date = new Date(currentYear, currentMonth-1);
		
		var expenseWindow = Ext.create('expensetracker.view.expense.ExpenseWindow', {
			height : Ext.getBody().getViewSize().height - 70,
			width : Ext.getBody().getViewSize().width - 250,
			x : me.getView().getX(),
			y : me.getView().getY(),
			modal : true
		});

			var model = expenseWindow.getViewModel();
			model.set('expenseStartDate', Ext.Date.getFirstDateOfMonth(date));
			model.set('expenseDate', Ext.Date.getFirstDateOfMonth(date));
			model.set('expenseEndDate', Ext.Date.getLastDateOfMonth(date));			
			model.set('month', currentMonth);
			model.set('year', currentYear);
			model.set('title', record.get('month') + '-' + currentYear);

			expenseWindow.show();
	},
	onIncomeEditClick: function() {
		var me = this;
		var incomeWindow = Ext.create('expensetracker.view.income.IncomeWindow', {
				height : this.getView().getHeight(),
				width : this.getView().getWidth(),
				x : me.getView().getX(),
				y : me.getView().getY(),
				modal : true
		});
		incomeWindow.show();
	}	
});