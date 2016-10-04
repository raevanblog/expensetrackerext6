/**
 * The main application class. An instance of this class is created by app.js
 * when it calls Ext.application(). This is the ideal place to handle
 * application launch and initialization details.
 */
Ext.define('expensetracker.Application', {
	extend : 'Ext.app.Application',

	name : 'expensetracker',

	stores : [ 'ExpenseDock', 'ExpenseCategory', 'ExpenseType', 'ExpenseName', 'Expense' ],

	requires : [ 'expensetracker.util.Url', 'Ext.data.validator.Presence', 'Ext.form.FieldSet', 'Ext.form.FieldContainer', 'Ext.form.SliderField', 'Ext.form.field.ComboBox',
			'Ext.form.Panel', 'Ext.form.field.Text', 'Ext.layout.container.HBox', 'Ext.layout.container.VBox', 'Ext.list.Tree', 'Ext.toolbar.Toolbar' ],

	defaultToken : 'login',

	launch : function() {
		this.redirectTo('login');
	},

	onAppUpdate : function() {
		Ext.Msg.confirm('Application Update', 'This application has an update, reload?', function(choice) {
			if (choice === 'yes') {
				window.location.reload();
			}
		});
	}
});
