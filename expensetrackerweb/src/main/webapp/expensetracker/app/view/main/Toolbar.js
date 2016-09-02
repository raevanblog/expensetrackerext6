Ext.define('expensetracker.view.main.Toolbar', {
	extend : 'Ext.toolbar.Toolbar',
	alias : 'view.maintoolbar',
	xtype : 'maintoolbar',
	items : [ {
		xtype : 'component',
		html : '  Expense Tracker',
		reference : 'logcomponent',
		width : 250,
		cls : 'logo-component'
	}, {
		xtype : 'button',
		iconCls : 'x-fa fa-bars',
		reference : 'toggleNavigation',
		handler : 'onToggleNavigation'
	}, '->', {
		xtype : 'button',
		iconCls : 'x-fa fa-info-circle'
	}, {
		xtype : 'button',
		iconCls : 'x-fa fa-user'
	}, {
		xtype : 'button',
		iconCls : 'x-fa fa-sign-out'
	} ]
});