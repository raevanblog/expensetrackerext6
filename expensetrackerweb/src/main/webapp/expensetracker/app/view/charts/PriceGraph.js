Ext.define('expensetracker.view.charts.PriceGraph', {
	extend : 'Ext.panel.Panel',
	xtype : 'pricegraph',
	alias : 'view.pricegraph',
	layout : 'fit',
	requires: ['expensetracker.view.charts.theme.BaseTheme', 'expensetracker.view.charts.PriceYearSeries'],
	tbar : [ '->', {
		xtype : 'checkbox',
		checked : false,
		handler : 'onCheckYear'
	},{
		xtype : 'combobox',	
		reference : 'yearcombo',
		disabled : true,
		displayField : 'year',
		valueField : 'year',
		queryMode : 'local',
		allowBlank : false,
		editable : false,
		forceSelection : true,
		listeners : {
			afterrender : 'onRenderYearCombo',
			change : 'onSelectExpenseYear'
		}
	} ],
	items : [ {
		xtype : 'cartesian',
		itemId : 'pricegraphchart',
		reference : 'pricegraphchart',		
		theme: {
            type: 'basetheme'
        },
		legend: {
            type: 'sprite',
            docked: 'bottom'
        },
		insetPadding : {
			top : 30,
			bottom : 10,
			left : 20,
			right : 20
		},
		axes : [ {
			type : 'numeric',
			minimum : 0,
			position : 'left',
			titleMargin : 20,
			majorTickSteps: 10,
			title : {
				text : 'Price'
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
			position : 'bottom',
			grid: true,
			label : {
				rotate : -45
			}
		}]	
	} ],
	bindStore : function(store) {
		var me = this;
		me.items.items[0].bindStore(store);
	},
	addSeries : function(series) {
		var me = this;
		me.items.items[0].setSeries(series);
	},
	getStore : function() {
		var me = this;
		return me.items.items[0].getStore();
	}
});