Ext.define('expensetracker.view.login.RegisterForm', {
	extend : 'Ext.container.Container',
	xtype : 'registercontainer',
	alias : 'view.registercontainer',
	requires : [ 'expensetracker.util.Validation' ],
	scrollable : true,
	layout : {
		type : 'hbox',
		pack : 'middle'
	},
	items : [ {
		xtype : 'form',
		cls : 'shadow-medium',
		border : 1,
		reference : 'registerform',
		listeners : {
			validitychange : 'updateErrorState',
			errorchange : 'updateErrorState'
		},
		platformConfig : {
			desktop : {
				width : 600
			},

			'!desktop' : {
				width : '80%'
			}
		},
		method : 'POST',
		jsonSubmit : true,
		margin : '30 0 0 0',
		url : expensetracker.util.Url.getCreateUser(),
		bodyPadding : 10,
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [ {
			xtype : 'fieldcontainer',
			layout : {
				type : 'hbox'
			},
			items : [ {
				xtype : 'textfield',
				emptyText : 'First Name',
				height : 51,
				labelSeparator : '',
				reference : 'fname',
				validateBlank : true,
				name : 'firstName',
				allowBlank : false,
				enableKeyEvents : true,
				maxLength : 30,
				triggers : {
					glyph : {
						cls : 'textfield-trigger-icon user-trigger'
					}
				},
				flex : 1
			}, {
				xtype : 'textfield',
				emptyText : 'Last Name',
				height : 51,
				reference : 'lname',
				allowBlank : false,
				name : 'lastName',
				enableKeyEvents : true,
				validateBlank : true,
				labelSeparator : '',
				maxLength : 30,
				padding : '0 0 0 10',
				triggers : {
					glyph : {
						cls : 'textfield-trigger-icon user-trigger'
					}
				},
				flex : 1
			} ]
		}, {
			xtype : 'combobox',
			emptyText : 'Sex',
			reference : 'sex',
			height : 51,
			name : 'sex',
			hideTrigger : true,
			allowBlank : false,
			labelSeparator : '',
			forceSelection : true,
			store : [ [ 'M', 'Male' ], [ 'F', 'Female' ] ]
		}, {
			xtype : 'fieldcontainer',
			layout : 'hbox',
			items : [ {
				xtype : 'textfield',
				height : 51,
				allowBlank : false,
				emptyText : 'Email',
				vtype : 'email',
				name : 'email',
				allowBlank : false,
				maxLength : 50,
				reference : 'email',
				flex : 0.6,
				labelSeparator : '',
				listeners : {
					blur : 'onFocusOut',
					change : 'onChange'
				}
			} ]
		}, {
			xtype : 'numberfield',
			height : 51,
			enforceMaxLength : true,
			name : 'mobile',
			emptyText : 'Mobile',
			maxLength : 10,
			hideTrigger : true,
			reference : 'mobile',
			validator : function(value) {
				return value.length == 10 ? true : "Please enter a valid mobile number";
			},
			labelSeparator : ''
		}, {
			xtype : 'textarea',
			emptyText : 'Address Max(200)',
			name : 'address',
			maxLength : 200,
			reference : 'address',
			labelSeparator : ''
		}, {
			xtype : 'fieldcontainer',
			layout : 'hbox',
			items : [ {
				xtype : 'textfield',
				emptyText : 'Username',
				height : 51,
				reference : 'regUsername',
				allowBlank : false,
				name : 'username',
				enableKeyEvents : true,
				validateBlank : true,
				labelSeparator : '',
				maxLength : 30,
				vtype : 'username',
				flex : 0.6,
				triggers : {
					glyph : {
						cls : 'textfield-trigger-icon user-trigger'
					}
				},
				listeners : {
					blur : 'onFocusOut',
					change : 'onChange'
				}
			} ]
		}, {
			xtype : 'fieldcontainer',
			layout : {
				type : 'hbox'
			},
			items : [ {
				xtype : 'textfield',
				emptyText : 'Password',
				allowBlank : false,
				name : 'password',
				enableKeyEvents : true,
				validateBlank : true,
				labelSeparator : '',
				vtype : 'password',
				id : 'regpassword',
				inputType : 'password',
				maxLength : 25,
				height : 51,
				triggers : {
					glyph : {
						cls : 'textfield-trigger-icon password-trigger'
					}
				},
				flex : 1
			}, {
				xtype : 'textfield',
				emptyText : 'Retype Password',
				reference : 'regRPassword',
				allowBlank : false,
				name : 'rpassword',
				enableKeyEvents : true,
				validateBlank : true,
				labelSeparator : '',
				inputType : 'password',
				submitValue : false,
				maxLength : 25,
				padding : '0 0 0 10',
				vtype : 'match',
				vtypeText : 'Password does not match',
				matchfield : 'regpassword',
				height : 51,
				triggers : {
					glyph : {
						cls : 'textfield-trigger-icon password-trigger'
					}
				},
				flex : 1
			} ]
		}, {
			xtype : 'button',
			text : 'Register',
			scale : 'large',
			ui : 'soft-green',
			formBind : true,
			iconCls : 'x-fa  fa-user-plus',
			reference : 'registerBtn',
			handler : 'onRegisterUser'
		}]
	}, {
			xtype : 'errorstate',
			height : 50,
			margin : '0 0 0 5',
			padding : '15 0 0 20',
			anchor : 'left',
			reference : 'errorInd'
		} ]

});