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
		if (!window.location.hash || window.location.hash === '#login') {
			this.redirectTo("expensedashboard");
		}
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
	filterGrid : function(gridsearchtext, newValue, oldValue, options) {
		var grid = this.lookup('expensegrid');
		grid.getStore().filter('itemName', newValue);
	},
	onAddExpenseRecord : function(addexpenseBtn) {
		var grid = this.lookup('expensegrid');
		var store = grid.getStore();
		var model = new expensetracker.model.Expense({
			itemName : '',
			expdate : new Date(),
			price : 0.0,
			qty : 0.0,
			username : 'shyamcse07'
		});
		store.insert(0, model);
		grid.getView().refresh();
	},
	onQtyChange : function(gridqtytext, newValue, oldValue, options) {
		var grid = this.lookup('expensegrid');
		var selection = grid.getSelectionModel().getSelection();
		var price = selection[0].get('price');
		var pricePerUnit = 0.0;
		if (newValue != 0.0) {
			pricePerUnit = price / newValue;
			selection[0].set('pricePerUnit', pricePerUnit);
		}
	},
	onPriceChange : function(gridqtytext, newValue, oldValue, options) {
		var grid = this.lookup('expensegrid');
		var selection = grid.getSelectionModel().getSelection();
		var qty = selection[0].get('qty');
		var pricePerUnit = 0.0;
		if (qty != 0.0) {
			pricePerUnit = newValue / qty;
			selection[0].set('pricePerUnit', pricePerUnit);
		}
	},
	onSaveOrUpdateExpense : function(saveBtn) {
		var grid = this.lookup('expensegrid');
		grid.setLoading("Saving...")
		grid.getStore().sync({
			success: function(batch) {
				grid.setLoading(false)
			}
		});
	},
	onDeleteExpense : function(deleteBtn) {
		var grig = this.lookup('expensegrid');
	}
});
