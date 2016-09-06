Ext.define('expensetracker.view.expense.ExpenseViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expenseviewcontroller',
	onThumbnailClick : function(thumbnailcont, record, item, index, e) {

	},
	onYearSelection : function(slider, newValue, thumb, eOpts) {
		var me = this;
		var refs = me.getReferences();
		refs.expviewselyear.update(newValue);
	},
	onRenderSlider: function(slider) {
		var me = this;
		var value = slider.getValue();
		var refs = me.getReferences();
		refs.expviewselyear.update(value);
	}
});