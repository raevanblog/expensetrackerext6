Ext.define('expensetracker.view.login.ActivationController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.activation',
	onActivate : function(activateBtn) {
		var me = this;
		var view = me.getView();
		var username = me.lookup('activationuser').getValue();
		var activationkey = me.lookup('activationkey').getValue();
		view.setLoading('Activating...');
		Ext.Ajax.request({
			url : expensetracker.util.Url.getActivateUser(),
			method : 'POST',
			jsonData : Ext.JSON.encode({
				username : username,
				activationkey : activationkey
			}),
			success : function(response, opts) {
				view.setLoading(false);
				var errorLbl = me.lookup('activateerrorlbl');
				var response = Ext.decode(response.responseText);
				if (response.success) {
					expensetracker.util.Message.toast('Activation Successful');
					me.getView().destroy();
					window.location = "#login";
					Ext.widget('login');
				} else {
					errorLbl.update('<p>* ' + response.message + ' Mail To :' + expensetracker.util.Message.getMailTo('Activation failed for ' + username) + '</p>');
				}
			},
			failure : function(response, opts) {
				view.setLoading(false);
				var errorLbl = me.lookup('activateerrorlbl');
				errorLbl.update('<p>* ' + response.message + ' Mail To :' + expensetracker.util.Message.getMailTo() + '</p>');
			}
		});
	}
});