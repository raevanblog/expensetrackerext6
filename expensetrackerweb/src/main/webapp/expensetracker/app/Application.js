/**
 * The main application class. An instance of this class is created by app.js
 * when it calls Ext.application(). This is the ideal place to handle
 * application launch and initialization details.
 */
Ext.define('expensetracker.Application', {
	extend : 'Ext.app.Application',

	name : 'expensetracker',

	stores : [ 'ExpenseDock', 'ExpenseCategory', 'ExpenseType', 'ExpenseName', 'Expense' ],

	requires : [ 'expensetracker.util.Url', 'expensetracker.util.Session', 'expensetracker.view.login.Login', 'expensetracker.view.main.Main',
			'Ext.data.validator.Presence', 'Ext.form.FieldSet', 'Ext.form.FieldContainer', 'Ext.form.SliderField', 'Ext.form.field.ComboBox',
			'Ext.form.Panel', 'Ext.form.field.Text', 'Ext.layout.container.HBox', 'Ext.layout.container.VBox', 'Ext.list.Tree',
			'Ext.toolbar.Toolbar', 'Ext.form.Label', 'Ext.form.field.Display', 'Ext.plugin.Viewport', 'Ext.form.field.TextArea',
			'Ext.form.FieldContainer', 'expensetracker.util.Calendar','Ext.chart.CartesianChart', 'Ext.chart.axis.Numeric', 'Ext.chart.axis.Category', 'Ext.chart.series.Bar', 'Ext.chart.interactions.ItemHighlight', 'Ext.chart.theme.DefaultGradients', 'Ext.chart.PolarChart', 'Ext.chart.series.Pie', 'Ext.chart.interactions.Rotate' ],
	defaultToken : 'login',
	launch : function() {
		var me = this;
		expensetracker.util.Calendar.init();
		Ext.Ajax.request({
			url : expensetracker.util.Url.getSession(),

			success : function(response, opts) {
				var response = Ext.decode(response.responseText);
				if (response.success) {
					expensetracker.util.Session.setUser(response.user);
					Ext.widget('app-main');
				} else {
					Ext.widget('login');
				}

			},
			failure : function(response, opts) {
				Ext.widget('app-main');
			}
		});
	},

	onAppUpdate : function() {
		Ext.Msg.confirm('Application Update', 'This application has an update, reload?', function(choice) {
			if (choice === 'yes') {
				window.location.reload();
			}
		});
	}
});
