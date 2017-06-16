Ext.define('expensetracker.view.analyzer.ExpenseTrendWindow', {
	extend : 'Ext.window.Window',
	alias : 'view.expensetrendwindow',
	xtype : 'expensetrendwindow',
	requires : ['expensetracker.view.charts.ExpenseTrendPie', 'expensetracker.view.analyzer.ExpenseTrendController'],
	controller : 'expensetrendcontroller',	
	height : expensetracker.util.Constants.getWindowHeight(),
	width : expensetracker.util.Constants.getWindowWidth(),
	x : expensetracker.util.Constants.getWindowX(),
	y : expensetracker.util.Constants.getWindowY(),
	title : 'Expense Trend',
	layout : 'fit',
	constrain : true,
	modal : true,
	listeners : {
		afterrender : 'onRenderExpenseTrendWindow'
	},
	items : [{
		xtype : 'expensetrendpie'		
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
	},
	setGraphTitle : function(title) {
		var me = this;
		return me.items.items[0].setTitle(title);
	}
});