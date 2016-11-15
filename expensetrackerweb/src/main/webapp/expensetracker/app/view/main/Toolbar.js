Ext.define('expensetracker.view.main.Toolbar', {
	extend : 'Ext.toolbar.Toolbar',
	alias : 'view.maintoolbar',
	xtype : 'maintoolbar',
	items : [ {
		xtype : 'component',
		html: '<div class="main-logo"><img src="resources/images/logo.png">Expense Tracker</div>',
		reference : 'logocomponent',
		width : 250,
		cls : 'logo-component'
	}, {
		xtype : 'button',
		ui: 'toolbar',
		iconCls : 'x-fa fa-bars',
		reference : 'toggleNavigation',
		handler : 'onToggleNavigation'
	}, '->', {
		xtype : 'button',
		ui: 'toolbar',
		iconCls : 'x-fa fa-info-circle'
	}, {
		xtype : 'button',
		ui: 'toolbar',
		tooltip : 'Profile',		
		iconCls : 'x-fa fa-user',
		handler : 'onUserProfile'
	}, {
		xtype : 'button',
		ui: 'toolbar',
		tooltip : 'Sign Out',
		iconCls : 'x-fa fa-sign-out',
		handler : 'onSignOut'
	} ]
});