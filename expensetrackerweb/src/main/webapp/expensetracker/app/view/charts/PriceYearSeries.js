Ext.define('expensetracker.view.charts.PriceYearSeries', {
	extend : 'Ext.chart.series.Bar',
	alias : 'series.priceyearseries',
	xtype : 'priceyearseries',
	bind : {
		title : '{itemName}',
		xField : '{xField}'
	},	
	yField : 'avgprice',
	style : {
		minGapWidth : 20
	},
	highlight : {
		strokeStyle : 'black',
		fillStyle : 'gold'
	},
	label : {
		field : 'avgprice',
		display : 'insideEnd',
		renderer : function(value) {
			return expensetracker.util.Session.getCurrencySymbol() + ' ' + Ext.util.Format.number(value, '0,000.00');
		}
	},
	tooltip : {
		trackMouse : true,
		renderer : function(tooltip, record) {
			var tip = expensetracker.util.Session.getCurrencySymbol() + ' ' + Ext.util.Format.number(record.get('avgprice'), '0,000.00');
			tooltip.setHtml(tip);
		}
	}
});