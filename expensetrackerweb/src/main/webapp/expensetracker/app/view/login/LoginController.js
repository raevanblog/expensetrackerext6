Ext.define('expensetracker.view.login.LoginController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.login',
	onLogin: function(button) {
		expensetracker.app.setMainView('expensetracker.view.main.Main');
	}
});