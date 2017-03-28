Ext.define('expensetracker.view.login.ActivationForm', {
	extend : 'Ext.container.Container',
	alias : 'view.activationform',
	xtype : 'activationform',
	scrollable : 'y',
	layout : {
		type : 'hbox',
		pack : 'middle'
	},
	items : [ {
		xtype : 'form',		
		platformConfig : {
			desktop : {
				width : 600
			},

			'!desktop' : {
				width : '80%'
			}
		},
		padding : '100 0 0 0',
		bodyPadding : '10 10 10 10',
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [ {
			xtype : 'textfield',
			fieldLabel : 'Username',
			reference : 'activationuser',
			labelAlign : 'top',
			height : 75,
			flex : 1,
			bind : {
				value : '{username}'
			},
			labelSeparator : ''
		}, {
			xtype : 'textfield',
			fieldLabel : 'Activation Key',
			reference : 'activationkey',
			labelAlign : 'top',
			height : 75,
			flex : 1,
			bind : {
				value : '{key}'
			},
			labelSeparator : ''
		},{
			xtype: 'button',
			text : 'Activate',
			scale : 'large',
			ui : 'soft-green',
			formBind : true,
			iconCls : 'x-fa fa-check',			
			handler : 'onActivate'
		},{
            xtype: 'errorstate',
			height : 50,
			padding : '15 0 0 20',
            reference: 'formerrorlabel',                      
            flex: 1            
        }]		
	}]
});