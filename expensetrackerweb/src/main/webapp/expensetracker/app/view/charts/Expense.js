Ext.define('expensetracker.view.charts.Expense', {
	extend : 'Ext.panel.Panel',
	xtype : 'expensechart',
	alias : 'view.expensechart',
	layout : 'fit',
	tbar: ['->', {
		iconCls : 'x-fa fa-eye',
		tooltip : 'Preview',
		ui : 'toolbar',
		handler : 'onCCPreview'
	}],
	items : [ {
		xtype : 'cartesian',
		itemId : 'expensechart',
		reference : 'expensechart',
		legend : {
			docked : 'top'
		},
		insetPadding : {
			top : 30,
			bottom : 10,
			left : 20,
			right : 20
		},
		axes : [ {
			type : 'numeric',
			minimum : 100,
			position : 'left',
			titleMargin : 20,
			title : {
				text : 'Expense'
			}
		}, {
			type : 'category',
			position : 'bottom',
			label : {
				rotate : -45
			}
		} ],
		series : {
			type : 'bar',
			title: 'Expense',
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
					return value.toFixed(2);
				}
			},
			tooltip : {
				trackMouse : true,
				renderer : function(tooltip, record) {
					var tip = expensetracker.util.Session.getCurrencySymbol() + ' '+ record.get('expense');
					tooltip.setHtml(tip);
				}
			}
		}
	} ]
});