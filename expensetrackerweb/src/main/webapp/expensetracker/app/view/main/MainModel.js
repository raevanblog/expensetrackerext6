/**
 * This class is the view model for the Main view of the application.
 */
Ext.define('expensetracker.view.main.MainModel', {
	extend : 'Ext.app.ViewModel',

	alias : 'viewmodel.main',

	data : {
		name : 'expensetracker',
		usrname : '',
		profileimg : '',		
		navBarWidth : expensetracker.util.Constants.getNavBarWidth()
	}

});
