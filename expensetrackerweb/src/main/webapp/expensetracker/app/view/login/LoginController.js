Ext.define('expensetracker.view.login.LoginController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.login',
	onLogin: function(button) {
		var view = expensetracker.app.getMainView();
		view.getLayout().setActiveItem(1);
	}
});