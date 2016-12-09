/**
 * The main application class. An instance of this class is created by app.js
 * when it calls Ext.application(). This is the ideal place to handle
 * application launch and initialization details.
 */
Ext.define('expensetracker.Application', {
	extend : 'Ext.app.Application',

	name : 'expensetracker',

	stores : [ 'ExpenseDock', 'ExpenseType', 'ExpenseName', 'Expense', 'IncomeType' ],

	requires : [ 'expensetracker.util.Url', 'expensetracker.util.Constants', 'expensetracker.util.Session', 'expensetracker.view.login.Login', 'expensetracker.util.Message',
			'expensetracker.view.main.Main', 'Ext.data.validator.Presence', 'Ext.form.FieldSet', 'Ext.form.FieldContainer', 'Ext.form.SliderField', 'Ext.form.field.ComboBox', 'Ext.form.Panel',
			'Ext.form.field.Text', 'Ext.layout.container.HBox', 'Ext.layout.container.VBox', 'Ext.list.Tree', 'Ext.toolbar.Toolbar', 'Ext.form.Label', 'Ext.form.field.Display', 'Ext.plugin.Viewport',
			'Ext.form.field.TextArea', 'Ext.form.FieldContainer', 'expensetracker.util.Calendar', 'Ext.chart.CartesianChart', 'Ext.chart.axis.Numeric', 'Ext.chart.axis.Category',
			'Ext.chart.series.Bar', 'Ext.chart.interactions.ItemHighlight', 'Ext.chart.theme.DefaultGradients', 'Ext.chart.PolarChart', 'Ext.chart.series.Pie', 'Ext.chart.interactions.Rotate',
			'Ext.chart.series.Line', 'Ext.chart.interactions.PanZoom', 'Ext.layout.container.Border', 'Ext.form.field.File' ],
	defaultToken : 'login',
	init : function() {
		Ext.getBody().mask('Loading Expense Tracker....');
	},
	launch : function() {
		var me = this;
		var navigateToActivate = false;
		var vcode = '';
		var username = '';
		var link = document.location.href.split('#');

		if (link.length > 1) {
			var params = link[1].split('?');
			if (params.length > 1) {
				if (params[0] === 'activate') {
					username = Ext.Object.fromQueryString(params[1]).username;
					vcode = Ext.Object.fromQueryString(params[1]).vcode;
					if (vcode !== undefined && username !== undefined) {
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
						expensetracker.util.Session.setCurrency({
							name : 'IND',
							symbol : '₹'
						});
						me.loadApplicationStore();
						Ext.widget('app-main');
					} else {
						Ext.widget('login', {
							viewModel : {
								data : {
									activeItem : 0
								}
							}
						});
					}

				},
				failure : function(response, opts) {
					expensetracker.util.Message.toast('Server Error. Please contact customer support...');
					Ext.widget('login', {
						viewModel : {
							data : {
								activeItem : 0
							}
						}
					});
				}
			});
		} else {
			Ext.getBody().unmask();			
			Ext.widget('login', {
				viewModel : {
					data : {
						activeItem : 2,
						username : username,
						activationKey : vcode
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
	},

	loadApplicationStore : function() {
		Ext.getStore('ExpenseDock').load();
		Ext.getStore('ExpenseType').load();
		Ext.getStore('ExpenseName').load();
		Ext.getStore('IncomeType').load();
	}
});
