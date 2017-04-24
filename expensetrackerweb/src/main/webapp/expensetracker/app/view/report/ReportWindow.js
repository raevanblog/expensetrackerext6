Ext.define('expensetracker.view.report.ReportWindow', {
	extend : 'Ext.window.Window',
	xtype : 'reportwindow',
	alias : 'view.reportwindow',
	title : 'Expense Report',
	closeToolText : 'Close',
	iconCls : 'x-fa fa-file-pdf-o',	
	constructor :function(config) {
		var me = this;			
		config.items = [{
		xtype : 'component',
			autoEl: {
				tag: 'iframe',
				style: 'height: 100%; width: 100%; border: none',
				src: config.reportUrl
			},
			listeners: {
                load: {
                    element: 'el',
                    fn: function () {
                        var me = this;
						console.log(this.parent());
						this.parent().component.setLoading(false);
                    }
                },
                render: function () {
					var me = this;
					me.up('component').setLoading('Loading...');
                }
            }
		}];
		me.callParent(arguments);
	}	
});