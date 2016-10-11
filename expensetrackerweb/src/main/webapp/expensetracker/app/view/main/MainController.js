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
				unmatchedroute : function(hash) {
					console.log(hash);
				}
			}
		}
	},
	routes : {
		'main/:node' : 'onRouteChange'
	},
	onRouteChange : function(id) {
		console.log("asdasdds");
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
			this.redirectTo('main/' + toNode);
		}
	},
	onMainViewRender : function() {
		var me = this;
		me.setCurrentView('expensedashboard');
	},
	onToggleNavigation : function(button) {
		var me = this;
		var refs = me.getReferences();
		var navMenu = refs.navigationMenu;
		var mainDashboard = refs.mainDashboard;

		var collapsing = !navMenu.getMicro();

		var new_width = collapsing ? 64 : 250;

		if (Ext.isIE9m || !Ext.os.is.Desktop) {
			Ext.suspendLayouts();

			refs.logocomponent.setWidth(new_width);
			navMenu.setWidth(new_width);
			navMenu.setMicro(collapsing);
			if (collapsing) {
				refs.logocomponent.update('ET');
			} else {
				refs.logocomponent.update('Expense Tracker');
			}

			Ext.resumeLayouts();

			mainDashboard.layout.animatePolicy = mainDashboard.layout.animate = null;
			mainDashboard.updateLayout();
		} else {

			if (!collapsing) {

				navMenu.setMicro(false);
			}

			refs.logocomponent.animate({
				dynamic : true,
				to : {
					width : new_width
				}
			});

			navMenu.width = new_width;

			navMenu.el.addCls('nav-tree-animating');

			mainDashboard.updateLayout({
				isRoot : true
			});

			if (collapsing) {
				navMenu.on({
					afterlayoutanimation : function() {
						navMenu.setMicro(true);
						navMenu.el.removeCls('nav-tree-animating');
					},
					single : true
				});
			}
		}
	},
	onUserProfile : function(profileBtn) {
		var me = this;
		var profileWindow = Ext.create('expensetracker.view.profile.User', {
			modal : true,
			height : me.getView().getHeight() - 70,
			width : (me.getView().getWidth() - 250) / 2,
			x : 250,
			y : 70
		});
		profileWindow.show();
	},
	onSignOut : function(signOutBtn) {
		var me = this;
		Ext.Ajax.request({
			url : expensetracker.util.Url.getLogout(),
			method : 'POST',
			success : function(response, opts) {
				var response = Ext.decode(response.responseText);
				me.getView().destroy();
				Ext.widget('login');
			},
			failure : function(response, opts) {

			}
		});

	}
});
