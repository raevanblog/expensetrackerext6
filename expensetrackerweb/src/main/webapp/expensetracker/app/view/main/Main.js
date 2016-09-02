/**
 * This class is the main view for the application. It is specified in app.js as
 * the "mainView" property. That setting automatically applies the "viewport"
 * plugin causing this view to become the body element (i.e., the viewport).
 * 
 * TODO - Replace this content of this view to suite the needs of your
 * application.
 */
Ext.define('expensetracker.view.main.Main', {
	extend : 'Ext.container.Viewport',
	xtype : 'app-main',

	requires : [ 'expensetracker.view.main.MainCard', 'expensetracker.view.main.Toolbar', 'expensetracker.view.main.MainController',
			'expensetracker.view.main.MainModel' ],

	controller : 'main',
	viewModel : 'main',
	listeners : {
		render : 'onMainViewRender'
	},
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	items : [ {
		xtype : 'maintoolbar',
		cls : 'maintoolbar',	
		height : 70
	}, {
		xtype : 'maincard',
		reference : 'maincard',
		flex : 1
	} ]

});
