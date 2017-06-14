Ext.define('expensetracker.view.analyzer.AnalyzerViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.analyzerviewcontroller',
	onShowPriceGraph : function(button) {
		var me = this;
		var itemcombo = me.lookup('itemcombo');
		var graphWindow = Ext.create('expensetracker.view.analyzer.PriceGraphWindow', {			
			viewModel : {				
				data : {
					itemName : itemcombo.getValue(),
					xField : 'year'
				}
			}
		});
		graphWindow.show();
	},
	onRenderItemCombo : function(combobox) {
		var store = expensetracker.util.Store.loadStore(Ext.create('expensetracker.store.Item'), {
			type : 'trackitems',
			username : expensetracker.util.Session.getUsername()
		});
		combobox.bindStore(store);
	},
	onCheckYear : function(checkbox, newValue, oldValue) {
		var me = this;
		var combobox = me.lookup('yearcombo');		
		combobox.setDisabled(!newValue);
		
	}
});