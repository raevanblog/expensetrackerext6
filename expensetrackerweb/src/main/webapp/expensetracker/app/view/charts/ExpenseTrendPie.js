Ext.define('expensetracker.view.charts.ExpenseTrendPie', {
	extend : 'Ext.panel.Panel',
	xtype : 'expensetrendpie',
	alias : 'view.expensetrendpie',
	layout : 'fit',
	requires: ['expensetracker.view.charts.theme.BaseTheme', 'expensetracker.view.charts.series.ExpenseTrendPie'],
	tbar : ['->', {
		xtype : 'checkbox',
		checked : false,
		handler : 'onCheckMonth'
	}, {
		xtype : 'combobox',
		reference : 'monthcombo',
		valueField : 'month',
		displayField : 'name',
		queryMode : 'local',
		allowBlank : false,
		editable : false,
		forceSelection : true,
		disabled :  true,
		listeners : {
			afterrender : 'onRenderMonthCombo',
			change : 'onSelectExpenseMonth'
		}
	}],
	items : [ {
		xtype : 'polar',		
		reference : 'expensetrendpolar',
		legend: {           
            docked: 'bottom'
        },		
		insetPadding: 30,
        innerPadding: 30,
		theme: 'basetheme',
		interactions : [ 'itemhighlight', 'rotate' ],		
		series : [ {
			type : 'trendseries'
		} ]
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
	},
	setTitle : function(title) {
		var me = this;
		me.items.items[0].setSprites({
            type: 'text',
            text: title,
            fontSize: 22,
            width: 100,
            height: 30,
            x: 40,
            y: 60
        });
	}
});