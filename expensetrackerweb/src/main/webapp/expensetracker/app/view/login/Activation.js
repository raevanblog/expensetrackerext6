Ext.define('expensetracker.view.login.Activation', {
	extend: 'Ext.container.Container',	
	xtype : 'activationcontainer',
	scrollable : 'y',
	alias : 'view.activationcontainer',
	layout : {
		type : 'vbox'
	},
	layout : 'responsivecolumn',
	items: [{
		xtype: 'component',
		padding : '10 10 10 10',
		html : '<h1>Activate Your Account</h1><br><p>Before you can login,you must active your account with the activation email sent to your email address.</p><p>If you did not receive this e-mail, please check your junk/spam folder.</p><p>Click <a href="javascript:void(0);">here</a> to resend the activation email</p>',
		cls : 'big-100 small-100',
		listeners : {
			el : {
				delegate : 'a',
				click : function() {
					expensetracker.util.Message.toast('Sending Activation E-mail');
				}
			}			
		}
	},{
		xtype : 'form',
		cls : 'big-40 small-100',
		padding : '5 10 10 10',
		bodyPadding: '10 10 10 10',
		layout : {
			type : 'vbox',
			align : 'stretch'			
		},
		items: [{
			xtype : 'textfield',
			fieldLabel : 'Username',
			reference : 'activationuser',
			labelAlign : 'top',
			bind : {
				value : '{username}'
			},
			labelSeparator: ''
		}, {
			xtype : 'textfield',
			fieldLabel : 'Activation Key',
			reference : 'activationkey',
			labelAlign : 'top',
			bind : {
				value : '{activationKey}'
			},
			labelSeparator: ''
		}],
		buttons: [{
			text : 'Activate',
			iconCls : 'x-fa fa-check',
			handler : 'onActivate'
		}]
	}, {
		xtype : 'component',
		reference : 'activateerrorlbl',
		padding : '5 10 10 10',
		cls : 'big-100 small-100',
		style : {
			color : 'red'
		}
	}]
});