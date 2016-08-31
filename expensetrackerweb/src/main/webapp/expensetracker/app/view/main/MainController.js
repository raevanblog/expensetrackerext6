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
	listen : {
		controller : {
			'#' : {
				unmatchedroute : 'onRouteChange'
			}
		}
	},
	routes : {
		':node' : 'onRouteChange'
	},
	onRouteChange : function(id) {
		this.setCurrentView(id);
	},
	lastView : null,
	setCurrentView : function(hashTag) {
		var me = this;
		var hashTag = (hashTag || '').toLowerCase();

		var refs = me.getReferences();
		var mainCard = refs.mainCard;
		var mainLayout = mainCard.getLayout();
		var navigationList = refs.navigationMenu;
		var store = navigationList.getStore();
		var node = store.findNode('routeId', hashTag) || store.findNode('viewType', hashTag);
		var view = (node && node.get('viewType'));
		var lastView = me.lastView;
		var existingItem = mainCard.child('component[routeId=' + hashTag + ']');
		var newView;

		if (lastView && lastView.isWindow) {
			lastView.destroy();
		}

		lastView = mainLayout.getActiveItem();

		if (!existingItem) {
			newView = Ext.create({
				xtype : view,
				routeId : hashTag,
				hideMode : 'offsets'
			});
		}

		if (!newView || !newView.isWindow) {
			if (existingItem) {
				if (existingItem !== lastView) {
					mainLayout.setActiveItem(existingItem);
				}
				newView = existingItem;
			} else {
				Ext.suspendLayouts();
				mainLayout.setActiveItem(mainCard.add(newView));
				Ext.resumeLayouts(true);
			}
		}

		navigationList.setSelection(node);

		if (newView.isFocusable(true)) {
			newView.focus();
		}

		me.lastView = newView;
	},
	onNavigationSelection : function(treelist, node) {
		var toNode = node && (node.get('routeId') || node.get('viewType'));

		if (toNode) {
			this.redirectTo(toNode);
		}
	},
	onMainViewRender : function() {
		if (!window.location.hash) {
			this.redirectTo("dashboard");
		}
	}

});
