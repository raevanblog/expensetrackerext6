Ext.define('expensetracker.view.main.Toolbar', {
	extend : 'Ext.toolbar.Toolbar',
	alias : 'view.maintoolbar',
	xtype : 'maintoolbar',
	items : [ {
		xtype : 'component',
		html : 'Expense Tracker',
		reference : 'logocomponent',
		width : 250,
		cls : 'logo-component'
	}, {
		xtype : 'button',
		iconCls : 'x-fa fa-bars',
		reference : 'toggleNavigation',
		handler : 'onToggleNavigation'
	}, '->', {
		xtype : 'button',
		iconCls : 'x-fa fa-info-circle'
	}, {
		xtype : 'button',
		tooltip: 'Profile',
		iconCls : 'x-fa fa-user',
		handler : 'onUserProfile'
	}, {
		xtype : 'button',
		tooltip: 'Sign Out',
		iconCls : 'x-fa fa-sign-out',
		handler : 'onSignOut'
	} ]
});