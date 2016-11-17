Ext.define('expensetracker.view.main.Toolbar', {
	extend : 'Ext.toolbar.Toolbar',
	alias : 'view.maintoolbar',
	xtype : 'maintoolbar',
	items : [ {
		xtype : 'component',
		html: '<div class="main-logo"><img src="resources/images/logo.png">Expense Tracker</div>',
		reference : 'logocomponent',
		width : 64,
		cls : 'logo-component'
	}, {
		xtype : 'button',
		ui: 'toolbar',
		iconCls : 'x-fa fa-bars',
		reference : 'toggleNavigation',
		handler : 'onToggleNavigation'
	},{
		xtype: 'image',
		height: 35,
        width: 35,
		reference : 'tbProfileImage', 
		alt  : 'Profile Image',
		bind : {
			src : '{profileimg}'
		},
		cls: 'profile-image'		
	},{
		xtype : 'displayfield',
		bind: {
			value : '{usrname}',
		},
		reference : 'tbUserName'
	}, '->', {
		xtype : 'button',
		ui: 'toolbar',
		iconCls : 'x-fa fa-info-circle'
	}, {
		xtype : 'button',
		ui: 'toolbar',
		tooltip : 'Profile',		
		iconCls : 'x-fa fa-user',
		menu : [{
			text : 'Profile',
			iconCls : 'x-fa fa-user',
			tooltip : 'View profile',
			handler : 'onUserProfile'
		},{
			text : 'Change Password',
			iconCls : 'x-fa fa-key',
			tooltip : 'Change Password',
			handler : 'onChangePwd'
		}]
	}, {
		xtype : 'button',
		ui: 'toolbar',
		tooltip : 'Sign Out',
		iconCls : 'x-fa fa-sign-out',
		handler : 'onSignOut'
	} ]
});