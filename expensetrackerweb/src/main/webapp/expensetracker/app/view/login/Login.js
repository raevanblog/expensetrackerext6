Ext.define('expensetracker.view.login.Login', {
	extend : 'Ext.window.Window',
	xtype : 'login',
	alias : 'view.login',
	layout : 'fit',
	requires : [ 'expensetracker.view.login.LoginController' ],
	controller : 'login',
	title : 'Login',
	bodyPadding : 10,
	closable : false,
	autoShow : true,
	items : [ {
		xtype : 'form',
		width : 350,
		method : 'POST',
		jsonSubmit : true,
		url : expensetracker.util.Url.getLogin(),
		reference : 'loginform',
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [ {
			xtype : 'textfield',
			fieldLabel : 'Username',
			labelSeparator : '',
			reference : 'username',
			submitValue : false,
			validateBlank : true,
			name : 'username',
			allowBlank : false,
			enableKeyEvents : true,
			listeners : {
				keypress : 'onEnter'
			},
			maxLength : 25,
			flex : 1
		}, {
			xtype : 'textfield',
			fieldLabel : 'Password',
			submitValue : false,
			reference : 'password',
			allowBlank : false,
			name : 'password',
			enableKeyEvents : true,
			validateBlank : true,
			labelSeparator : '',
			maxLength : 25,
			listeners : {
				keypress : 'onEnter'
			},
			inputType : 'password',
			flex : 1
		}, {
			xtype : 'label',
			padding : '10 10 10 10',
			reference : 'errorlbl',
			text : '  ',
			style : {
				color : 'red'
			}
		} ],
		buttons : [ {
			text : 'Login',
			formBind : true,
			reference : 'loginBtn',
			handler : 'onLogin'
		} ]
	} ]

});