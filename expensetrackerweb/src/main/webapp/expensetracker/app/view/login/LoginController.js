Ext.define('expensetracker.view.login.LoginController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.login',
	onRender : function() {
		var me = this;
		var model = me.getView().getViewModel();
		var cookie = expensetracker.util.Session.getCookie();

		if (cookie !== null) {
			model.set('username', cookie.username);
			model.set('password', Ext.util.Base64.decode(cookie.password));
			model.set('rememberMe', true);
		}
	},
	onLogin : function(button) {
		var me = this;
		var view = me.getView();
		var model = view.getViewModel();
		var loginform = me.lookup('loginform');

		if (loginform.isValid()) {
			view.setLoading('Logging in...');
			loginform.submit({
				params : {
					credential : Ext.util.Base64.encode(model.get('username') + ':' + model.get('password'))
				},
				success : function(form, action) {
					view.setLoading(false);
					if (model.get('rememberMe')) {
						var cookie = {
							username : model.get('username'),
							password : Ext.util.Base64.encode(model.get('password'))
						};
						expensetracker.util.Session.setCookie(cookie);
					}
					var response = Ext.decode(action.response.responseText);
					if (response.result.any !== null && response.result.any.length === 1) {
						expensetracker.util.Session.setUser(response.result.any[0]);
						expensetracker.util.Store.loadStaticStore();
						expensetracker.util.Store.loadStore(Ext.getStore('ExpenseCategory'), {
							username : expensetracker.util.Session.getUsername()
						});
						expensetracker.util.Store.loadStore(Ext.getStore('Year'), {
							username : expensetracker.util.Session.getUsername()
						});
					}
					me.getView().destroy();
					Ext.widget('app-main');
				},
				failure : function(form, action) {
					view.setLoading(false);
					var response = Ext.decode(action.response.responseText);
					me.updateErrorLbl(response.message);
				}
			})
		}
	},
	updateErrorLbl : function(error) {
		var me = this;
		var errorlabel = me.lookup('errorlbl');
		var errors = [];
		errorlabel.setHidden(false);
		errors.push({
			name : 'Error',
			error : error
		});

		errorlabel.setErrors(errors);
	},
	onEnter : function(textfield, e) {
		var me = this;
		var loginBtn = me.lookup('loginBtn');
		if (Ext.event.Event.ENTER === e.keyCode) {
			me.onLogin(loginBtn);
		}
	},
	onOpenRegistration : function(registrationBtn) {
		var me = this;
		var card = me.lookup('formcard');
		card.getLayout().setActiveItem(1);
	},	
	onOpenSignIn : function(signInBtn) {
		var me = this;
		var card = me.lookup('formcard');
		var activeItem = card.getLayout().getActiveItem().xtype;
		if('registercontainer' === activeItem) {			
			me.lookup('registerform').reset();		
			card.getLayout().setActiveItem(0);	
		}
	},
	onActivateUser : function(actUserBtn) {
		var me = this;
		var window = Ext.create('expensetracker.view.login.ActivationWindow', {
			modal : true,
			reference : 'activationwindow'
		});
		window.show();
	},
	onForgorPwd : function() {
		var me = this;
		var window = Ext.create('expensetracker.view.login.ForgotPassword', {
			modal : true,
			reference : 'forgotpwdwindow'
		});
		window.show();
	},
	onChange : function(textfield, newvalue, oldvalue) {
		var me = this;
		textfield.isChanged = true;
		if (!newvalue) {
			textfield.clearInvalid();
		}
	},
	onFocusOut : function(textfield, event) {
		var me = this;
		var registercontainer = me.lookup('registercontainer');
		var value = textfield.getValue();
		if (textfield.isValid()) {
			if (textfield.isChanged) {
				textfield.isChanged = false;
				if (value) {
					registercontainer.setLoading('Checking Availability...');
					Ext.Ajax.request({
						url : expensetracker.util.Url.getCheckAvailability(),
						method : 'GET',
						params : {
							type : textfield.name,
							value : value
						},
						success : function(response, opts) {
							registercontainer.setLoading(false);
							var response = Ext.decode(response.responseText);
							var isAvailable = response.result.any[0];
							textfield.isAvailable = isAvailable;
							if (isAvailable) {
								textfield.clearInvalid();
							} else {
								textfield.markInvalid(response.message);
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
	updateErrorState : function(cmp, state) {
		var me = this, errorCmp = me.lookupReference('errorInd'), view, form, fields, errors;

		view = me.lookup('registerform');
		form = view.getForm();

		if (state === false || (typeof state === 'string')) {
			fields = form.getFields();
			errors = [];

			fields.each(function(field) {
				Ext.Array.forEach(field.getErrors(), function(error) {
					errors.push({
						name : field.emptyText,
						error : error
					});
				});
			});

			errorCmp.setErrors(errors);
			me.hasBeenDirty = true;
		} else if (state === true) {
			errorCmp.setErrors();
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
				if (response.success) {
					expensetracker.util.Message.toast(response.message);
				} else {
					var errlbl = me.lookup('activationwindowerrlbl');
					errlbl.update('<p>* ' + response.message + ' Mail To :' + expensetracker.util.Message.getMailTo(username) + '</p>');
				}
			},
			failure : function(response, opts) {
				activationwindow.setLoading(false);
				var errlbl = me.lookup('activationwindowerrlbl');
				errorLbl.update('<p>* ' + response.message + ' Mail To :' + expensetracker.util.Message.getMailTo() + '</p>');
			}
		});
	},
	onContactUs : function(contactUsBtn) {
		var window = Ext.create('expensetracker.view.message.ContactUs', {
			modal : true,
			title : 'Contact Us',
			reference : 'contactus'
		});
		window.show();
	},
	onSendMessageToAdmin : function(sendBtn) {
		var me = this;
		var contactus = me.lookup('contactus');
		var form = me.lookup('contactusform');
		var fromName = me.lookup('contactname');
		var fromEmail = me.lookup('contactemail');
		var subject = me.lookup('contactsubject');
		var message = me.lookup('contactmessage');

		contactus.setLoading('Sending...');

		Ext.Ajax.request({
			url : expensetracker.util.Url.getQuery(),
			method : 'POST',
			jsonData : Ext.JSON.encode({
				msgfrom : fromName.getValue(),
				email : fromEmail.getValue(),
				subject : subject.getValue(),
				message : message.getValue(),
				isNew : 'Y'
			}),
			success : function(response, opts) {
				contactus.setLoading(false);
				form.reset();
				var response = Ext.decode(response.responseText);
				if (response.success) {
					expensetracker.util.Message.toast(response.message);
				} else {
					expensetracker.util.Message.toast(response.message);
				}
				contactus.close();
			},
			failure : function(response, opts) {
				contactus.setLoading(false);
				expensetracker.util.Message.toast(response.message);
				contactus.close();
			}
		});

	}
});