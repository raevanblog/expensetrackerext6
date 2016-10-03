Ext.define('expensetracker.view.login.LoginController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.login',
	listen : {
		controller : {
			'#' : {
				unmatchedroute : function(hash) {
					
				}
			}
		}
	},
	routes : {
		'login' : 'onLoginRoute',
		'main' : 'onMainRoute'
	},
	onLoginRoute : function(id) {
		var view = expensetracker.app.getMainView();
		var existing = view.child('component[xtype=app-main]');
		if(existing) {
			view.getLayout().setActiveItem(0);
			view.remove(existing);
		}		
	},
	onMainRoute : function(id) {
		var view = expensetracker.app.getMainView();
		var existing = view.child('component[xtype=app-main]');
		if(!existing) {
			var mainView = Ext.create({
				xtype: 'app-main'
			});
			view.add(mainView);
		}
		view.getLayout().setActiveItem(1);
	},
	onLogin: function(button) {
		this.redirectTo('main');
	}
});