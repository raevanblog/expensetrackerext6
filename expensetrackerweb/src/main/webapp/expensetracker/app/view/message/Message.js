Ext.define('expensetracker.view.message.Message', {
	extend : 'Ext.container.Container',
	requires : [ 'expensetracker.view.message.Menu', 'expensetracker.view.message.Inbox', 'expensetracker.view.message.Compose', 'expensetracker.view.message.Details', 'expensetracker.view.message.MessageController', 'expensetracker.store.Message' ],
	xtype : 'message',
	alias : 'view.message',
	controller : 'message',
	scrollable: 'y',
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
	items : [ /*{
		xtype : 'emailmenu',
		cls : 'shadow',		
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
	},*/ {
		xtype : 'container',
		itemId : 'viewer',
		margin : '20 20 20 20',
		layout : {
			type : 'fit'
		},
		flex : 1
	} ]

});