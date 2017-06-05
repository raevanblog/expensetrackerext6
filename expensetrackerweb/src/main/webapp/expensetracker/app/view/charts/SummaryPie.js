Ext.define('expensetracker.view.charts.SummaryPie', {
	extend : 'Ext.panel.Panel',
	xtype : 'summarypie',
	alias : 'view.summarypie',
	layout : 'fit',	
	requires : ['expensetracker.view.charts.PieSeries3D'],
	items : [ {
		xtype : 'polar',
		itemId : 'summaryPolar',
		colors : ['#000000', '#35BAF6'],
		store : Ext.create('Ext.data.Store'),
		insetPadding : 5,
		innerPadding : 10,
		theme: 'Muted',
		interactions : [ 'itemhighlight', 'rotate' ],		
		series : [ {
			type : 'pieseries3d'
		} ]
	} ]
});