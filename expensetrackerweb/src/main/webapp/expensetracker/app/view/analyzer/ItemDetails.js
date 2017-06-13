Ext.define('expensetracker.view.analyzer.ItemDetails', {
	extend : 'Ext.grid.Panel',
	alias : 'view.itemdetails',
	xtype : 'itemdetails',	
	scrollable : true,
	layout : 'fit',
	title : 'Item Detail',	
	listeners : {
		render : 'onRenderItemDetails'
	},
	columns : [ {
		xtype : 'rownumberer',
		text : 'S.No',
		width : 75
	}, {
		text : 'Item Name',
		dataIndex : 'name',
		align : 'left',
		flex : 1
	}, {
		xtype : 'actioncolumn',
		align : 'center',
		tooltip : 'View Price Graph',
		handler : 'onViewPriceGraph',
		iconCls : 'x-fa fa-line-chart',
		flex : 1
	}]
});