Ext.define('expensetracker.view.main.MainDashboard', {
	extend : 'Ext.container.Container',
	alias : 'view.maindashboard',
	xtype : 'maindashboard',
	requires : [ 'expensetracker.store.NavigationMenu', 'expensetracker.view.dashboard.ExpenseDashboard', 'expensetracker.view.expense.ExpenseView' ],
	scrollable : 'y',
	layout : {
		type : 'hbox',
		align : 'stretchmax',
		animate : true,
		animatePolicy : {
			x : true,
			width : true
		}
	},
	beforeLayout : function() {
		var me = this;
		var height = Ext.Element.getViewportHeight() - 70;
		var navMenu = me.getComponent('navigationMenu');
		me.minHeight = height;

		navMenu.setStyle({
			'min-height' : height + 'px'
		});
		me.callParent(arguments);
	},
	items : [ {
		xtype : 'treelist',
		itemId : 'navigationMenu',
		reference : 'navigationMenu',
		ui : 'navigation',
		store : {
			type : 'navigationmenu'
		},
		width : 250,
		expanderFirst : false,
		expanderOnly : false,
		listeners : {
			selectionchange : 'onNavigationSelection'
		}
	}, {
		xtype : 'container',
		itemdId : 'mainCard',
		cls : 'sencha-dash-right-main-container',
		flex : 1,
		reference : 'mainCard',
		layout : {
			type : 'card',
			anchor : '100%',
			animation: {
				xtype: 'animation.slide',
				direction: 'left'
			}
		}
	} ]

});