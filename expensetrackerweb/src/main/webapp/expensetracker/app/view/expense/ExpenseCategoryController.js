Ext.define('expensetracker.view.expense.ExpenseCategoryController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensecategorycontroller',	
	onRenderCategoryGrid : function(categorygrid) {
		var me = this;
		me.keyMap = Ext.create('Ext.util.KeyMap', categorygrid.el, [{
			key : 'r',
			fn : function() {
				expensetracker.util.Grid.refresh(categorygrid);
			},
			ctrl : true,
			alt : true,
			scope : me
		}]);
	},
	filterGrid : function(gridsearchtext, newValue, oldValue, options) {
		var me = this;
		var grid = me.getView();
		var store = grid.getStore();
		if (gridsearchtext.reference === 'categorygridsearch') {
			store.filter('category', newValue);
		}
	},
	onBackCategory : function(backCategBtn) {
		var me = this;
		var categorygrid = me.getView();		
		var expensewindow = categorygrid.up('window');
		expensewindow.getLayout().prev();
	}
});