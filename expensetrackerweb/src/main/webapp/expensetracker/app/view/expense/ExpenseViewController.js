Ext.define('expensetracker.view.expense.ExpenseViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expenseviewcontroller',
	onThumbnailClick : function(thumbnailcont, record, item, index, e) {
		var me = this;
		var refs = me.getReferences();
		var currentYear = expensetracker.util.Calendar.getCurrentYear();
		var currentMonth = expensetracker.util.Calendar.getCurrentMonthNo();
		var year = parseInt(refs.expensedock.getTitle());
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
				height : this.getView().getHeight(),
				width : this.getView().getWidth(),
				x : me.getView().getX(),
				y : me.getView().getY(),
				modal : true
			});

			var model = expenseWindow.getViewModel();
			model.set('expenseStartDate', Ext.Date.getFirstDateOfMonth(date));
			model.set('expenseDate', Ext.Date.getFirstDateOfMonth(date));
			model.set('expenseEndDate', Ext.Date.getLastDateOfMonth(date));
			model.set('username', 'shyamcse07');
			model.set('month', monthNo);
			model.set('year', year);
			model.set('title', month + '-' + year);

			expenseWindow.show();
		}
	},
	onYearSelection : function(slider, newValue, thumb, eOpts) {
		var me = this;
		var refs = me.getReferences();
		refs.expensedock.setTitle('' + newValue);
		refs.expviewselyear.update(newValue);
	},
	onRenderSlider : function(slider) {
		var me = this;
		var value = slider.getValue();
		var refs = me.getReferences();
		refs.expviewselyear.update(value);
	},
	onRenderExpenseDock : function(expensedock) {
		var me = this;
		expensedock.setTitle('' + new Date().getFullYear());
	}
});