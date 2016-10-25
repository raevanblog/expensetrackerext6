Ext.define('expensetracker.util.Message', {
	singleton : true,
	toast : function(message) {
		Ext.toast({
			html : message,
			iconCls : 'x-fa fa-info-circle',
			title : 'Expense Tracker',
			width : 200,
			align : 't'
		});
	}
});