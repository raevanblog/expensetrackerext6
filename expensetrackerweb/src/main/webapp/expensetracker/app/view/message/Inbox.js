Ext.define('expensetracker.view.message.Inbox', {
	extend : 'Ext.grid.Panel',
	xtype : 'inbox',
	alias : 'view.inbox',
	cls : 'inbox shadow', 
	selModel: {
        selType: 'checkboxmodel',
        checkOnly: true,
        showHeaderCheckbox: true
    },
	border : 1,
    listeners: {
        cellclick: 'onGridCellItemClick'
    },
    headerBorders: false,
    rowLines: false,
	columns : [{
		text : 'From',
		dataIndex : 'msgFrom',
		width: 140
	}, {
		text : 'Subject',
		dataIndex : 'subject',
		flex : 1
	}, {
		text: '<span class="x-fa fa-paperclip"></span>',
		dataIndex: 'hasAttachments',		
		width: 75,
		renderer: function(value) {
			return value ? '<span class="x-fa fa-paperclip"></span>' : '';
		}
	}, {
		text : 'Date',
		dataIndex : 'date',
		width : 100
	}]
});