Ext.define('expensetracker.view.analyzer.ExpenseTrendController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensetrendcontroller',
	onRenderExpenseTrendWindow : function(expensetrendwindow) {
		var me = this;
		var model = me.getView().getViewModel();
		var store = Ext.create('expensetracker.store.Graph');
		me.loadPriceGraphStore(store, 'EXPENSE_TREND',  model.get('year'));
		expensetrendwindow.setGraphTitle('Expense Trend - ' + model.get('year'));
		expensetrendwindow.bindGraphStore(store);		
	},
	onRenderMonthCombo : function(combobox) {
		var me = this;
		var model = me.getView().getViewModel();
		var monthstore = Ext.create('expensetracker.store.Month');
		expensetracker.util.Store.loadStore(monthstore, {
			username : expensetracker.util.Session.getUsername(),
			year : model.get('year')
		});
		combobox.bindStore(monthstore);
	},
	onCheckMonth : function(checkbox, newValue, oldValue) {
		var me = this;
		var model = me.getView().getViewModel();
		var combobox = me.lookup('monthcombo');		
		combobox.setDisabled(!newValue);
		if(newValue) {
			if(combobox.getValue() != null) {
				me.onSelectExpenseMonth(combobox, combobox.getValue());
			}
		} else {
			var store = me.getView().getGraphStore();
			me.loadPriceGraphStore(store, 'EXPENSE_TREND',  model.get('year'));
			me.getView().setGraphTitle('Expense Trend - ' + model.get('year'));			
		}
	},
	onSelectExpenseMonth : function(combobox, newValue, oldValue) {		
		if(newValue != null) {
			var me = this;
			var model = me.getView().getViewModel();
			var store = me.getView().getGraphStore();			
			me.loadPriceGraphStore(store, 'EXPENSE_TREND_MONTHLY',  model.get('year'), combobox.getValue());
			me.getView().setGraphTitle('Expense Trend - ' + expensetracker.util.Calendar.getName(combobox.getValue()) + ',' + model.get('year'));
		}
	},	
	loadPriceGraphStore :  function(store, graphtype, year, month) {
		store.load({
			params : {
				username : expensetracker.util.Session.getUsername(),				
				type : graphtype,
				year : year,
				month : month
			},
			callback : function(records, operation, success) {			
				if (!success) {
					var response = Ext.JSON.decode(operation.getError().response.responseText);
					expensetracker.util.Message.toast(response.message);
					if (401 === response.statusCode) {
						me.fireEvent('navigatelogin');						
					}
				}
			}
		});
	}
});