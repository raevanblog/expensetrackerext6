Ext.define('expensetracker.view.dashboard.Summary', {
	extend : 'Ext.form.Panel',
	xtype : 'summary',
	alias : 'view.summary',
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	items : [ {
		xtype : 'component',
		padding : '5 0 20 0',
		html : '<a href="javascript:void(0);">Update Income</a>',
		listeners : {
			el : {
				delegate : 'a',
				click : 'onIncomeEditClick'
			},
			render : function(comp) {
				Ext.QuickTips.register({
					target : comp.getEl(),
					text : 'Edit Income'
				});
			}
		}
	}, {
		xtype : 'displayfield',
		fieldLabel : 'Total Income',
		labelSeparator : '',
	}, {
		xtype : 'displayfield',
		fieldLabel : 'Total Expense',
		labelSeparator : ''
	}, {
		xtype : 'displayfield',
		fieldLabel : 'Cash in Hand',
		labelSeparator : ''
	} ]
});