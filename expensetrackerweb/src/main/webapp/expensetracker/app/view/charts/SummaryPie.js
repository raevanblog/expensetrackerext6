Ext.define('expensetracker.view.charts.SummaryPie', {
	extend: 'Ext.panel.Panel',
	xtype: 'summarypie',
	alias: 'view.summarypie',
	layout: 'fit',
	items: [{
		xtype: 'polar',
		itemId: 'summaryPolar',
		theme: 'default-gradients',
		insetPadding: 20,
		innerPadding: 10,
		interactions: ['rotate'],
        series: [{
            type: 'pie',
            angleField: 'value',
            label: {
                field: 'item',
				fontSize: '12px',
				display: 'vertical',
				calloutLine: {
                    length: 35,
                    width: 3                
                }				
            },
            highlight: true            
        }]
	}]
});