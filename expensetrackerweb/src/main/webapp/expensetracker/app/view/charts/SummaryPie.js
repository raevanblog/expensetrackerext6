Ext.define('expensetracker.view.charts.SummaryPie', {
	extend : 'Ext.panel.Panel',
	xtype : 'summarypie',
	alias : 'view.summarypie',
	layout : 'fit',
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
			type : 'pie3d',
			angleField : 'value',
			donut: 40,
			distortion: 0.6,
			highlight : {
				margin: 40
			},			
			tooltip : {
				trackMouse : true,
				renderer : function(tooltip, record, item) {
					var value = Ext.util.Format.number(record.get('value'), '0.00');
					tooltip.setHtml(record.get('item') + ' : ' + value + ' %');
				}
			}
		} ]
	} ]
});