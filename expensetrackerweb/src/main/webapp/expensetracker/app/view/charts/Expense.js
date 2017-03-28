Ext.define('expensetracker.view.charts.Expense', {
	extend : 'Ext.panel.Panel',
	xtype : 'expensechart',
	alias : 'view.expensechart',
	layout : 'fit',
	requires: ['Ext.chart.theme.Muted'],
	tbar : [ '->', {
		iconCls : 'x-fa fa-eye',
		tooltip : 'Preview',
		ui : 'toolbar',
		handler : 'onCCPreview'
	} ],
	items : [ {
		xtype : 'cartesian',
		itemId : 'expensechart',
		reference : 'expensechart',		
		theme: {
            type: 'muted'
        },
		insetPadding : {
			top : 30,
			bottom : 10,
			left : 20,
			right : 20
		},
		axes : [ {
			type : 'numeric3d',
			minimum : 100,
			position : 'left',
			titleMargin : 20,
			majorTickSteps: 10,
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
			type : 'category3d',
			position : 'bottom',
			grid: true,
			label : {
				rotate : -45
			}
		} ],
		series : {
			type : 'bar3d',
			title : 'Expense',
			xField : 'category',
			yField : 'expense',
			style : {
				minGapWidth : 20
			},
			highlight : {
				strokeStyle : 'black',
				fillStyle : 'gold'
			},
			label : {
				field : 'expense',
				display : 'insideEnd',
				renderer : function(value) {
					return expensetracker.util.Session.getCurrencySymbol() + ' ' + Ext.util.Format.number(value, '0,000.00');
				}
			},
			tooltip : {
				trackMouse : true,
				renderer : function(tooltip, record) {
					var tip = expensetracker.util.Session.getCurrencySymbol() + ' ' + record.get('expense');
					tooltip.setHtml(tip);
				}
			}
		}
	} ]
});