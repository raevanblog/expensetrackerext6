Ext.define('expensetracker.view.login.ActivationWindow', {
	extend : 'Ext.window.Window',
	xtype : 'activationwindow',
	alias : 'view.activationwindow',
	title : 'Send Activation Mail',
	closeToolText : 'Close',
	layout : 'fit',
	items : [ {
		xtype : 'form',
		reference : 'activationwindowform',
		platformConfig : {
			desktop : {
				width : 350
			},

			'!desktop' : {
				width : '80%'
			}
		},
		bodyPadding : 10,
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [ {
			xtype : 'textfield',
			fieldLabel : 'Username',
			allowBlank : false,
			reference : 'activationwindowuser',
			validateBlank : true,
			labelSeparator : ''
		}, {
			xtype : 'label',
			padding : '10 10 10 10',
			reference : 'activationwindowerrlbl',
			style : {
				color : 'red'
			}
		} ],
		buttons : [ {
			text : 'Send',
			ui : 'soft-green',
			formBind : true,
			tooltip : 'Send',
			iconCls : 'x-fa fa-envelope',
			handler : 'onSendActivationMail'
		} ]
	} ]
});