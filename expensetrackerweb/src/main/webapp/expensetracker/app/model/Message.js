Ext.define('expensetracker.model.Message', {
	extend : 'Ext.data.Model',
	alias : 'model.message',
	fields : [ {
		name : 'id',
		type : 'int',
		persist : false
	}, {
		name : 'msgto',
		type : 'string'
	}, {
		name : 'msgfrom',
		type : 'string'		
	}, {
		name : 'subject',
		type : 'string'
	}, {
		name : 'message',
		type : 'string'
	}, {
		name : 'email',
		type : 'string'
	}, {
		name : 'isNew',
		type : 'string'
	}],
	idProperty : 'id',
	validators : {
		msgto : 'presence',
		msgfrom : 'presence',
		subject : 'presence',
		message : 'presence',
		email : 'presence'		
	}
});