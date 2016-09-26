Ext.define('expensetracker.view.expense.ExpenseViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expenseviewcontroller',
	onThumbnailClick : function(thumbnailcont, record, item, index, e) {
		var window = Ext.create('Ext.window.Window', {
			layout : 'fit'
		});
		var store = Ext.getStore('Expense');
		store.getProxy().setExtraParam('username', "shyamcse07");
		store.load();

		var expenseGrid = Ext.create('expensetracker.view.expense.ExpenseGrid', {
			height : this.getView().getHeight()-100,
			width : this.getView().getWidth()-100,
			store : store			
		});
		
		window.add(expenseGrid);
		window.show();
	},
	onYearSelection : function(slider, newValue, thumb, eOpts) {
		var me = this;
		var refs = me.getReferences();
		refs.expviewselyear.update(newValue);
	},
	onRenderSlider : function(slider) {
		var me = this;
		var value = slider.getValue();
		var refs = me.getReferences();
		refs.expviewselyear.update(value);
	}	
});