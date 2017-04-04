Ext.define('expensetracker.view.expense.ThumbnailContainer', {
	extend : 'Ext.view.View',
	cls : 'thumbnails',
	xtype : 'thumnailcontainer',
	itemSelector : '.thumbnail-item',
	initComponent : function() {
		this.tpl = new Ext.XTemplate('<tpl for=".">' + '<div class="thumbnail-item">' + '<div class="thumbnail-icon-wrap">' + '<div class="thumbnail-icon {[this.getIcon()]}"></div>' + '</div>'
				+ '<div class="thumbnail-text">{title}</div>' + '</div>' + '</tpl>', {
			icontype : this.icontype,
			getIcon : function() {
				var me = this;
				if ('calendar' === me.icontype) {
					return 'icon-calendar';
				} else if ('inventory' === me.icontype) {
					return 'icon-inventory';
				} else if ('pdf' === me.icontype) {
					return 'icon-pdf';
				}
			}
		});
		this.callParent();
	}
});