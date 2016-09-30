Ext.define('expensetracker.view.login.Login', {
	extend : 'Ext.container.Container',
	xtype : 'login',
	alias : 'view.login',
	layout : 'fit',
	requires : ['expensetracker.view.login.LoginController'],
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