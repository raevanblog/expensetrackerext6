Ext.define('expensetracker.view.profile.ChangePassword', {
	extend : 'Ext.window.Window',
	xtype : 'changepassword',
	alias : 'view.changepassword',
	title : 'Change Password',
	iconCls : 'x-fa fa-key',
	bodyPadding : 10,
	requires : [ 'expensetracker.view.profile.PasswordController' ],
	controller : 'password',
	layout : {
		type : 'hbox',
		pack : 'middle'
	},
	items : [ {
		xtype : 'form',
		method : 'PUT',
		jsonSubmit : true,
		padding : '75 0 0 0',
		url : expensetracker.util.Url.getPasswordService(),
		reference : 'changepwdform',
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [ {
			xtype : 'textfield',
			fieldLabel : 'Password',
			labelSeparator : '',
			reference : 'changeCurrPwd',
			validateBlank : true,
			name : 'password',
			allowBlank : false,
			maxLength : 25,
			inputType : 'password'
		}, {
			xtype : 'textfield',
			fieldLabel : 'New Password',
			labelSeparator : '',
			reference : 'newPwd',
			validateBlank : true,
			name : 'newPassword',
			allowBlank : false,
			maxLength : 25,
			vtype : 'password',
			id : 'chgpassword',
			inputType : 'password'
		}, {
			xtype : 'textfield',
			fieldLabel : 'Retype Password',
			labelSeparator : '',
			reference : 'newRetypePwd',
			validateBlank : true,
			submitValue : false,
			allowBlank : false,
			maxLength : 25,
			vtype : 'match',
			vtypeText : 'Password does not match',
			matchfield : 'chgpassword',
			inputType : 'password'
		}, {
			xtype : 'label',
			padding : '10 10 10 10',
			reference : 'chgPwdErrorLbl',
			text : '  ',
			style : {
				color : 'red'
			}
		} ],
		buttons : [ {
			text : 'Submit',
			handler : 'onSubmitPwd',
			formBind : true
		} ]
	} ]
});