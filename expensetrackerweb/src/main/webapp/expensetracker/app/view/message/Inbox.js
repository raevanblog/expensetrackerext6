Ext.define('expensetracker.view.message.Inbox', {
	extend : 'Ext.grid.Panel',
	xtype : 'inbox',
	alias : 'view.inbox',	
	cls : 'inbox shadow',
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
			metadata.tdAttr = 'data-qtip="' + value + '"';
			if('Y' === record.get('isNew')) {
				return '<b>' + value + ' ' + record.get('senderlname') + '</b>';
			} else {
				return value + ' ' + record.get('senderlname');
			}
		},
		width : 140
	}, {
		text : 'Subject',
		align : 'left',
		dataIndex : 'subject',
		renderer : function(value, metadata, record){
			metadata.tdAttr = 'data-qtip="' + value + '"';
			if('Y' === record.get('isNew')) {
				return '<b>' + value + '</b>';
			} else {
				return value;
			}
		},
		flex : 1
	}, {
		xtype : 'datecolumn',
		align : 'left',
		text : 'Received',		
		dataIndex : 'msgdate',		
		renderer : function(value, metadata, record){
			value = Ext.Date.format(value, 'd/m/Y H:i:s A');
			metadata.tdAttr = 'data-qtip="' + value + '"';
			if('Y' === record.get('isNew')) {
				return '<b>' + value + '</b>';
			} else {
				return value;
			}
		},
		width : 200
	}]
});