Ext.define('expensetracker.view.dashboard.Summary',{
	extend: 'Ext.form.Panel',
	xtype: 'summary',
	alias: 'view.summary',
	layout : {
			type : 'vbox',
			align : 'stretch'
	},
	items: [{
		xtype: 'fieldcontainer',
		layout: 'hbox',
		items: [{
			xtype: 'displayfield',
			fieldLabel: 'Total Income',
			labelSeparator : '',
			value: '50000'
		}, {
			xtype: 'component',
			padding: '0 0 0 2',
			html: '<a href="javascript:void(0);"><span class="x-fa fa-edit"/></a>',
			listeners: {
				 el : {
					delegate : 'span',
					click : 'onIncomeEditClick'
				},
				render: function(comp) {
					Ext.QuickTips.register({
						target: comp.getEl(),
						text: 'Edit Income'
					});
				}
			}
		}]
	}, {
		xtype: 'displayfield',
		fieldLabel: 'Total Expense',
		labelSeparator : '',
		value: '25000'
	}, {
		xtype: 'displayfield',
		fieldLabel: 'Cash in Hand',
		labelSeparator : '',
		value: '25000'
	}]
});