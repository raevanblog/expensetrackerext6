Ext.define('expensetracker.view.message.Message', {
	extend : 'Ext.container.Container',
	requires : [ 'expensetracker.view.message.Menu', 'expensetracker.view.message.Inbox', 'expensetracker.view.message.Compose', 'expensetracker.view.message.MessageController' ],
	xtype : 'message',
	alias : 'view.message',
	controller : 'message',
	listeners : {
		render : 'onRender'
	},
	platformConfig : {
		desktop : {
			layout : {
				type : 'hbox',
				align : 'stretch'
			}
		},
		'!desktop' : {
			type : 'vbox',
			align : 'stretch'
		}
	},
	items : [ {
		xtype : 'emailmenu',
		cls : 'shadow',
		style : 'border-width:1px;',
		margin : '20 20 20 20',
		platformConfig : {
			desktop : {
				width : '20%'
			},

			'!desktop' : {
				flex : 1
			}
		},
		cls : 'menu-email',
		listeners : {
			click : 'onEmailMenuClick'
		},
		height : '100%'
	}, {
		xtype : 'container',
		itemId : 'viewer',
		margin : '20 20 20 20',
		layout : {
			type : 'fit'
		},
		flex : 1
	} ]

});