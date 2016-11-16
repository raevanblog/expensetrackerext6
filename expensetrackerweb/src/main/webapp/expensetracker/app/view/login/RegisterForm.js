Ext.define('expensetracker.view.login.RegisterForm', {
	extend : 'Ext.container.Container',
	xtype : 'registercontainer',
	alias : 'view.registercontainer',
	requires : ['expensetracker.util.Validation'], 
	scrollable: true,
	layout : {
		type : 'hbox',
		pack : 'middle'
	},
	items : [ {
		xtype : 'form',
		reference : 'registerform',
		platformConfig : {
			desktop : {
				width : 600				
			},

			'!desktop' : {
				width : '80%'
			}
		},
		title : 'Register',
		method : 'POST',
		jsonSubmit : true,
		padding : '30 0 0 0',
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
				labelAlign : 'top',
				fieldLabel : 'First Name',
				labelSeparator : '',
				reference : 'fname',
				validateBlank : true,
				name : 'firstName',
				allowBlank : false,
				enableKeyEvents : true,
				maxLength : 30,
				flex : 1
			}, {
				xtype : 'textfield',
				labelAlign : 'top',
				fieldLabel : 'Last Name',
				reference : 'lname',
				allowBlank : false,
				name : 'lastName',
				enableKeyEvents : true,
				validateBlank : true,
				labelSeparator : '',
				maxLength : 30,
				padding : '0 0 0 10',
				flex : 1
			} ]
		}, {
			xtype : 'combobox',
			labelAlign : 'top',
			fieldLabel : 'Sex',
			emptyText : 'Sex',
			reference : 'sex',
			name : 'sex',
			allowBlank : false,
			labelSeparator : '',
			anchor : '100%',
			forceSelection : true,
			store : [ [ 'M', 'Male' ], [ 'F', 'Female' ] ]
		}, {
			xtype : 'textfield',
			labelAlign : 'top',
			fieldLabel : 'Email',
			allowBlank : false,
			emptyText : 'Email',
			vtype : 'email',
			name : 'email',
			anchor : '100%',
			allowBlank : false,
			maxLength : 50,
			reference : 'email',
			labelSeparator : ''
		}, {
			xtype : 'numberfield',
			labelAlign : 'top',
			fieldLabel : 'Mobile',
			enforceMaxLength : true,
			name : 'mobile',
			emptyText : 'Mobile',
			anchor : '100%',
			maxLength : 10,
			hideTrigger : true,
			reference : 'mobile',
			validator : function(value) {
				return value.length == 10 ? true : "Please enter a valid mobile number";
			},
			labelSeparator : ''
		}, {
			xtype : 'textarea',
			labelAlign : 'top',
			fieldLabel : 'Address',
			anchor : '100%',
			emptyText : 'Max(200)',
			name : 'address',
			maxLength : 200,
			reference : 'address',
			labelSeparator : ''
		}, {
			xtype : 'fieldcontainer',
			layout : 'hbox',
			items : [ {
				xtype : 'textfield',
				labelAlign : 'top',
				fieldLabel : 'Username',
				reference : 'regUsername',
				allowBlank : false,
				name : 'username',
				enableKeyEvents : true,
				validateBlank : true,
				labelSeparator : '',
				maxLength : 30,
				vtype: 'username',
				flex : 0.8,
				listeners : {
					blur : 'onFocusOutUserName',
					change : 'onUserNameChage'
				}
			}, {
				xtype : 'component',
				reference : 'usrNmeAvailInd',
				padding : '35 0 0 10',
				flex : 0.2
			} ]
		}, {
			xtype : 'fieldcontainer',
			layout : {
				type : 'hbox'
			},
			items : [ {
				xtype : 'textfield',
				labelAlign : 'top',
				fieldLabel : 'Password',
				reference : 'regPassword',
				allowBlank : false,
				name : 'password',
				enableKeyEvents : true,
				validateBlank : true,
				labelSeparator : '',
				vtype: 'password',
				id : 'regpassword',
				inputType : 'password',
				maxLength : 25,
				flex : 1
			}, {
				xtype : 'textfield',
				labelAlign : 'top',
				fieldLabel : 'Retype Password',
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
				vtype: 'match',
				vtypeText : 'Password does not match',
				matchfield: 'regpassword',				
				listeners : {
					change : 'onPasswordReType'
				},
				flex : 1
			} ]
		} ],
		buttons : [ {
			text : 'Register',
			formBind : true,
			iconCls : 'x-fa  fa-user-plus',
			reference : 'registerBtn',
			handler : 'onRegisterUser'
		}, {
			text : 'Cancel',
			iconCls : 'x-fa  fa-close',
			reference : 'closeRegisterBtn',
			handler : 'onCloseRegister'
		} ]
	} ]

});