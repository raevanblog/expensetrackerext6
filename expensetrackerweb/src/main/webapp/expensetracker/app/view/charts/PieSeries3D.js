Ext.define('expensetracker.view.charts.PieSeries3D', {
	extend : 'Ext.chart.series.Pie3D',
	alias : 'series.pieseries3d',
	xtype : 'pieseries3d',
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
});