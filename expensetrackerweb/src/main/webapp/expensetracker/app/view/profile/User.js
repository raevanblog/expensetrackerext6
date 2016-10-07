Ext.define('expensetracker.view.profile.User', {
	extend: 'Ext.window.Window',
	alias: 'view.profile',
	requires: ['expensetracker.view.profile.UserController','Ext.form.field.VTypes'],
	xtype: 'profile',
	bodyPadding: 10,
	title: 'User Profile',
	layout: 'fit',
	controller: 'user',
	listeners: {
		afterrender: 'onProfileRender'
	},
	items: [{
		xtype: 'form',		
		jsonSubmit: true,
		method: 'POST',
		layout: 'anchor',
		items: [{
			xtype: 'displayfield',
			fieldLabel: 'Username',
			reference: 'userName',
			anchor: '50%',
			labelSeparator: ''			
		},{
			xtype: 'textfield',
			fieldLabel: 'First Name',			
			reference: 'firstName',
			anchor: '50%',
			allowBlank: false,
			maxLength: 30,
			labelSeparator: ''
		},{
			xtype: 'textfield',
			fieldLabel: 'Last Name',
			reference: 'lastName',
			anchor: '50%',
			allowBlank: false,
			maxLength: 30,
			labelSeparator: ''
		},{
			xtype: 'combobox',
			fieldLabel: 'Sex',
			reference: 'sex',
			anchor: '50%',
			labelSeparator: '',
			forceSelection: true,
			store: [
			['M', 'Male'],
			['F', 'Female']]			
		},{
			xtype: 'textfield',
			fieldLabel: 'Email',
			allowBlank: false,
			vtype: 'email',
			anchor: '50%',
			maxLength: 50,
			reference: 'email',
			labelSeparator: ''			
		},{
			xtype: 'numberfield',
			fieldLabel: 'Mobile',
			maxLength: 10,
			hideTrigger:true,
			anchor: '50%',
			reference: 'mobile',
			labelSeparator: ''			
		}],
		buttons: [{
			text:'Save',
			iconCls: 'x-fa fa-save',
			reference: 'saveprofile',
			formBind: true
		}]
	}]
	
});