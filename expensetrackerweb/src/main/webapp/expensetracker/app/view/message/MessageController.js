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

		if (view) {
			Ext.suspendLayouts();

			viewer.removeAll();

			viewer.add(Ext.apply({
				xtype : view
			}, params));

			Ext.resumeLayouts(true);
		}

	}
});