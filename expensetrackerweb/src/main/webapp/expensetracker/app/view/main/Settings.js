Ext.define('expensetracker.view.main.Settings', {
	extend : 'Ext.window.Window',
	alias : 'view.settings',
	xtype : 'settings',
	closeToolText : 'Close',
	title : 'Settings',
	requires : ['expensetracker.store.Currency', 'expensetracker.view.main.SettingsController', 'expensetracker.view.main.SettingsModel'],
	controller : 'settings',
	viewModel : 'settings',
	listeners : {
		afterrender : 'onRender'
	},
	platformConfig : {		
		desktop : {
			height : Ext.Element.getViewportHeight() * (80/100),
			width : Ext.Element.getViewportWidth() * (50/100)
		},

		'!desktop' : {
			height : Ext.Element.getViewportHeight(),
			width : Ext.Element.getViewportWidth()
		}
	},
	layout : {
		type : 'fit'
	},
	items : [{
		xtype : 'form',
		bodyPadding : 10,
		padding : '10 10 10 10',
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [{
			xtype : 'combobox',
			fieldLabel : 'Currency',
			store : 'Currency',			
			displayField : 'display',
			valueField : 'id',
			labelSeparator : '',
			height : 75,
			queryMode : 'local',
			typeAhead : true,
			forceSelection : true,
			labelAlign : 'top',
			reference : 'currency'
		}, {
			xtype : 'textfield',
			fieldLabel : 'Alternate Email',
			labelSeparator : '',
			height : 75,
			labelAlign : 'top',
			vtype : 'email',
			reference : 'alternateemail'
		}, {
			xtype : 'button',
			bind : {
				text : '{buttonText}'
			},
			scale : 'large',
			margin : '10 0 0 0',
			ui : 'soft-green',
			iconCls : 'x-fa fa-save',
			reference : 'savesettings',
			handler : 'onSaveSettings',
			formBind : true
		}]
	}]
});