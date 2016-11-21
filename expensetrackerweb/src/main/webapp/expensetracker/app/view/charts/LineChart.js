Ext.define('expensetracker.view.charts.LineChart', {
	extend : 'Ext.panel.Panel',
	xtype : 'linechart',
	alias : 'view.linechart',
	layout : 'fit',	
	items: [{
		xtype: 'cartesian',
		itemId : 'lineChart',
		interactions: {
            type: 'panzoom',
            zoomOnPanGesture: true
        },
		animation: {
            duration: 200
        },
		insetPadding: 20,
        innerPadding: 20,
		axes: [{
            type: 'numeric',
			fields: ['income', 'expense'],
            position: 'left',
            grid: true             
        }, {
            type: 'category',
            position: 'bottom',
            grid: true,
            label: {
                rotate: {
                    degrees: -45
                }
            },
			renderer: 'onXAxisRenderer'
        }],
		series: [{
            type: 'line',
            xField: 'month',
            yField: 'income',
            style: {
                lineWidth: 2
            },
            marker: {
                radius: 4,
                lineWidth: 2,
				fx: {
                    duration: 200,
                    easing: 'backOut'
                }
            },
            label: {
                field: 'income',
                display: 'over'
            },
            highlightCfg: {
                scaling: 2
            },
            tooltip: {
                trackMouse: true,
                showDelay: 0,
                dismissDelay: 0,
                hideDelay: 0                
            }
        }, {
            type: 'line',
            xField: 'month',
            yField: 'expense',
            style: {
                lineWidth: 2
            },
			marker: {
                type: 'triangle',
                fx: {
                    duration: 200,
                    easing: 'backOut'
                }
            },
            label: {
                field: 'expense',
                display: 'over'
            },
            highlightCfg: {
                scaling: 2
            },
            tooltip: {
                trackMouse: true,
                showDelay: 0,
                dismissDelay: 0,
                hideDelay: 0                
            }
        }],
	}]
});