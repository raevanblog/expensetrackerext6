Ext.define('expensetracker.view.message.MessageController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.message',
	onEmailMenuClick : function(menu, item, e, obj) {
			console.log(item);
	},
	setCurrentView(view, params) {
		var me = this;
		var viewer = me.getView().down('holder');
		
		if(!viewer || view === '' || (viewer.down() && viewer.down().xtype === view)){
            return false;
        }
	}
});