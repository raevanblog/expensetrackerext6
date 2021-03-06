Ext.define('expensetracker.view.profile.PasswordController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.password',
	onSubmitPwd : function(submitBtn) {
		var me = this;
		var view = me.getView();
		var chgPwdForm = me.lookup('changepwdform');
		var errorLbl = me.lookup('chgPwdErrorLbl');		
		if (chgPwdForm.isValid()) {
			view.setLoading('Updating...');
			chgPwdForm.submit({
				params : {
					username : expensetracker.util.Session.getUsername()
				},
				success : function(form, action) {
					view.setLoading(false);
					var resObj = Ext.decode(action.response.responseText);
					var response = resObj.result;
					if (response.noOfRecords > 0) {
						chgPwdForm.reset();
						expensetracker.util.Message.toast('Password Updated');
					}
				},
				failure : function(form, action) {
					view.setLoading(false);
					var resObj = Ext.decode(action.response.responseText);
					if (401 === resObj.statusCode) {
						me.fireEvent('navigatelogin');
						if (view !== null) {
							view.close();
						}
						expensetracker.util.Message.toast(resObj.message);
					} else if (400 === resObj.statusCode) {						
						me.updateErrorLbl(resObj.exception);
					} else {
						expensetracker.util.Message.toast('* ' + resObj.message);
					}
				}
			});
		}
	},
	updateErrorLbl : function(error) {
		var me = this;
		var errorlabel = me.lookup('chgPwdErrorLbl');
		var errors = [];
			
		errors.push({name : 'Error', error: error});

		errorlabel.setErrors(errors);
	}
});