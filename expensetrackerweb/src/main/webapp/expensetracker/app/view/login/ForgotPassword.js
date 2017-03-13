Ext.define('expensetracker.view.login.ForgotPassword', {
	extend : 'Ext.window.Window',
	xtype : 'forgotpwdwindow',
	alias : 'view.forgotpwdwindow',
	title : 'Forgot Password?',
	closeToolText : 'Close',
	layout : 'fit',
	items : [ {
		xtype : 'form',
		reference : 'forgotpwdform',
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
			fieldLabel : 'Your registered e-mail?',
			allowBlank : false,
			labelAlign : 'top',
			reference : 'forgotpwdemail',
			vtype : 'email',
			validateBlank : true,
			labelSeparator : ''
		}, {
			xtype : 'label',
			padding : '10 10 10 10',
			reference : 'forgotpwderrlbl',
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
			handler : 'onSendForgotPwd'
		} ]
	} ]
});