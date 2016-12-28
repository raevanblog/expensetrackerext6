Ext.define('expensetracker.view.expense.ExpenseViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expenseviewcontroller',
	onThumbnailClick : function(thumbnailcont, record, item, index, e) {
		var me = this;
		var model = me.getView().getViewModel();
		var refs = me.getReferences();
		var currentYear = expensetracker.util.Calendar.getCurrentYear();
		var currentMonth = expensetracker.util.Calendar.getCurrentMonthNo();
		var year = refs.expenseyear.getValue();
		var monthNo = record.get('monthNo');
		var month = record.get('month');

		var date = new Date(year, monthNo - 1);

		if (year === currentYear && monthNo > currentMonth) {
			Ext.Msg.show({
				title : 'Expense Tracker',
				message : 'Expense for future date cannot be created',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.INFO
			});
		} else {

			var expenseWindow = Ext.create('expensetracker.view.expense.ExpenseWindow', {
				height : Ext.Element.getViewportHeight(),
				width : Ext.Element.getViewportWidth(),
				resizable : false,
				modal : true
			});

			var model = expenseWindow.getViewModel();
			model.set('expenseStartDate', Ext.Date.getFirstDateOfMonth(date));
			model.set('expenseDate', Ext.Date.getFirstDateOfMonth(date));
			model.set('expenseEndDate', Ext.Date.getLastDateOfMonth(date));
			model.set('month', monthNo);
			model.set('year', year);
			model.set('title', month + ' - ' + year);

			expenseWindow.show();
		}
	},
	filterDock : function(textfield, newValue, oldValue, options) {
		var me = this;
		var docker = me.lookup('thumbnaildocker');
		var store = docker.getStore();
		store.filter('month', newValue);
	},
	onYearSelection : function(numberfield, newValue, oldValue, options) {
		var me = this;
		var expensedock = me.lookup('expensedock');
		expensedock.setTitle('' + newValue);
	},
	onRenderExpenseDock : function(expensedock) {
		var me = this;
		expensedock.setTitle('' + new Date().getFullYear());
	}
});