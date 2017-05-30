Ext.define('expensetracker.view.report.ReportViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.reportviewcontroller',	
	onRenderReportDock : function(reportdock) {
		var me = this;
		reportdock.setTitle(expensetracker.util.Calendar.getCurrentYear());
	},
	onThumbnailClick : function(thumbnailcont, record, item, index, e) {
		var me = this;
		var selectedYear = me.lookup('reportyear');
		var currentYear = expensetracker.util.Calendar.getCurrentYear();
		var currentMonth = expensetracker.util.Calendar.getCurrentMonthNo();
		var selectedMonth = record.get('monthNo');
		
		if (selectedYear.getValue() === currentYear && selectedMonth > currentMonth) {
			Ext.Msg.show({
				title : 'Expense Tracker',
				message : 'Report cannot be generated for the future date',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.INFO
			});
		} else {			
			var username = expensetracker.util.Session.getUsername();			
			var pdfWindow = Ext.create('expensetracker.view.report.ReportWindow',{
				height : 500,
				width : 700,
				modal : true,
				reportUrl : expensetracker.util.Url.getReportingService() + '?username='+ username + '&year=' + selectedYear.getValue() + '&month=' + selectedMonth
			});
			pdfWindow.show();	
		}
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