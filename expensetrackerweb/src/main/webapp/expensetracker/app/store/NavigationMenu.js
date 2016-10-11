Ext.define('expensetracker.store.NavigationMenu', {
	extend : 'Ext.data.TreeStore',
	storeId : 'NavigationMenu',
	alias : 'store.navigationmenu',
	fields : [ {
		name : 'text'
	} ],
	root : {
		expanded : true,
		children : [ {
			text : 'Dashboard',
			iconCls : 'x-fa fa-desktop',
			viewType : 'expensedashboard',
			routeId : 'expensedashboard',
			leaf : true
		}, {
			text : 'Expense Sheet',
			iconCls : 'x-fa fa-money',
			viewType : 'expenseview',
			leaf : true
		}, {
			text : 'Reports',
			iconCls : 'x-fa fa-search',
			viewType : 'reports',
			leaf : true
		}, {
			text : 'Charts',
			iconCls : 'x-fa fa-pie-chart',
			viewType : 'charts',
			leaf : true
		} ]
	}
});
