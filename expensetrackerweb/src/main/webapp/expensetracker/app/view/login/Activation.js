Ext.define('expensetracker.view.login.Activation', {
	extend: 'Ext.container.Container',	
	xtype : 'activation',	
	alias : 'view.activation',
	plugins : 'viewport',
	requires : ['expensetracker.view.login.ActivationForm', 'expensetracker.view.login.ActivationController'],
	controller : 'activation',
	layout : {
		type : 'border'
	},	
	items: [{
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
		}],
		cls : 'maintoolbar'
	},{
		xtype : 'container',
		reference : 'formcard',
		region : 'center',
		layout : {
			type : 'fit'
		},
		items: [{
			xtype : 'activationform',
			style : {
				backgroundColor : '#FFFFFF'
			}
		}]
	}]
});