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
		text : 'Year',
		xtype : 'widgetcolumn',
		flex : 1,
		widget : {
			xtype: 'slider',
			bind : {
				minValue: '{record.start_year}',
				maxValue: '{record.end_year}',
			},
			increment : 1			
		}
	}, {
		xtype : 'actioncolumn',
		align : 'center',
		tooltip : 'View Price Graph',
		handler : 'onViewPriceGraph',
		iconCls : 'x-fa fa-line-chart',
		flex : 1
	}]
});