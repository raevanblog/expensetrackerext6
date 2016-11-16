Ext.define('expensetracker.util.Validation', {
	override: 'Ext.form.field.VTypes',
	username: function(value) {
		if(value) {
			return this.userNameReg.test(value);
		}
		return true;
    },
	userNameReg : /^[a-zA-Z0-9]+$/,
	usernameText : 'Username should not contain special characters',

	password : function(value) {
		if(value) {
			return this.passwordReg.test(value);
		}
		return true;
	},
	passwordReg : /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\w\s]).{8,}$/,
	passwordText : 'Password should be atleast 8 characters<br>Password should contain one uppercase alphabet<br>Password should contain one lowercase alphabet<br>Password should contain one special character',
	
	match : function(value, field) {
		if(value) {
			var matchfield = Ext.getCmp(field.matchfield);
			if(matchfield.getValue() === value) {
				return true;
			}
			return false;
		}
		return true;
	},
	matchText : 'Value does not match'
});