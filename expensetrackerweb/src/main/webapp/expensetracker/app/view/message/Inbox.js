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
		align : 'left',
		dataIndex: 'senderfname',
		renderer : function(value, metadata, record){
			return value + ' ' + record.get('senderlname');
		},
		width : 140
	}, {
		text : 'Subject',
		align : 'left',
		dataIndex : 'subject',
		flex : 1
	}, {
		xtype : 'datecolumn',
		align : 'left',
		text : 'Received',		
		dataIndex : 'msgdate',
		format : 'd/m/Y H:i:s A',
		width : 180
	}]
});