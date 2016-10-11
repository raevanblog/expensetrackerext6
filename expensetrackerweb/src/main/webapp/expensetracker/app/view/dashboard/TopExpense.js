Ext.define('expensetracker.view.dashboard.TopExpense', {
	extend: 'Ext.panel.Panel',
	xtype: 'topexpense',
	alias: 'view.topexpense',
	layout: 'fit',
	items: [{
		xtype: 'cartesian',
		reference: 'topexpensechart',		
		insetPadding: {
            top: 30,
            bottom: 10,
            left: 20,
            right: 20
        },
		axes: [{
			type: 'numeric',
			minimum: 100,
			position: 'left',
			titleMargin: 20,
			title: {
				text: 'Price'
			}						
		},{
			type: 'category',
			position: 'bottom',
			label: {
				rotate: -45
			}
		}],
		series: {
			type: 'bar',
			xField: 'itemName',
			yField: 'price',
			style: {
                minGapWidth: 20
            },
            highlight: {
                strokeStyle: 'black',
                fillStyle: 'gold'
            },
            label: {
                field: 'price',
                display: 'insideEnd',
				renderer: function(value) {
					return value.toFixed(2);
				}				
            },
			tooltip: {
				trackMouse: true,
				renderer: function(tooltip, record) {
					var tip = 'CATEGORY : ' + record.get('category') +'</br>ITEM NAME : ' +record.get('itemName')+ '</br>PRICE : ' +record.get('price');
					tooltip.setHtml(tip);
				}
			}
		}		
	}]
});