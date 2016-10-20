Ext.define('expensetracker.view.income.IncomeWindowController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.incomewindowcontroller',
	onCloseExpenseWindow : function(window) {
	
	},
	onRenderIncomeGrid : function(incomegrid, options) {
		var me = this;		
		var store = incomegrid.getStore();
		store.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				month : expensetracker.util.Calendar.getCurrentMonthNo(),				
				year : expensetracker.util.Calendar.getCurrentYear()
			},
			callback: function(records, operation, success) {    
				if(success) {
					if(records.length === 0) {
						var typeStore = Ext.getStore('IncomeType');
						
					}
				}
			}
		});
		
		
	},
    onReload : function() {
		
	}
});