Ext.define('expensetracker.view.main.Toolbar', {
	extend : 'Ext.toolbar.Toolbar',
	alias : 'view.maintoolbar',
	xtype : 'maintoolbar',
	overflowHandler : 'menu',
	items : [ {
		xtype : 'component',
		html : '<div class="main-logo"><img src="resources/images/logo.png">Expense Tracker</div>',
		reference : 'logocomponent',
		width : expensetracker.util.Constants.getNavBarWidth(),
		cls : 'logo-component'
	}, /*
		 * { xtype : 'button', ui: 'toolbar', iconCls : 'x-fa fa-bars',
		 * reference : 'toggleNavigation', handler : 'onToggleNavigation' },
		 */{
		xtype : 'image',
		height : 35,
		width : 35,
		reference : 'tbProfileImage',
		alt : 'Profile Image',
		bind : {
			src : '{profileimg}'
		},
		cls : 'profile-image'
	}, {
		xtype : 'displayfield',
		bind : {
			html : '<h3>{usrname}</h3>'
		},
		reference : 'tbUserName'
	}, '->', {
		xtype : 'button',
		ui : 'toolbar',
		text : 'Notification',
		handler : 'onMessage',
		iconCls : 'x-fa fa-envelope'
	}, {
		xtype : 'button',
		ui : 'toolbar',
		text : 'Profile',
		tooltip : 'Profile',
		iconCls : 'x-fa fa-user',
		menu : [ {
			text : 'View Profile',
			iconCls : 'x-fa fa-user',
			tooltip : 'View profile',
			handler : 'onUserProfile'
		}, {
			text : 'Change Password',
			iconCls : 'x-fa fa-lock',
			tooltip : 'Change Password',
			handler : 'onChangePwd'
		}, {
			text : 'Settings',
			iconCls : 'x-fa fa-cog',
			tooltip : 'Settings',
			handler : 'onChangeSettings'
		} ]
	}, {
		xtype : 'button',
		text : 'Sign Out',
		ui : 'toolbar',
		tooltip : 'Sign Out',
		iconCls : 'x-fa fa-sign-out',
		handler : 'onSignOut'
	} ]
});