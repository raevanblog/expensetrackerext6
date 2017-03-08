Ext.define('expensetracker.view.report.ReportView', {
	extend : 'Ext.container.Container',
	xtype : 'reportview',
	alias : 'view.reportview',
	requires : [ 'expensetracker.view.report.ReportDock', 'expensetracker.view.report.ReportWindow', 'expensetracker.view.report.ReportViewController', 'expensetracker.view.report.ReportViewModel'],
	controller : 'reportviewcontroller',
	viewModel : 'reportviewmodel',
	layout : 'responsivecolumn',
	items : [ {
		xtype : 'reportdock',
		cls : 'big-100 small-100 dash-panel shadow',
		reference : 'reportdock',
		listeners : {
			afterrender : 'onRenderReportDock'
		}
	} ]
});