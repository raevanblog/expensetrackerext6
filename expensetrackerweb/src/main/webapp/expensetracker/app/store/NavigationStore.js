Ext.define('expensetracker.store.NavigationStore', {
	extend : 'Ext.data.TreeStore',
	alias : 'store.navigationstore',
	fields : [ {
		name : 'text'
	} ],
	root : {
		expanded : true,
		children : [ {
			text : 'Dashboard',
			iconCls : 'x-fa fa-desktop',
			routeId: 'dashboard',
			viewXType: 'dashboard',
			leaf : true
		}, {
			text : 'Reports',
			iconCls : 'x-fa fa-search',			
			viewXType: 'reports',			
			leaf : true
		} ]
	}
});