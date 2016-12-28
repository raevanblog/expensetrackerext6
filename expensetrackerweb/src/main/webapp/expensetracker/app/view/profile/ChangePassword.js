Ext.define('expensetracker.view.profile.ChangePassword', {
	extend : 'Ext.window.Window',
	xtype : 'changepassword',
	alias : 'view.changepassword',
	title : 'Change Password',
	iconCls : 'x-fa fa-lock',
	bodyPadding : '75 0 0 0',
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
		cls : 'grey-border shadow',		
		url : expensetracker.util.Url.getPasswordService(),
		reference : 'changepwdform',
		bodyPadding : '10 10 10 10',
		platformConfig : {
			desktop : {
				width : 450
			},

			'!desktop' : {
				width : '80%'
			}
		},
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [ {
			xtype : 'textfield',
			emptyText : 'Current Password',
			reference : 'changeCurrPwd',
			validateBlank : true,
			name : 'password',
			allowBlank : false,
			height : 51,
			maxLength : 25,
			triggers : {
				glyph : {
					cls : 'textfield-trigger-icon password-trigger'
				}
			},
			inputType : 'password'
		}, {
			xtype : 'textfield',
			emptyText : 'New Password',
			reference : 'newPwd',
			validateBlank : true,
			name : 'newPassword',
			allowBlank : false,
			maxLength : 25,
			height : 51,
			vtype : 'password',
			id : 'chgpassword',
			triggers : {
				glyph : {
					cls : 'textfield-trigger-icon password-trigger'
				}
			},
			inputType : 'password'
		}, {
			xtype : 'textfield',
			emptyText : 'Retype Password',
			reference : 'newRetypePwd',
			validateBlank : true,
			submitValue : false,
			allowBlank : false,
			maxLength : 25,
			height : 51,
			vtype : 'match',
			vtypeText : 'Password does not match',
			matchfield : 'chgpassword',
			triggers : {
				glyph : {
					cls : 'textfield-trigger-icon password-trigger'
				}
			},
			inputType : 'password'
		}, {
			xtype : 'label',			
			reference : 'chgPwdErrorLbl',
			text : '  ',
			style : {
				color : 'red'
			}
		},{
			xtype : 'button',
			scale : 'large',
			margin : '10 0 10 0',
			ui : 'soft-green',
			text : 'Submit',
			handler : 'onSubmitPwd',
			formBind : true
		} ]		
	} ]
});