Ext.define('expensetracker.view.profile.User', {
	extend : 'Ext.window.Window',
	alias : 'view.profile',
	requires : [ 'expensetracker.view.profile.UserController', 'Ext.form.field.VTypes' ],
	xtype : 'profile',
	resizable : false,
	scrollable : 'y',
	title : 'User Profile',
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	controller : 'user',
	listeners : {
		afterrender : 'onProfileRender'
	},
	items : [ {
		xtype : 'container',
		height : 100,
		padding : '10 0 10 0',
		layout : {
			type : 'hbox',
			pack : 'middle'
		},
		items : [ {
			xtype : 'image',
			alt : 'Photo',
			reference : 'profileimage',
			style : {
				borderRadius : '50%'
			},
			height : 100,
			width : 100
		} ]
	}, {
		xtype : 'fileuploadfield',
		buttonText : 'Change Photo',
		buttonOnly : true,
		buttonConfig : {
			ui : 'toolbar',
			iconCls : 'x-fa fa-upload'
		},
		listeners : {
			change : 'onImageSelection'
		}
	}, {
		xtype : 'container',
		layout : {
			type : 'hbox',
			pack : 'middle'
		},
		items : [ {
			xtype : 'form',
			jsonSubmit : true,
			url : expensetracker.util.Url.getUserService(),
			bodyPadding : 10,
			platformConfig : {
				desktop : {
					width : 600
				},

				'!desktop' : {
					width : '80%'
				}
			},
			reference : 'profileform',
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			items : [ {
				xtype : 'textfield',
				fieldLabel : 'Username',
				reference : 'userName',
				readOnly : true,
				labelAlign : 'top',
				name : 'username',
				height : 75,
				labelSeparator : ''
			}, {
				xtype : 'textfield',
				fieldLabel : 'First Name',
				reference : 'firstName',
				labelAlign : 'top',
				allowBlank : false,
				allowBlank : false,
				name : 'firstName',
				emptyText : 'First Name',
				maxLength : 30,
				height : 75,
				labelSeparator : ''
			}, {
				xtype : 'textfield',
				fieldLabel : 'Last Name',
				emptyText : 'Last Name',
				labelAlign : 'top',
				reference : 'lastName',
				name : 'lastName',
				allowBlank : false,
				allowBlank : false,
				maxLength : 30,
				height : 75,
				labelSeparator : ''
			}, {
				xtype : 'combobox',
				fieldLabel : 'Sex',
				emptyText : 'Sex',
				labelAlign : 'top',
				reference : 'sex',
				name : 'sex',
				allowBlank : false,
				labelSeparator : '',
				forceSelection : true,
				height : 75,
				store : [ [ 'M', 'Male' ], [ 'F', 'Female' ] ]
			}, {
				xtype : 'textfield',
				fieldLabel : 'Email',
				allowBlank : false,
				labelAlign : 'top',
				emptyText : 'Email',
				vtype : 'email',
				name : 'email',
				allowBlank : false,
				maxLength : 50,
				reference : 'email',
				height : 75,
				labelSeparator : ''
			}, {
				xtype : 'numberfield',
				fieldLabel : 'Mobile',
				enforceMaxLength : true,
				labelAlign : 'top',
				name : 'mobile',
				emptyText : 'Mobile',
				maxLength : 10,
				hideTrigger : true,
				reference : 'mobile',
				validator : function(value) {
					return value.length == 10 ? true : "please enter a valid mobile number";
				},
				height : 75,
				labelSeparator : ''
			}, {
				xtype : 'textarea',
				fieldLabel : 'Address',
				emptyText : 'Max(200)',
				labelAlign : 'top',
				name : 'address',
				maxLength : 200,
				reference : 'address',
				labelSeparator : ''

			}, {
				xtype : 'button',
				text : 'Save',
				scale : 'large',
				margin : '10 0 0 0',
				ui : 'soft-green',
				iconCls : 'x-fa fa-save',
				reference : 'saveprofile',
				handler : 'onSaveProfile',
				formBind : true
			} ]
		} ]
	} ]

});