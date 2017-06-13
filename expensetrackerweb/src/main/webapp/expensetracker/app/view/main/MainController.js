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
	lastView : null,
	listen : {
		controller : {
			'#' : {
				unmatchedroute : function(hash) {
					console.log(hash);
				}
			},
			'*' : {
				navigatelogin : 'navigateToLogin',
				updateprofile : 'updateProfile'
			}
		}
	},
	routes : {
		'main/:node' : 'onRouteChange'
	},
	onRouteChange : function(id) {
		this.setCurrentView(id);
	},
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
		var model = me.getView().getViewModel();
		var name = me.lookup('tbUserName');		
		me.updateProfile();
		me.setCurrentView('expensedashboard');
	},
	onToggleNavigation : function(button) {
		var me = this;
		var model = me.getView().getViewModel();
		var refs = me.getReferences();
		var navMenu = refs.navigationMenu;
		var mainDashboard = refs.mainDashboard;

		var collapsing = !navMenu.getMicro();

		var new_width = collapsing ? expensetracker.util.Constants.getNavBarWidth() : expensetracker.util.Constants.getNavBarExpandedWidth();

		model.set('navBarWidth', new_width);

		if (Ext.isIE9m || !Ext.os.is.Desktop) {
			Ext.suspendLayouts();

			refs.logocomponent.setWidth(new_width);
			navMenu.setWidth(new_width);
			navMenu.setMicro(collapsing);
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
		var model = me.getView().getViewModel();
		var profileWindow = Ext.create('expensetracker.view.profile.User', {
			modal : true,
			height : expensetracker.util.Constants.getWindowHeight(),
			width : expensetracker.util.Constants.getWindowWidth(),
			x : expensetracker.util.Constants.getWindowX(),
			y : expensetracker.util.Constants.getWindowY()
		});
		profileWindow.show();
	},
	onChangePwd : function(chgPwdBtn) {			
		var chgPwdWindow = Ext.create('expensetracker.view.profile.ChangePassword', {
			modal : true,			
			height : expensetracker.util.Constants.getWindowHeight(),
			width : expensetracker.util.Constants.getWindowWidth(),
			x : expensetracker.util.Constants.getWindowX(),
			y : expensetracker.util.Constants.getWindowY()
		});
		chgPwdWindow.show();
	},
	onChangeSettings : function(chgSettingsBtn) {
		var settingsWindow = Ext.create('expensetracker.view.main.Settings', {
			modal : true			
		});
		settingsWindow.show();
	},
	onSignOut : function(signOutBtn) {
		var me = this;
		Ext.Ajax.request({
			url : expensetracker.util.Url.getLogout(),
			method : 'POST',
			success : function(response, opts) {
				var response = Ext.decode(response.responseText);
				me.navigateToLogin();
			},
			failure : function(response, opts) {

			}
		});

	},
	navigateToLogin : function() {
		var me = this;
		me.getView().destroy();
		window.location = '#login';
		Ext.create({
			xtype : 'login'
		});
	},
	updateProfile : function() {
		var me = this;
		var model = me.getView().getViewModel();
		model.set('usrname', expensetracker.util.Session.getName());
		var picture = expensetracker.util.Session.getProfilePicture();
		var profileImg = me.lookup('tbProfileImage');				
		if (picture === null) {
			var sex = expensetracker.util.Session.getUserSex();
			if ('M' === sex) {
				picture = 'resources/images/male-profile.png';			
			} else {
				picture = 'resources/images/female-profile.png';			
			}
			model.set('profileimg', picture);
		} else {
			model.set('profileimg', picture);
		}
		
		me.tips = new Ext.tip.ToolTip({
			target : profileImg.el,
			anchor : 'top',
			width : 165,
			html : '<img alt="Profile Picture" style="height:150px; width:150px; border-radius: 75px;" src="' + picture + '"/>'
		});	
	},
	onMessage : function(messageBtn) {
		var me = this;
		var msgWindow = Ext.create('Ext.window.Window', {
			modal : true,
			title : 'Message',
			closeToolText : 'Close',
			height : expensetracker.util.Constants.getWindowHeight(),
			width : expensetracker.util.Constants.getWindowWidth(),
			x : expensetracker.util.Constants.getWindowX(),
			y : expensetracker.util.Constants.getWindowY(),
			layout : {
				type : 'fit'
			},
			items : [ {
				xtype : 'message'
			} ]
		});
		msgWindow.show();
	}

});
