Ext.define('expensetracker.view.login.LoginForm', {
	extend: 'Ext.container.Container',	
	xtype : 'logincontainer',
	alias : 'view.logincontainer',		
	layout : {
		type : 'hbox',
		pack : 'middle'
	},
	items: [{
		xtype: 'form',
		platformConfig: {
			 desktop: {
				 width: 350				 
			 },

			 '!desktop': {
				width: '80%'
			 }
		},
		reference : 'loginform',
		title : 'Login',
		method : 'POST',		
		jsonSubmit : true,
		padding: '100 0 0 0',
		url : expensetracker.util.Url.getLogin(),
		bodyPadding : 10,
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
		},{
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
				iconCls: 'x-fa fa-sign-in',
				reference : 'loginBtn',
				handler : 'onLogin'
		} ]
	}]
	
});