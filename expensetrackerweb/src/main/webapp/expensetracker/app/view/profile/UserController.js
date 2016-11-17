Ext.define('expensetracker.view.profile.UserController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.user',
	onProfileRender : function(view) {
		var me = this;
		var view = me.getView();
		var refs = me.getReferences();
		var userinfo = expensetracker.util.Session.getUser();
		view.setLoading("Loading...");
		Ext.Ajax.request({
			url : expensetracker.util.Url.getUserService(),
			params : {
				username : userinfo.username
			},
			method : 'GET',
			success : function(response, opts) {
				view.setLoading(false);
				var response = Ext.decode(response.responseText);
				if (response.success) {
					var user = response.result.any[0];
					refs.userName.setValue(user.username);
					refs.firstName.setValue(user.firstName);
					refs.lastName.setValue(user.lastName);
					refs.sex.setValue(user.sex);
					refs.email.setValue(user.email);
					refs.mobile.setValue(user.mobile);
					refs.address.setValue(user.address);
					if ('M' === user.sex) {
						refs.profileimage.setSrc('resources/images/male-profile.png');
					} else if ('F' === user.sex) {
						refs.profileimage.setSrc('resources/images/female-profile.png');
					}
				}
			},
			failure : function(response, opts) {
				view.setLoading(false);
				var response = Ext.JSON.decode(response.responseText);
				expensetracker.util.Message.toast(response.status_Message);
				if (401 === response.status_Code) {					
					me.getView().close();
					me.fireEvent('navigatelogin');
				}
			}
		});
	},
	onSaveProfile : function(saveBtn) {
		var me = this;
		var model = me.getView().getViewModel();
		var profileForm = me.lookup('profileform');
		var refs = me.getReferences();
		
		if (profileForm.isValid()) {
			profileForm.setLoading("Saving...");
			profileForm.submit({
				method : 'PUT',
				params : {
					username : expensetracker.util.Session.getUsername()
				},
				success : function(form, action) {
					profileForm.setLoading(false);
					var resObj = Ext.decode(action.response.responseText);
					var response = resObj.result;
					if (response.noOfRecords > 0) {
						expensetracker.util.Message.toast('Profile Updated');						
						expensetracker.util.Session.reload(model);
					}
				},
				failure : function(form, action) {
					profileForm.setLoading(false);
					var resObj = Ext.decode(action.response.responseText);
					if(401 === resObj.status_Code) {
						me.getView().close();
						me.fireEvent('navigatelogin');
						expensetracker.util.Message.toast(resObj.status_Message);
					} else {
						expensetracker.util.Message.toast('*' +resObj.status_Message);
					}
				}
			});
		}

	}
});