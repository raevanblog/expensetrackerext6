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
			}
		});
	},
	onSaveProfile : function(saveBtn) {
		var me = this;
		var profileForm = me.lookup('profileform');
		var refs = me.getReferences();

		if (profileForm.isValid()) {
			profileForm.setLoading("Saving...");
			profileForm.submit({
				method : 'PUT',
				params : {
					username : refs.userName.getValue()
				},
				success : function(form, action) {
					profileForm.setLoading(false);
					var response = Ext.decode(action.response.responseText);
					if (response.success) {
						expensetracker.util.Session.update();
					}
				},
				failure : function(form, action) {
					profileForm.setLoading(false);
				}
			});
		}

	}
});