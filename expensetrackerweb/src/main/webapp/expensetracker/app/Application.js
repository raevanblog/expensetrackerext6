/**
 * The main application class. An instance of this class is created by app.js
 * when it calls Ext.application(). This is the ideal place to handle
 * application launch and initialization details.
 */
Ext.define('expensetracker.Application', {
	extend : 'Ext.app.Application',

	name : 'expensetracker',

	stores : [ 'Thumbnail', 'ExpenseType', 'ExpenseCategory', 'ExpenseName', 'Expense', 'IncomeType' ],

	requires : [ 'expensetracker.util.Url', 'expensetracker.util.Constants', 'expensetracker.util.Session', 'expensetracker.util.Grid', 'expensetracker.util.Store', 'expensetracker.view.login.Login',
			'expensetracker.view.login.Activation', 'expensetracker.view.message.ContactUs', 'expensetracker.util.Message', 'expensetracker.view.main.Main', 'Ext.data.validator.Presence',
			'Ext.form.FieldSet', 'Ext.form.FieldContainer', 'Ext.form.SliderField', 'Ext.form.field.ComboBox', 'Ext.form.Panel', 'Ext.form.field.Text', 'Ext.layout.container.HBox',
			'Ext.layout.container.VBox', 'Ext.list.Tree', 'Ext.toolbar.Toolbar', 'Ext.form.Label', 'Ext.form.field.Display', 'Ext.plugin.Viewport', 'Ext.form.field.TextArea',
			'Ext.form.FieldContainer', 'expensetracker.util.Calendar', 'Ext.chart.CartesianChart', 'Ext.chart.axis.Numeric', 'Ext.chart.axis.Category', 'Ext.chart.series.Bar',
			'Ext.chart.interactions.ItemHighlight', 'Ext.chart.theme.DefaultGradients', 'Ext.chart.PolarChart', 'Ext.chart.series.Pie', 'Ext.chart.interactions.Rotate', 'Ext.chart.series.Line',
			'Ext.chart.interactions.PanZoom', 'Ext.layout.container.Border', 'Ext.form.field.File', 'Ext.form.field.Number', 'Ext.form.field.HtmlEditor', 'Ext.util.Cookies', 'Ext.window.Window', 'Ext.window.Toast',
			'Ext.menu.Menu', 'Ext.window.MessageBox','Ext.selection.CheckboxModel' ],
	defaultToken : 'login',
	init : function() {
		Ext.getBody().mask('Loading Expense Tracker....');
	},
	launch : function() {
		var me = this;
		var navigateToActivate = false;
		var key = '';
		var username = '';
		var link = document.location.href.split('#');

		if (link.length > 1) {
			var params = link[1].split('?');
			if (params.length > 1) {
				if (params[0] === 'activate') {
					username = Ext.Object.fromQueryString(params[1]).username;
					key = Ext.Object.fromQueryString(params[1]).key;
					if (key !== undefined && username !== undefined) {
						navigateToActivate = true;
					}
				}
			}
		}
		if (!navigateToActivate) {
			expensetracker.util.Calendar.init();
			Ext.Ajax.request({
				url : expensetracker.util.Url.getSession(),

				success : function(response, opts) {
					Ext.getBody().unmask();
					var response = Ext.decode(response.responseText);
					if (response.success) {
						expensetracker.util.Session.setUser(response.user);						
						expensetracker.util.Store.loadStaticStore();
						expensetracker.util.Store.loadStore(Ext.getStore('ExpenseCategory'), {
							username : expensetracker.util.Session.getUsername()
						});
						Ext.widget('app-main');
					} else {
						window.location = '#login';
						Ext.create({
							xtype : 'login'
						});
					}

				},
				failure : function(response, opts) {
					Ext.getBody().unmask();
					var response = Ext.decode(response.responseText);
					expensetracker.util.Message.toast(response.message + expensetracker.util.Message.getMailTo());
					window.location = '#login';
					Ext.create({
						xtype : 'login'
					});
				}
			});
		} else {
			Ext.getBody().unmask();
			Ext.widget('activation', {
				viewModel : {
					data : {
						username : username,
						key : key
					}
				}
			});
		}
	},

	onAppUpdate : function() {
		Ext.Msg.confirm('Application Update', 'This application has an update, reload?', function(choice) {
			if (choice === 'yes') {
				window.location.reload();
			}
		});
	}
});
