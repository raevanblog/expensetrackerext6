Ext.define('expensetracker.view.charts.LineChart', {
	extend : 'Ext.panel.Panel',
	xtype : 'linechart',
	alias : 'view.linechart',
	layout : 'fit',
	tbar : [ '->', {
		iconCls : 'x-fa fa-eye',
		tooltip : 'Preview',
		ui : 'toolbar',
		handler : 'onLCPreview'
	} ],
	items : [ {
		xtype : 'cartesian',
		itemId : 'linechart',
		colors : [ 'green', 'red' ],
		interactions : {
			type : 'panzoom',
			zoomOnPanGesture : true
		},
		legend : {
			docked : 'top'
		},
		animation : {
			duration : 200
		},
		insetPadding : 20,
		innerPadding : 20,
		axes : [ {
			type : 'numeric',
			fields : [ 'income', 'expense' ],
			position : 'left',
			title : {
				text : 'Expense'
			},
			grid: {
                odd: {
                    fillStyle: 'rgba(255, 255, 255, 0.06)'
                },
                even: {
                    fillStyle: 'rgba(0, 0, 0, 0.03)'
                }
            }
		}, {
			type : 'category',
			fields : 'month',
			position : 'bottom',
			grid : true,
			label : {
				rotate : {
					degrees : -45
				}
			},
			renderer : function(axis, label, context) {
				return context.renderer(expensetracker.util.Calendar.getName(label));
			}
		} ],
		series : [ {
			type : 'line',
			title : 'Income',
			xField : 'month',
			yField : 'income',
			style : {
				lineWidth : 1
			},
			marker : {
				radius : 4,
				lineWidth : 2,
				fx : {
					duration : 200,
					easing : 'backOut'
				}
			},
			highlightCfg : {
				scaling : 2
			},
			tooltip : {
				trackMouse : true,
				showDelay : 0,
				dismissDelay : 0,
				hideDelay : 0,
				renderer : function(tooltip, record, item) {
					var title = item.series.getTitle();
					tooltip.setHtml(title + ' : ' + expensetracker.util.Session.getCurrencySymbol() + ' ' +  Ext.util.Format.number(record.get(item.series.getYField()), '0,000.00'));
				}
			}
		}, {
			type : 'line',
			title : 'Expense',
			xField : 'month',
			yField : 'expense',
			style : {
				lineWidth : 1
			},
			marker : {
				type : 'triangle',
				fx : {
					duration : 200,
					easing : 'backOut'
				}
			},
			highlightCfg : {
				scaling : 2
			},
			tooltip : {
				trackMouse : true,
				showDelay : 0,
				dismissDelay : 0,
				hideDelay : 0,
				renderer : function(tooltip, record, item) {
					var title = item.series.getTitle();
					tooltip.setHtml(title + ' : ' + expensetracker.util.Session.getCurrencySymbol() + ' ' + Ext.util.Format.number(record.get(item.series.getYField()), '0,000.00'));
				}
			}
		} ]
	} ]
});