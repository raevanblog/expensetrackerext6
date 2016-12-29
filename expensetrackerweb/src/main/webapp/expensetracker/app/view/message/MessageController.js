Ext.define('expensetracker.view.message.MessageController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.message',
	onRender : function() {
		var me = this;
		me.setCurrentView('inbox');
	},
	onEmailMenuClick : function(menu, item, e, obj) {
		var me = this;
		me.setCurrentView(item.routeId, item.params);
	},
	setCurrentView : function(view, params) {
		var me = this;
		var viewer = me.getView().down('#viewer');

		if (!viewer || view === '' || (viewer.down() && viewer.down().xtype === view)) {
			return false;
		}

		if(params && params.isWindow) {
			var win = Ext.apply({
				xtype : view				
			},params);
			Ext.create(win);			
		}else {
			Ext.suspendLayouts();

			viewer.removeAll();

			viewer.add(Ext.apply({
				xtype : view
			}, params));

			Ext.resumeLayouts(true);
		}

	},
	onRenderInbox : function(inbox) {
		var me = this;
		var view = me.getView();
		var store = Ext.create('expensetracker.store.Message');
		inbox.setLoading('Loading...');
		store.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				isNew : false
			},
			callback : function(records, operation, success) {
				inbox.setLoading(false);
				if (!success) {
					var response = Ext.JSON.decode(operation.getError().response.responseText);
					expensetracker.util.Message.toast(response.status_Message);
					if (401 === response.status_Code) {
						me.fireEvent('navigatelogin');
						if (view != null) {
							view.close();
						}
					}
				}
			}
		});
		inbox.bindStore(store);
	}
});