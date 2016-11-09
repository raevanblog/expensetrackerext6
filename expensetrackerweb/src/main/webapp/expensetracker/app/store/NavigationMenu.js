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
			iconCls : 'x-fa fa-file-pdf-o',
			viewType : 'reports',
			leaf : true
		}, {
			text : 'Graph',
			iconCls : 'x-fa fa-bar-chart',
			viewType : 'graph',
			leaf : true
		} ]
	},
	autoLoad : false
});
