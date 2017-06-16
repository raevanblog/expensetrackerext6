Ext.define('expensetracker.view.analyzer.PriceGraphController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.pricegraphcontroller',
	onRenderPriceGraphWindow : function(pricegraphwindow) {
		var me = this;
		var model = me.getView().getViewModel();
		var store = Ext.create('expensetracker.store.Graph');
		me.loadPriceGraphStore(store, 'PRICE_GRAPH',  model.get('itemName'));		
		pricegraphwindow.bindGraphStore(store);		
	},
	onRenderYearCombo : function(combobox) {
		var me = this;
		var model = me.getView().getViewModel();
		var yearstore = Ext.create('expensetracker.store.Year');
		expensetracker.util.Store.loadStore(yearstore, {
			username : expensetracker.util.Session.getUsername(),
			name : model.get('itemName')
		});
		combobox.bindStore(yearstore);
	},
	onCheckYear : function(checkbox, newValue, oldValue) {
		var me = this;
		var model = me.getView().getViewModel();
		var combobox = me.lookup('yearcombo');		
		combobox.setDisabled(!newValue);
		if(newValue) {
			if(combobox.getValue() != null) {
				me.onSelectExpenseYear(combobox, combobox.getValue());
			}
		} else {
			var store = me.getView().getGraphStore();
			model.set('xField', 'year');
			me.loadPriceGraphStore(store, 'PRICE_GRAPH',  model.get('itemName'));
		}
	},
	onSelectExpenseYear : function(combobox, newValue, oldValue) {		
		if(newValue != null) {
			var me = this;
			var model = me.getView().getViewModel();
			var store = me.getView().getGraphStore();
			model.set('xField', 'monthname');
			me.loadPriceGraphStore(store, 'PRICE_GRAPH_YEARLY',  model.get('itemName'), newValue);
		}
	},	
	loadPriceGraphStore :  function(store, graphtype, itemname, year) {
		store.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				name : itemname,
				type : graphtype,
				year : year
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