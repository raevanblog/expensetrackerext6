Ext.define('expensetracker.view.message.Details', {
	extend : 'Ext.panel.Panel',
	xtype : 'messagedetails',
	alias : 'view.messagedetails',
	cls : 'shadow',
	style : 'border-width:1px;',
	bodyPadding : 20,
	listeners : {
		beforerender : 'onRenderDetails'
	},
	layout : {
		type : 'anchor',
		anchor : '100%'
	},
	tbar : [ {
		text : 'Back',
		ui : 'toolbar',
		iconCls : 'x-fa fa-backward',
		handler : 'onDetailsBack'
	}],
	items : [ {
		xtype : 'container',
		height : 85,
		layout : {
			type : 'hbox',
			align : 'stretch'
		},
		items : [ {
			xtype : 'image',
			height : 80,
			itemId : 'senderpic',
			width : 80			
		}, {
			xtype : 'component',			
			flex : 1,
			style : 'border-bottom: 1px solid #EEEEEE',
			cls : 'email-subject',
			data : {},
			itemId : 'mailsubject',
			padding : 10,
			tpl : [ '<div class="user-name">From : {senderfname} {senderlname}</div>', '<div class="mail-subject">{subject}</div>', '<div>{msgdate:date("d/m/Y H:i:s A")}</div>' ]
		} ]
	}, {
		xtype : 'box',
		padding : '10 30 10 30',
		cls : 'mail-body',
		itemId : 'mailbody',
		scrollable : 'y'
	} ]
});