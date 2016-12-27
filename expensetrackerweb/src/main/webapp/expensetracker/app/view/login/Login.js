Ext.define('expensetracker.view.login.Login', {
	extend : 'Ext.container.Container',
	xtype : 'login',
	alias : 'view.login',
	plugins : 'viewport',
	layout : {
		type : 'border'
	},
	requires : [ 'expensetracker.view.login.LoginForm', 'expensetracker.view.login.ActivationWindow', 'expensetracker.view.login.RegisterForm', 'expensetracker.view.login.LoginController',
			'expensetracker.view.login.LoginViewModel' ],
	controller : 'login',
	viewModel : 'loginviewmodel',
	listeners : {
		render : 'onRender'
	},
	items : [ {
		xtype : 'toolbar',
		border : 1,
		height : 64,
		cls : 'maintoolbar shadow',
		region : 'north',
		overflowHandler : 'menu',
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
			text : 'Activate User',
			ui : 'toolbar',
			tooltip : 'Activate User',
			iconCls : 'x-fa fa-check',
			handler : 'onActivateUser'
		}, {
			xtype : 'button',
			text : 'Contact Us',
			ui : 'toolbar',
			tooltip : 'Contact Us',
			handler : 'onContactUs',
			iconCls : 'x-fa fa-envelope-o'
		} ],
		cls : 'maintoolbar'
	}, {
		xtype : 'container',
		reference : 'formcard',
		style : {
			backgroundColor : '#FFFFFF'
		},
		region : 'center',
		layout : {
			type : 'card'
		},
		items : [ {
			xtype : 'logincontainer',
			cls : 'container-background',
			reference : 'logincontainer'
		}, {
			xtype : 'registercontainer',
			cls : 'container-background',
			reference : 'registercontainer'
		} ]
	} ]

});