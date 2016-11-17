Ext.define('expensetracker.view.profile.ChangePassword', {
	extend: 'Ext.window.Window',
	xtype: 'changepassword',
	alias: 'view.changepassword',
	title : 'Change Password',
	iconCls : 'x-fa fa-key',
	requires : ['expensetracker.view.profile.PasswordController'],
	controller : 'password',
	layout : {
		type: 'hbox',
		pack: 'middle'
	},
	items: [{
		xtype : 'form',
		padding : '150 0 0 0',
		width : '40%',
		method : 'PUT',
		jsonSubmit : true,
		url: expensetracker.util.Url.getPasswordService(),
		reference : 'changepwdform',
		layout :  {
			type : 'vbox',
			align : 'stretch'
		},
		items: [{
			xtype: 'textfield',
			fieldLabel : 'Current Password',
			labelSeparator : '',
			labelWidth : 150,
			reference : 'changeCurrPwd',
			validateBlank : true,
			name : 'password',
			allowBlank : false,			
			maxLength : 25,
			inputType : 'password',
			flex : 1
		}, {
			xtype: 'textfield',
			fieldLabel : 'New Password',
			labelWidth : 150,
			labelSeparator : '',
			reference : 'newPwd',
			validateBlank : true,
			name : 'newPassword',
			allowBlank : false,			
			maxLength : 25,
			vtype: 'password',
			id: 'chgpassword',
			inputType : 'password',
			flex : 1
		}, {
			xtype: 'textfield',
			fieldLabel : 'Retype Password',
			labelSeparator : '',
			labelWidth : 150,
			reference : 'newRetypePwd',
			validateBlank : true,
			submitValue : false,
			allowBlank : false,			
			maxLength : 25,
			vtype: 'match',
			vtypeText : 'Password does not match',
			matchfield: 'chgpassword',	
			inputType : 'password',
			flex : 1
		},{
			xtype : 'label',
			padding : '10 10 10 10',
			reference : 'chgPwdErrorLbl',
			text : '  ',
			style : {
				color : 'red'
			}
		}],
		buttons: [{
			text: 'Submit',
			handler : 'onSubmitPwd',
			formBind : true
		}]
	}]
});