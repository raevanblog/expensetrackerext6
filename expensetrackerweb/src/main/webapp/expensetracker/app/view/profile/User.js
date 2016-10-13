Ext.define('expensetracker.view.profile.User', {
	extend : 'Ext.window.Window',
	alias : 'view.profile',
	requires : [ 'expensetracker.view.profile.UserController', 'Ext.form.field.VTypes' ],
	xtype : 'profile',
	resizable : false,
	bodyPadding : 10,
	title : 'User Profile',
	layout : 'fit',
	controller : 'user',
	listeners : {
		afterrender : 'onProfileRender'
	},
	items : [ {
		xtype : 'form',
		jsonSubmit : true,
		url : expensetracker.util.Url.getUserService(),
		reference : 'profileform',
		layout : 'anchor',
		items : [ {
			xtype : 'fieldcontainer',
			layout : {
				type : 'hbox'
			},
			items : [ {
				xtype : 'image',
				reference : 'profileimage',
				alt : 'Profile Image',
				height : 100,
				width : 100
			}, {
				xtype : 'fieldcontainer',
				padding : '0 0 0 10',
				flex : 1,
				layout : {
					type : 'vbox',
					align : 'stretch'
				},
				items : [ {
					xtype : 'displayfield',
					fieldLabel : 'Username',
					reference : 'userName',
					name : 'username',
					labelSeparator : ''
				}, {
					xtype : 'textfield',
					fieldLabel : 'First Name',
					reference : 'firstName',
					allowBlank : false,
					allowBlank : false,
					name : 'firstName',
					emptyText : 'First Name',
					maxLength : 30,
					labelSeparator : ''
				}, {
					xtype : 'textfield',
					fieldLabel : 'Last Name',
					emptyText : 'Last Name',
					reference : 'lastName',
					name : 'lastName',
					allowBlank : false,
					allowBlank : false,
					maxLength : 30,
					labelSeparator : ''
				} ]
			} ]
		}, {
			xtype : 'combobox',
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
			fieldLabel : 'Mobile',
			enforceMaxLength : true,
			name : 'mobile',
			emptyText : 'Mobile',
			anchor : '100%',
			maxLength : 10,
			hideTrigger : true,
			reference : 'mobile',
			validator : function(value) {
				return value.length == 10 ? true : "please enter a valid mobile number";
			},
			labelSeparator : ''
		}, {
			xtype : 'textarea',
			fieldLabel : 'Address',
			anchor : '100%',
			emptyText : 'Max(200)',
			name : 'address',
			maxLength : 200,
			reference : 'address',
			labelSeparator : ''

		} ],
		buttons : [ {
			text : 'Save',
			iconCls : 'x-fa fa-save',
			reference : 'saveprofile',
			handler : 'onSaveProfile',
			formBind : true
		} ]
	} ]

});