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
		params : {
			isWindow : true,
			type : 'NEW',
			modal : true,
			contrain : true,
			height : '85%',
			width : '80%' 
		},
		routeId : 'emailcompose'
	}, {
		text : 'Inbox',
		iconCls : 'x-fa fa-inbox',
		routeId : 'inbox'
	}, {
		text : 'Sent',
		iconCls : 'x-fa fa-check',
		routeId : 'emailsent'
	}]
})