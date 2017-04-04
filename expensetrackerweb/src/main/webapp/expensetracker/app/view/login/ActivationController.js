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
				var response = Ext.decode(response.responseText);
				if (response.success) {
					expensetracker.util.Message.toast('Activation Successful');
					me.getView().destroy();
					window.location = "#login";
					Ext.widget('login');
				} else {
					me.updateErrorLbl(response.message);
				}
			},
			failure : function(response, opts) {
				view.setLoading(false);
				me.updateErrorLbl(response.message);
			}
		});
	},
	updateErrorLbl : function(error) {
		var me = this;
		var errorlabel = me.lookup('formerrorlabel');
		var errors = [];

		errors.push({
			name : 'Error',
			error : error.message
		});

		errorlabel.setErrors(errors);
	}
});