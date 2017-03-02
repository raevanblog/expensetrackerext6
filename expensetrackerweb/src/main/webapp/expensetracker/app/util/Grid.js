Ext.define('expensetracker.util.Grid', {
	singleton : true,
	refresh : function(grid) {
		grid.getView().refresh();
	},
	reload : function(grid) {
		grid.getStore().reload();
	}
});