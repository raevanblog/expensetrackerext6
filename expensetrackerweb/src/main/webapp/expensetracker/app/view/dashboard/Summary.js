Ext.define('expensetracker.view.dashboard.Summary', {
	extend : 'Ext.container.Container',
	xtype : 'summary',
	alias : 'view.summary',
	layout : 'fit',
	items : [{
		xtype : 'component',
		itemId : 'summary-component',
		cls: 'summary-tiles',
		tpl:[
            '<div class="summary">',
                '<tpl for=".">',
                    '<span>',
                        '<div>{value:currency([expensetracker.util.Session.getCurrencySymbol()], 2, false, " ")}</div> {category}',
                    '</span>',
                '</tpl>',
            '</div>'
        ]
	}]
});