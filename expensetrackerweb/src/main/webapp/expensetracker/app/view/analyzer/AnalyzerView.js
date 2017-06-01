Ext.define('expensetracker.view.analyzer.AnalyzerView',{
	extend : 'Ext.container.Container',
	xtype : 'analyzerview',
	alias : 'view.analyzerview',
	layout : 'responsivecolumn',
	controller : 'analyzerviewcontroller',
	requires :  ['expensetracker.view.analyzer.ItemDetails', 'expensetracker.view.analyzer.AnalyzerViewController'],
	items : [{
		xtype : 'itemdetails',
		height : 450,
		cls : 'big-100 small-100 dash-panel shadow'
	}] 
});