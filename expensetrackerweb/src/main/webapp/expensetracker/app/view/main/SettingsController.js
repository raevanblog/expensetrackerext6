Ext.define('expensetracker.view.main.SettingsController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.settings',
	onRender : function(settingsWindow) {
		var me = this;		
		if(!expensetracker.util.Session.isFirstLogin()) {
			var preferences = expensetracker.util.Session.getPreferences();
			var currency  = expensetracker.util.Session.getCurrency();		
			var currencyField  = me.lookup('currency');
			var altEmailField = me.lookup('alternateemail');			
			currencyField.setValue(currency.id);
			altEmailField.setValue(preferences.rcvryemail);
		}
	},	
	onSaveSettings: function() {
		var me = this;
		var view = me.getView();
		var method = expensetracker.util.Session.isFirstLogin() ? 'POST' : 'PUT';
		var currency  = me.lookup('currency').getSelection();
		var altemail = me.lookup('alternateemail').getValue();
		var username = expensetracker.util.Session.getUsername();
		
		if(currency != null && altemail != null) {
			view.setLoading('Saving...');
			var jsonData = {
				username : username,
				rcvryemail : altemail,
				currency : {
					id: currency.id
				}
			};
			Ext.Ajax.request({
				url : expensetracker.util.Url.getUserSettings(),
				method : method,
				jsonData : jsonData,
				success : function(response, opts) {
					view.setLoading(false);
					var resp = Ext.decode(response.responseText);
					if(200 === resp.status_Code) {
						expensetracker.util.Session.reload(me);						
						if(expensetracker.util.Session.isFirstLogin()) {						
							view.close();
						} else {
							expensetracker.util.Message.toast('Settings Updated');							
						}
					}
					console.log(response);
				},
				failure : function(response, opts) {
					view.setLoading(false);
				}
			});
		}
		
	}
});