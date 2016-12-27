Ext.define('expensetracker.view.message.Menu', {
	extend : 'Ext.menu.Menu',
	xtype : 'emailmenu',
	alias : 'view.emailmenu',
	title : 'Message',
	titleAlign : 'center',
	iconCls : 'x-fa fa-envelope',
	floating: false,	
	items : [{
		text : 'Compose',
		iconCls : 'x-fa fa-edit',
		routeId : 'emailcompose'
	}, {
		text : 'Inbox',
		iconCls : 'x-fa fa-inbox',
		routeId : 'emailinbox'
	}, {
		text : 'Sent',
		iconCls : 'x-fa fa-check',
		routeId : 'emailsent'
	}]
})