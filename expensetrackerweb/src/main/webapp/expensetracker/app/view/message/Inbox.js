Ext.define('expensetracker.view.message.Inbox', {
	extend : 'Ext.grid.Panel',
	xtype : 'inbox',
	alias : 'view.inbox',	
	cls : 'inbox shadow',	
	selModel : {
		selType : 'checkboxmodel',
		checkOnly : true,
		showHeaderCheckbox : true
	},
	border : 1,
	listeners : {
		cellclick : 'onGridCellItemClick',
		afterrender : 'onRenderInbox'
	},
	headerBorders : false,
	rowLines : false,
	columns : [ {
		text : 'From',
		dataIndex : 'msgfrom',
		width : 140
	}, {
		text : 'Subject',
		dataIndex : 'subject',
		flex : 1
	}]
});