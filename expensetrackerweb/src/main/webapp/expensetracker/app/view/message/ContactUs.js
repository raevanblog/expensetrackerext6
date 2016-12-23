Ext.define('expensetracker.view.message.ContactUs', {
	extend : 'Ext.window.Window',
	layout : 'fit',
	items : [{
		xtype: 'form',
		reference : 'contactusform',
		layout : {
			 type : 'vbox',
			 align : 'stretch'
		},
		bodyPadding : 10,
		height : '80%',
		width : '80%',
		items : [{
			xtype : 'textfield',
			fieldLabel : 'Name',
			labelAlign : 'top',			
			validateBlank : true,
			allowBlank : false,
			reference : 'contactname',
			labelSeparator : ''		
		},{
			xtype : 'textfield',
			fieldLabel : 'E-Mail',
			labelAlign : 'top',
			vtype : 'email',
			validateBlank : true,
			allowBlank : false,
			reference : 'contactemail',
			labelSeparator : ''			
		},{
			xtype : 'textfield',
			fieldLabel : 'Subject',
			labelAlign : 'top',
			validateBlank : true,
			allowBlank : false,			
			reference : 'contactsubject',
			labelSeparator : ''	
		},{
			xtype : 'htmleditor',
			fieldLabel : 'Message',
			enableSourceEdit : false,
			labelAlign : 'top',
			validateBlank : true,
			allowBlank : false,			
			reference : 'contactmessage',
			labelSeparator : ''
		}],
		buttons : [{
			text : 'Send',
			formBind : true,
			iconCls : 'x-fa fa-envelope',
			handler : 'onSendMessageToAdmin'
		}]
	}]	
});