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
					loginWindow.setLoading(false);
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
					errorlbl.update('<p>* ' + message);
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
		Ext.getStore('ExpenseType').load();
		Ext.getStore('ExpenseName').load();
		Ext.getStore('IncomeType').load();
	},
	onOpenRegistration : function(registrationBtn) {
		var me = this;
		var card = me.lookup('formcard');
		card.getLayout().setActiveItem(1);
	},
	onCloseRegister : function(closeRegBtn) {
		var me = this;
		var card = me.lookup('formcard');
		var registerform = me.lookup('registerform');
		registerform.reset();
		card.getLayout().setActiveItem(0);
	},	
    onActivateUser : function(actUserBtn) {
		var me = this;
		var window = Ext.create('expensetracker.view.login.ActivationWindow', {
			modal : true,
			reference : 'activationwindow'
		});
		window.show();
	},
	onUserNameChage : function(textfield, newvalue, oldvalue) {
		var me = this;
		var usrNmeAvailInd = me.lookup('usrNmeAvailInd');
		textfield.isChanged = true;
		if (!newvalue) {
			usrNmeAvailInd.update('');
		}
	},	
	onFocusOutUserName : function(textfield, event) {
		var me = this;
		var registercontainer = me.lookup('registercontainer');
		var usrNmeAvailInd = me.lookup('usrNmeAvailInd');
		var username = textfield.getValue();
		if (textfield.isValid()) {
			if (textfield.isChanged) {
				textfield.isChanged = false;
				if (username) {
					registercontainer.setLoading('Checking Availability...');
					Ext.Ajax.request({
						url : expensetracker.util.Url.getUserNameAvailability(),
						method : 'GET',
						params : {
							checkAvailable : username
						},
						success : function(response, opts) {
							registercontainer.setLoading(false);
							var response = Ext.decode(response.responseText);
							var isAvailable = response.Available;
							textfield.isAvailable = isAvailable;
							if (isAvailable) {
								usrNmeAvailInd.update('<img src="resources/images/check.ico"/> ' + response.message);
							} else {
								usrNmeAvailInd.update('<img src="resources/images/cross.ico"/> ' + response.message);
							}
						},
						failure : function(response, opts) {
							registercontainer.setLoading(false);
						}
					});
				}
			}
		}
	},
	onRegisterUser : function(registerBtn) {
		var me = this;
		var card = me.lookup('formcard');
		var usrName = me.lookup('regUsername');
		var registerform = me.lookup('registerform');
		var registercontainer = me.lookup('registercontainer');

		if (registerform.isValid()) {
			if (usrName.isAvailable) {
				registercontainer.setLoading('Registering...');
				registerform.submit({
					success : function(form, action) {
						registercontainer.setLoading(false);
						var response = Ext.decode(action.response.responseText);
						if (response.isUserRegistered) {
							registerform.reset();
							card.getLayout().setActiveItem(0);
						}
						expensetracker.util.Message.toast(response.message);
					},
					failure : function(form, action) {
						registercontainer.setLoading(false);
						var response = Ext.decode(action.response.responseText);
						expensetracker.util.Message.toast(response.message);
					}
				});
			} else {
				usrName.markInvalid('Username already taken');
			}
		}
	},
	onSendActivationMail : function(sendBtn) {
		var me = this;		
		var form = me.lookup('activationwindowform');
		var username = me.lookup('activationwindowuser').getValue();		
		var errlbl = me.lookup('activationwindowerrlbl');
		var activationwindow = me.lookup('activationwindow');
		activationwindow.setLoading('Sending...');
		Ext.Ajax.request({
			url : expensetracker.util.Url.getActivationMail(),
			method : 'POST',
			jsonData : Ext.JSON.encode({
				username : username				
			}),
			success : function(response, opts) {
				activationwindow.setLoading(false);				
				form.reset();
				var response = Ext.decode(response.responseText);
				if(response.success) {
					expensetracker.util.Message.toast(response.message);					
				}else{
					var errlbl = me.lookup('activationwindowerrlbl');					
					errlbl.update('<p>* '+response.message + ' Mail To :' + expensetracker.util.Message.getMailTo(username) + '</p>');
				}
			},
			failure : function(response, opts) {	
				activationwindow.setLoading(false);			
				var errlbl = me.lookup('activationwindowerrlbl');
				errorLbl.update('<p>* '+response.message + ' Mail To :' + expensetracker.util.Message.getMailTo() + '</p>');
			}
		});
	}
});