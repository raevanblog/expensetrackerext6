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
					if(user.profilePic === null) {
						if ('M' === user.sex) {
							refs.profileimage.setSrc('resources/images/male-profile.png');
						} else if ('F' === user.sex) {
							refs.profileimage.setSrc('resources/images/female-profile.png');
						}
					}else{
						refs.profileimage.setSrc(user.profilePic);
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
	onImageSelection : function(field) {
		var me = this;
		var profileimage = me.lookup('profileimage');
		var value = field.getEl().down('input[type=file]').dom.files[0];
		var reader = new FileReader();
		reader.addEventListener("load", function() {
			profileimage.setSrc(reader.result);
		}, false);

		reader.readAsDataURL(value);
	},
	onSaveProfile : function(saveBtn) {
		var me = this;
		var model = me.getView().getViewModel();
		var profileForm = me.lookup('profileform');
		var profileimage = me.lookup('profileimage');
		
		if (profileForm.isValid()) {
			me.getView().setLoading("Saving...");
			profileForm.submit({
				method : 'PUT',
				params : {
					username : expensetracker.util.Session.getUsername(),
					profilePic : profileimage.getSrc()
					
				},
				success : function(form, action) {
					me.getView().setLoading(false);
					var resObj = Ext.decode(action.response.responseText);
					var response = resObj.result;
					if (response.noOfRecords > 0) {
						expensetracker.util.Message.toast('Profile Updated');						
						expensetracker.util.Session.reload(me);
					}
				},
				failure : function(form, action) {
					me.getView().setLoading(false);
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