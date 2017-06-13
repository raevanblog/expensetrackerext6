Ext.define('expensetracker.view.analyzer.AnalyzerView',{
	extend : 'Ext.container.Container',
	xtype : 'analyzerview',
	alias : 'view.analyzerview',
	layout : 'responsivecolumn',
	controller : 'analyzerviewcontroller',
	requires :  ['expensetracker.view.analyzer.ItemDetails', 'expensetracker.view.analyzer.AnalyzerViewController'],
	items : [{
		xtype : 'panel',
		title : 'Price Graph',
		titleAlign : 'center',		
		height : 400,
		cls : 'big-20 small-100 dash-panel shadow',
		items : [{
			xtype : 'image',
			src : 'resources/images/graph.png',
			height : '60%',
			cls : 'shadow',
			width : '100%' 
		}, {
			xtype : 'form',
			height : '40%',
			layout : {
				type : 'vbox',
				align : 'stretch'				
			},
			items : [{
				xtype : 'combobox',
				fieldLabel : 'Select Item',
				labelAlign : 'top',
				labelSeparator : '',
				margin : '5	5 5	5',
				valueField : 'name',
				displayField : 'name',
				queryMode : 'local',
				editable : false,
				allowBlank : false,
				listeners : {
					afterrender : 'onRenderItemCombo'
				}				
			},{
				xtype : 'button',
				text : 'View',
				formBind : true,
				scale : 'large',
				margin : '5	5 5	5',
				ui : 'soft-green'
			}],			
		}]
	}, {
		xtype : 'panel',
		title : 'Expense Trend',
		titleAlign : 'center',		
		height : 400,
		cls : 'big-20 small-100 dash-panel shadow',
		items : [{
			xtype : 'image',
			src : 'resources/images/pie.png',
			height : '60%',
			cls : 'shadow',
			width : '100%' 
		}, {
			xtype : 'form',
			height : '40%',
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			items : [{
				xtype : 'combobox',
				fieldLabel : 'Select Year',
				labelSeparator : '',
				labelAlign : 'top',
				store : 'Year',
				valueField : 'year',
				displayField : 'year',
				editable : false,
				allowBlank : false,
				margin : '5 5 5 5',
				queryMode : 'local'				
			},{
				xtype : 'button',
				text : 'View',
				formBind : true,
				margin : '5 5 5 5',
				ui : 'soft-green',
				scale : 'large'
			}]
		}]
	}] 
});