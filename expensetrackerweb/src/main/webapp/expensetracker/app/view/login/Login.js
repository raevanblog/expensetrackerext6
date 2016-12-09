Ext.define('expensetracker.view.login.Login', {
	extend : 'Ext.container.Container',
	xtype : 'login',
	alias : 'view.login',
	plugins : 'viewport',
	layout : {
		type : 'border'
	},
	requires : [ 'expensetracker.view.login.LoginForm', 'expensetracker.view.login.Activation', 'expensetracker.view.login.RegisterForm', 'expensetracker.view.login.LoginController' ],
	controller : 'login',
	items : [ {
		xtype : 'toolbar',
		border : 1,
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
		}],
		cls : 'maintoolbar'
	}, {
		xtype : 'container',
		reference : 'formcard',
		bind : {
			activeItem : '{activeItem}'
		},
		style : {
			backgroundColor : '#FFFFFF'
		},
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
		},{
			xtype : 'activationcontainer',
			reference : 'activationcontainer'
		} ]
	} ]

});