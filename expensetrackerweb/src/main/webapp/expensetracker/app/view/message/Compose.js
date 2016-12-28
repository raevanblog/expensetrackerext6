Ext.define('expensetracker.view.message.Compose', {
	extend : 'Ext.window.Window',
	xtype : 'emailcompose',
	alias : 'view.emailcompose',
	title : 'New Message',
	autoShow : true,
	layout : 'fit',
	bodyPadding : 10,
	items : [{
		xtype : 'form',
		bodyPadding : 10,
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [{
			xtype : 'displayfield',
			fieldLabel : 'To',
			labelSeparator : '',			
			value : 'Administrator'
		}, {
			xtype : 'textfield',
			fieldLabel : 'Subject',			
			labelSeparator : ''			
		},{
			xtype : 'htmleditor',
			fieldLabel : 'Message',
			labelSeparator : '',
			flex : 1,
			labelAlign : 'top'
		}]
	}],
	bbar : ['->',{
		xtype : 'button',
		ui : 'soft-red',		
		text : 'Discard',
	}, {
		xtype : 'button',
		ui : 'soft-green',
		formBind : true,
		text : 'Send',
	}]
});