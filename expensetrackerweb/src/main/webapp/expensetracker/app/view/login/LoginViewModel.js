Ext.define('expensetracker.view.login.LoginViewModel', {
	extend : 'Ext.app.ViewModel',
	alias : 'viewmodel.loginviewmodel',
	data : [ {
		username : null,
		password : null,
		rememberMe : false
	} ]
})