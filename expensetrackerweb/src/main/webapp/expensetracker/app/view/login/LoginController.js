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
	onPasswordReType : function(textfield, newValue, oldValue) {
		var me = this;
		var pwdTxtField = me.lookup('regPassword');
		var password = pwdTxtField.getValue();
		if (newValue !== password) {

		}
	},
	onUserNameChage : function(textfield, newvalue, oldvalue) {
		var me = this;
		var usrNmeAvailInd = me.lookup('usrNmeAvailInd');
		textfield.isChanged = true;
		if (!newvalue) {
			usrNmeAvailInd.update('');
		}
	},
	onActivate : function(activateBtn) {
		var me = this;
		var username = me.lookup('activationuser').getValue();
		var activationKey = me.lookup('activationkey').getValue();
		Ext.Ajax.request({
			url : expensetracker.util.Url.getActivateUser(),
			method : 'POST',
			jsonData : Ext.JSON.encode({
				username : username,
				activationKey : activationKey
			}),
			success : function(response, opts) {
				var errorLbl = me.lookup('activateerrorlbl');				
				var response = Ext.decode(response.responseText);
				if(response.success) {
					expensetracker.util.Message.toast('Activation Successful');
				}else{					
					errorLbl.update('<p>* Exception occurred, please contact customer support mail to <a href="mailto:raevanblog@gmail.com?Subject=Activation Failed for ' + username + '" target="_top">raevanblog@gmail.com</a> </p>');
				}
			},
			failure : function(response, opts) {
				var errorLbl = me.lookup('activateerrorlbl');
				errorLbl.update('<p>* Exception occurred, please contact customer support mail to <a href="mailto:raevanblog@gmail.com?Subject=Activation Failed for ' + username + '" target="_top">raevanblog@gmail.com</a> </p>');
			}
		});
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
	}
});