Ext.define('expensetracker.view.login.Login', {
	extend : 'Ext.container.Container',
	xtype : 'login',
	alias : 'view.login',
	plugins : 'viewport',
	layout : {
		type : 'border'
	},
	requires : [ 'expensetracker.view.login.LoginForm', 'expensetracker.view.login.RegisterForm', 'expensetracker.view.login.LoginController' ],
	controller : 'login',
	items : [ {
		xtype : 'toolbar',
		height : 64,
		cls : 'maintoolbar shadow',
		region : 'north',
		items : [ {
			xtype : 'component',
			html : '<div class="main-logo"><img src="resources/images/logo.png">Expense Tracker</div>',
			cls : 'logo-component',
			width : 250
		}, '->', {
			xtype : 'button',
			text : 'Register',
			ui : 'toolbar',
			handler : 'onOpenRegistration',
			tooltip : 'Register',
			iconCls : 'x-fa fa-user-plus'
		}, {
			xtype : 'button',
			text : 'Activate Account',
			ui : 'toolbar',
			handler : 'onOpenRegistration',
			tooltip : 'Activate Account',
			iconCls : 'x-fa fa-check-circle'
		} ],
		cls : 'maintoolbar'
	}, {
		xtype : 'container',
		reference : 'formcard',
		region : 'center',
		layout : {
			type : 'card'
		},
		items : [ {
			xtype : 'logincontainer',
			reference : 'logincontainer'
		}, {
			xtype : 'registercontainer',
			reference : 'registercontainer'
		} ]
	} ]

});