Ext.define('expensetracker.view.analyzer.AnalyzerViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.analyzerviewcontroller',
	onViewPriceGraph : function(view, rowIndex, colIndex, item, e, record, row) {
		var priceGraphWindow = Ext.create('expensetracker.view.analyzer.PriceGraph', {
			height : Ext.Element.getViewportHeight(),
			width : Ext.Element.getViewportWidth()
		});
		priceGraphWindow.show();
	},
	onRenderItemCombo : function(combobox) {
		var store = expensetracker.util.Store.loadStore(Ext.create('expensetracker.store.Item'), {
			type : 'trackitems',
			username : expensetracker.util.Session.getUsername()
		});
		combobox.bindStore(store);
	}
});