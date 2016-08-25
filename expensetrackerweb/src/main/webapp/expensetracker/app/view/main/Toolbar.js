Ext.define('expensetracker.view.main.Toolbar', {
	extend : 'Ext.Toolbar',
	xtype : 'maintoolbar',
	alias : 'view.toolbar',
	items : [ {
		xtype : 'component',
		userCls : 'app-name',
		html : '  Expense Tracker'
	}, '->', {
		xtype : 'button',
		ui : 'toolbar-button',
		userCls : 'toolbar-button',
		iconCls : 'x-fa fa-info-circle'
	}, {
		xtype : 'button',
		ui : 'toolbar-button',
		userCls : 'toolbar-button',
		iconCls : 'x-fa fa-user'
	}, {
		xtype : 'button',
		ui : 'toolbar-button',
		userCls : 'toolbar-button',
		iconCls : 'x-fa fa-sign-out'
	} ]
});