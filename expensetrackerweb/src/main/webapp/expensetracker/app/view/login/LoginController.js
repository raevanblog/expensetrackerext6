Ext.define('expensetracker.view.login.LoginController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.login',
	onLogin : function(button) {
		var me = this;
		var loginWindow = me.getView();
		var loginform = me.lookup('loginform');
		var username = me.lookup('username');
		var password = me.lookup('password');
		if (loginform.isValid()) {
			loginWindow.setLoading('Logging in...');
			loginform.submit({
				params : {
					credential : Ext.util.Base64.encode(username.getValue() + ':' + password.getValue())
				},
				success : function(form, action) {
					var response = Ext.decode(action.response.responseText);
					if (response.user !== null) {
						expensetracker.util.Session.setUser(response.user);
						expensetracker.util.Session.setCurrency({
							name : 'IND',
							symbol : '₹'
						});
						me.loadApplicationStore();
					}
					me.getView().destroy();
					Ext.widget('app-main');
				},
				failure : function(form, action) {
					loginWindow.setLoading(false);
					var errorlbl = me.lookup('errorlbl');
					var message = action.result.message;
					errorlbl.setText(message);
				}
			})
		}
	},
	onEnter : function(textfield, e) {
		var me = this;
		var loginBtn = me.lookup('loginBtn');
		if (Ext.event.Event.ENTER === e.keyCode) {
			me.onLogin(loginBtn);
		}
	},
	loadApplicationStore : function() {
		Ext.getStore('ExpenseDock').load();
		Ext.getStore('ExpenseCategory').load();
		Ext.getStore('ExpenseType').load();
		Ext.getStore('ExpenseName').load();
		Ext.getStore('IncomeType').load();
	}
});