/**
 * This class is the controller for the main view for the application. It is
 * specified as the "controller" of the Main view class.
 * 
 * TODO - Replace this content of this view to suite the needs of your
 * application.
 */
Ext.define('expensetracker.view.main.MainController', {
	extend : 'Ext.app.ViewController',

	alias : 'controller.main',
	listen: {
		controller: {
			'#': {
				unmatchedroute: 'handleUnmatchedRoute'
			}
		}
	},
	routes: {
		':view':'onRouteChange'
	},
	
	onRouteChange: function(hash) {
		var me = this;
		hash = (hash || '').toLowerCase();
		
		var refs = me.getReferences();
		var mainView = refs.mainView;
		var navigationMenu = refs.navigationMenu;
		
		var store = navigationMenu.getStore();
		var node = store.findNode('routeId', hash) || store.findNode('viewXType', hash);
		
		var item = mainView.child('component[routeId=' +hash +']');
		
		if(item === null) {
			item = mainView.add({
				xtype: node.get('viewXType'),
				routeId: hash
			});
		}
		
		mainView.setActiveItem(item);
		navigationMenu.setSelection(node);
	},
	
	handleUnmatchedRoute: function(hash) {
		
	},

	onNavItemClick: function(navigator, navInfo, opts) {
				
	},
	
	onNavItemSelectionChange: function(navigator, node) {
		var to = node && (node.get('routeId') || node.get('viewXType'))
		if(to) {
			this.redirectTo(to);
		}
	},
	
	onToggleNavigation: function(button) {
			
	}
		
});
