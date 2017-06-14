Ext.define('expensetracker.view.charts.theme.BaseTheme', {
	extend : 'Ext.chart.theme.Base',
	alias : 'chart.theme.basetheme',
	xtype : 'basetheme',
	colors : ['#f63535', '#f67e35', '#f6bc35', '#d2f635', '#5ef635', '#35cff6', '#8f35f6', '#f4029b', '#8c8c8c', '#000000'],
	series : {
		bar : {
			animation : {
				easing : 'easeOut',
				duration : 3000
			}
		}
	},
	gradients : {
	  type: 'linear',
	  degrees: 180
	}
});