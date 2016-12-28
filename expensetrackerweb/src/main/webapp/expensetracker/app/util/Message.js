Ext.define('expensetracker.util.Message', {
	singleton : true,
	toast : function(message) {
		Ext.toast({
			html : message,
			iconCls : 'x-fa fa-info-circle',
			title : 'Expense Tracker',
			width : 350,
			align : 't'
		});
	},
	getMailTo : function(subject) {

		if (subject === undefined || subject === null) {
			subject = 'Server Error';
		}

		return '<a href="mailto:raevanblog@gmail.com?Subject=' + subject + '" target="_top">raevanblog@gmail.com</a>';
	}
});