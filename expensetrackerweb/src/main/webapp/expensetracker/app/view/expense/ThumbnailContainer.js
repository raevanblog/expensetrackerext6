Ext.define('expensetracker.view.expense.ThumbnailContainer', {
	extend : 'Ext.view.View',
	cls : 'thumbnails',
	xtype : 'thumnailcontainer',
	itemSelector : '.thumbnail-item',
	initComponent : function() {
		this.tpl = '<tpl for=".">' + '<div class="thumbnail-item">' + '<div class="thumbnail-icon-wrap">' + '<div class="thumbnail-icon icon-calendar"></div>' + '</div>'
				+ '<div class="thumbnail-text">{month}</div>' + '</div>' + '</tpl>';
		this.callParent();
	}
});