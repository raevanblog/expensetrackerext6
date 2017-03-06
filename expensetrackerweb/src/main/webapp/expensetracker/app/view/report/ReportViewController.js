Ext.define('expensetracker.view.report.ReportViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.reportviewcontroller',	
	onRenderReportDock : function(reportdock) {
		var me = this;
		reportdock.setTitle(expensetracker.util.Calendar.getCurrentYear());
	},
	onThumbnailClick : function() {
		Ext.Msg.alert('Expense Tracker', 'Not Enabled');
	},
	filterDock : function(textfield, newValue, oldValue, options) {
		var me = this;
		var docker = me.lookup('reportthumbdocker');
		var store = docker.getStore();
		store.filter('month', newValue);
	},
	onYearSelection : function(numberfield, newValue, oldValue, options) {
		var me = this;
		var reportdock = me.lookup('reportdock');
		reportdock.setTitle('' + newValue);
	}
});