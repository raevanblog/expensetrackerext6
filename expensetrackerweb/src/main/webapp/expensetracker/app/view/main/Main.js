/**
 * This class is the main view for the application. It is specified in app.js as
 * the "mainView" property. That setting causes an instance of this class to be
 * created and added to the Viewport container.
 * 
 * TODO - Replace this content of this view to suite the needs of your
 * application.
 */
Ext.define('expensetracker.view.main.Main', {
	extend : 'Ext.Container',
	xtype : 'app-main',

	requires : [ 'expensetracker.view.main.MainController',
			'expensetracker.view.main.MainModel',
			'expensetracker.view.main.Toolbar',
			'expensetracker.store.NavigationStore' ],
	controller : 'main',
	viewModel : 'main',
	layout : 'hbox',
	items : [ {
		xtype : 'maintoolbar',
		userCls : 'main-toolbar',
		docked : 'top'
	}, {
		xtype : 'container',
		userCls : 'nav-container',
		items : [ {
			xtype : 'treelist',
			ui : 'navigation',
			store : {
				type : 'navigationstore'
			},
			expanderFirst : false,
			expanderOnly : false
		} ]
	}, {
		xtype : 'navigationview',
		userCls : 'main-container',
		navigationBar : false,
		flex : 1
	} ]
});
