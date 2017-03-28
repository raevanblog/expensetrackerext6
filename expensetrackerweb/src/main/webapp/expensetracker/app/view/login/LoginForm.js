Ext.define('expensetracker.view.login.LoginForm', {
	extend : 'Ext.container.Container',
	xtype : 'logincontainer',
	alias : 'view.logincontainer',
	layout : {
		type : 'hbox',
		pack : 'middle'
	},
	items : [ {
		xtype : 'form',
		platformConfig : {
			desktop : {
				width : 350
			},

			'!desktop' : {
				width : '80%'
			}
		},
		reference : 'loginform',
		method : 'POST',
		jsonSubmit : true,
		padding : '100 0 0 0',
		url : expensetracker.util.Url.getLogin(),
		bodyPadding : 10,
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [ {
			xtype : 'textfield',
			labelSeparator : '',
			hideLabel : true,
			height : 51,
			reference : 'username',
			submitValue : false,
			validateBlank : true,
			name : 'username',
			allowBlank : false,
			emptyText : 'Username',
			enableKeyEvents : true,
			bind : {
				value : '{username}'
			},
			listeners : {
				keypress : 'onEnter'
			},
			triggers : {
				glyph : {
					cls : 'textfield-trigger-icon user-trigger'
				}
			},
			flex : 1
		}, {
			xtype : 'textfield',
			hideLabel : true,
			height : 51,
			submitValue : false,
			reference : 'password',
			allowBlank : false,
			name : 'password',
			bind : {
				value : '{password}'
			},
			enableKeyEvents : true,
			validateBlank : true,
			labelSeparator : '',
			emptyText : 'Password',
			listeners : {
				keypress : 'onEnter'
			},
			inputType : 'password',
			triggers : {
				glyph : {
					cls : 'textfield-trigger-icon password-trigger'
				}
			},
			flex : 1
		}, {
			xtype : 'container',
			layout : 'hbox',
			items : [ {
				xtype : 'checkboxfield',
				reference : 'loginrememberme',
				flex : 1,
				height : 30,
				bind : {
					value : '{rememberMe}'
				},
				boxLabel : 'Remember me'
			}, {
				xtype : 'box',
				html : '<a href="javascript:void(0);" class="link-forgot-password"> Forgot Password ?</a>',
				listeners : {
					el : {
						delegate : 'a',
						click : 'onForgorPwd'
					}
				}
			} ]
		}, {
			xtype : 'button',
			scale : 'large',
			ui : 'soft-green',
			formBind : true,
			text : 'Login',
			iconCls : 'x-fa fa-sign-in',
			reference : 'loginBtn',
			handler : 'onLogin'
		}, {
            xtype: 'errorstate',
			height : 50,
			padding : '15 0 0 20',
            reference: 'errorlbl',                      
            flex: 1            
        }]
	} ]

});