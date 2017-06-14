Ext.define('expensetracker.view.analyzer.PriceGraphWindow', {
	extend : 'Ext.window.Window',
	alias : 'view.pricegrapthwindow',
	xtype : 'pricegraphwindow',
	requires : ['expensetracker.view.charts.PriceGraph', 'expensetracker.view.analyzer.PriceGraphController'],
	controller : 'pricegraphcontroller',	
	height : expensetracker.util.Constants.getWindowHeight(),
	width : expensetracker.util.Constants.getWindowWidth(),
	x : expensetracker.util.Constants.getWindowX(),
	y : expensetracker.util.Constants.getWindowY(),
	title : 'Price Graph',
	layout : 'fit',
	constrain : true,
	modal : true,
	listeners : {
		afterrender : 'onRenderPriceGraphWindow'
	},
	items : [{
		xtype : 'pricegraph'		
	}],
	bindGraphStore : function(store) {
		var me = this;
		me.items.items[0].bindStore(store);
	},
	addGraphSeries : function(series) {
		var me = this;
		me.items.items[0].addSeries(series);
	},
	getGraphStore : function() {
		var me = this;
		return me.items.items[0].getStore();
	}
});