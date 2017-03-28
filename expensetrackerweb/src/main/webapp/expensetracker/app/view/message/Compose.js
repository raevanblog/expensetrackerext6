Ext.define('expensetracker.view.message.Compose', {
	extend : 'Ext.window.Window',
	xtype : 'emailcompose',
	alias : 'view.emailcompose',
	closeToolText : 'Close',
	itemId : 'compose',
	title : 'New Message',	
	autoShow : true,
	layout : {
		type : 'anchor',
		anchor : '100%'
	},
	bodyPadding : 10,
	controller : 'message',
	listeners : {
		beforerender : 'onRenderCompose'
	},
	items : [{
			xtype : 'image',
			itemId : 'profileimg',
			height : 80,
			width : 80,
			style : 'border-radius: 64px'
		},{
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
			itemId : 'messagesubject',
			labelSeparator : ''			
		},{
			xtype : 'htmleditor',
			itemId : 'messageeditor',
			fieldLabel : 'Message',
			labelSeparator : '',			
			labelAlign : 'top'
		}]
	}],
	bbar : ['->',{
		xtype : 'button',
		ui : 'soft-red',		
		text : 'Discard',
		handler : 'onDiscardMessage'
	}, {
		xtype : 'button',
		ui : 'soft-green',
		formBind : true,
		text : 'Send',
		handler : 'onSendMessage'
	}]
});