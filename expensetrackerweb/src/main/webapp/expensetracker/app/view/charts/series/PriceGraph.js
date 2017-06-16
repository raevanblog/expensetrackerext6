Ext.define('expensetracker.view.charts.series.PriceGraph', {
	extend : 'Ext.chart.series.Bar',
	alias : 'series.priceseries',
	xtype : 'priceseries',
	bind : {
		title : '{itemName}',
		xField : '{xField}'
	},	
	yField : 'avg',
	style : {
		minGapWidth : 20
	},
	highlight : {
		strokeStyle : 'black',
		fillStyle : 'gold'
	},
	label : {
		field : 'avg',
		display : 'insideEnd',
		renderer : function(value) {
			return expensetracker.util.Session.getCurrencySymbol() + ' ' + Ext.util.Format.number(value, '0,000.00');
		}
	},
	tooltip : {
		trackMouse : true,
		renderer : function(tooltip, record) {
			var tip = expensetracker.util.Session.getCurrencySymbol() + ' ' + Ext.util.Format.number(record.get('avg'), '0,000.00');
			tooltip.setHtml(tip);
		}
	}
});