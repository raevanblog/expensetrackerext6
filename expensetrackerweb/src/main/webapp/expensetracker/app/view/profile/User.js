Ext.define('expensetracker.view.profile.User', {
	extend : 'Ext.window.Window',
	alias : 'view.profile',
	requires : [ 'expensetracker.view.profile.UserController', 'Ext.form.field.VTypes' ],
	xtype : 'profile',
	resizable : false,
	bodyPadding : 10,
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
		scrollable : 'y',
		layout : {
			type : 'hbox',
			pack : 'middle'
		},
		items : [ {
			xtype : 'form',			
			jsonSubmit : true,
			url : expensetracker.util.Url.getUserService(),
			reference : 'profileform',
			layout : {
				type : 'vbox'				
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
			}, {
				xtype : 'combobox',
				fieldLabel : 'Sex',
				emptyText : 'Sex',
				reference : 'sex',
				name : 'sex',
				allowBlank : false,
				labelSeparator : '',
				forceSelection : true,
				store : [ [ 'M', 'Male' ], [ 'F', 'Female' ] ]
			}, {
				xtype : 'textfield',
				fieldLabel : 'Email',
				allowBlank : false,
				emptyText : 'Email',
				vtype : 'email',
				name : 'email',
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
	} ]

});