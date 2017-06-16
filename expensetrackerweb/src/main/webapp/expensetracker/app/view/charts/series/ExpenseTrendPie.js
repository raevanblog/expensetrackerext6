Ext.define('expensetracker.view.charts.series.ExpenseTrendPie', {
	extend : 'Ext.chart.series.Pie',
	alias : 'series.trendseries',
	xtype : 'trendseries',
	angleField : 'avg',	
	distortion: 0.6,
	highlight : {
		margin: 20
	},	
	label: {
		field: 'category',      // bind label text to name
		display: 'outside',
		fontSize: 14
	},	
	tooltip : {
		trackMouse : true,
		renderer : function(tooltip, record, item) {
			var value = Ext.util.Format.number(record.get('avg'), '0.00');
			tooltip.setHtml(record.get('category') + ' : ' + value + ' %');
		}
	}
});