/**
 * This class is the main view for the application. It is specified in app.js as
 * the "mainView" property. That setting automatically applies the "viewport"
 * plugin causing this view to become the body element (i.e., the viewport).
 * 
 * TODO - Replace this content of this view to suite the needs of your
 * application.
 */
Ext.define('expensetracker.view.main.Main', {
	extend : 'Ext.container.Container',
	xtype : 'app-main',
	layout : 'fit',
	requires : [ 'expensetracker.view.main.MainDashboard', 'expensetracker.view.main.Toolbar', 'expensetracker.view.main.MainController', 'expensetracker.view.main.MainModel',
			'expensetracker.view.profile.User', 'expensetracker.view.profile.ChangePassword' ],
	plugins : 'viewport',
	controller : 'main',
	viewModel : 'main',
	listeners : {
		afterrender : 'onMainViewRender'
	},
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	items : [ {
		xtype : 'maintoolbar',
		cls : 'maintoolbar shadow',
		height : 64
	}, {
		xtype : 'maindashboard',
		reference : 'mainDashboard',
		flex : 1
	} ]

});
