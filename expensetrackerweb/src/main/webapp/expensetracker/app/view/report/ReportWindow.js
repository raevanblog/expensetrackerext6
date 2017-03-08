Ext.define('expensetracker.view.report.ReportWindow', {
	extend : 'Ext.window.Window',
	xtype : 'reportwindow',
	alias : 'view.reportwindow',
	title : 'Expense Report',	
	iconCls : 'x-fa fa-file-pdf-o',	
	constructor :function(config) {
		var me = this;			
		config.items = [{
		xtype : 'component',
			autoEl: {
				tag: 'iframe',
				style: 'height: 100%; width: 100%; border: none',
				src: config.reportUrl
			}
		}];
		me.callParent(arguments);
	}	
});