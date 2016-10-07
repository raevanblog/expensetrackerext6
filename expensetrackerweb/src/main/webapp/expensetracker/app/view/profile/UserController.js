Ext.define('expensetracker.view.profile.UserController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.user',
	onProfileRender : function(view) {
		var me = this;
		var refs = me.getReferences();
		var user = expensetracker.util.Session.getUser();
		refs.userName.setValue(user.username);
		refs.firstName.setValue(user.firstName);
		refs.lastName.setValue(user.lastName);
		refs.sex.setValue(user.sex);
		refs.email.setValue(user.email);
		refs.mobile.setValue(user.mobile);		
	}
});