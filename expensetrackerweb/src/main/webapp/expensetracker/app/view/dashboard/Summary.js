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
		bind : {
			fieldLabel : 'Total Income ({currencySymbol})',
		},
		reference : 'totIncome',
		labelSeparator : '',
	}, {
		xtype : 'displayfield',
		bind : {
			fieldLabel : 'Total Expense ({currencySymbol})',
		},
		reference : 'totExpense',
		labelSeparator : ''
	}, {
		xtype : 'displayfield',
		bind : {
			fieldLabel : 'Cash in Hand ({currencySymbol})',
		},
		reference : 'cashInHand',
		labelSeparator : ''
	} ]
});