Ext.define('expensetracker.view.dashboard.Dashboard',{
	extend: 'Ext.Container',
	xtype: 'dashboard',
	alias: 'view.dashboard',
	cls: 'dashboard',	
	items: [{
		xtype: 'panel',
		title: 'Monthly Earning vs Expense',
		userCls: 'big-50 small-100 dashboard-item'
	},{
		xtype: 'panel',
		title: 'Top Expense',
		userCls: 'big-20 small-100 dashboard-item'
	},{
		xtype: 'panel',
		title: 'Expense Chart',
		userCls: 'big-20 small-100 dashboard-item'
	}, {
		xtype: 'panel',
		title: 'Profile',
		userCls: 'big-100 small-100 dashboard-item'
	}]
});