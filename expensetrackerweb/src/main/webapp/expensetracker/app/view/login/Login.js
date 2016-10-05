Ext.define('expensetracker.view.login.Login', {
	extend : 'Ext.container.Container',
	xtype : 'login',
	alias : 'view.login',
	layout : 'fit',
	requires : [ 'expensetracker.view.login.LoginController' ],
	controller : 'login',
	items : [ {
		xtype : 'container',
		layout : {
			type : 'hbox',
			pack : 'middle'
		},
		items : [ {
			xtype : 'form',
			width : 350,
			method : 'POST',
			jsonSubmit : true,
			url : expensetracker.util.Url.getLoginService(),
			reference : 'loginform',
			title : 'Login',
			bodyPadding : '5	5 0 5',
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
				maxLength : 25,
				flex : 1
			}, {
				xtype : 'textfield',
				fieldLabel : 'Password',
				submitValue : false,
				reference : 'password',
				allowBlank : false,
				name : 'password',
				validateBlank : true,
				labelSeparator : '',
				maxLength : 25,
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
				handler : 'onLogin'
			} ]
		} ]
	} ]

});