Ext.define('expensetracker.view.login.Login', {
	extend : 'Ext.container.Viewport',
	xtype : 'login',
	alias : 'view.login',
	requires : ['expensetracker.view.login.LoginController', 'expensetracker.view.main.Main'],
	controller: 'login',	
	items : [ {
		xtype: 'container',
		layout: {
			type: 'hbox',
			pack: 'middle'
		},
		items: [{
			xtype: 'form',
			width: 350,
			title: 'Login',
			bodyPadding: '5	5 0 5',
			layout: {
				type: 'vbox',
				align: 'stretch'
			},
			items: [{
				xtype: 'textfield',
				fieldLabel: 'Username',
				labelSeparator: '',
				flex: 1
			},{
				xtype: 'textfield',
				fieldLabel: 'Password',
				labelSeparator: '',
				flex: 1
			}],
			buttons: [{
				text: 'Login',
				handler: 'onLogin'
			}]
		}]
	}]

});