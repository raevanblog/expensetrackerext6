Ext.define('expensetracker.view.charts.SummaryPie', {
	extend : 'Ext.panel.Panel',
	xtype : 'summarypie',
	alias : 'view.summarypie',
	layout : 'fit',
	items : [ {
		xtype : 'polar',
		itemId : 'summaryPolar',
		theme : 'default-gradients',
		store : Ext.create('Ext.data.Store'),
		insetPadding : 10,
		innerPadding : 20,
		interactions : [ 'rotate' ],
		legend : {
			docked : 'bottom'
		},
		series : [ {
			type : 'pie',
			angleField : 'value',
			label : {
				field : 'item',
				fontSize : '10px',
				display : 'vertical',
				calloutLine : {
					length : 35,
					width : 3
				}
			},
			tooltip : {
				trackMouse : true,
				renderer : function(tooltip, record, item) {
					var value = Ext.util.Format.number(record.get('value'), '0.00');
					tooltip.setHtml(value + ' %');
				}
			}
		} ]
	} ]
});