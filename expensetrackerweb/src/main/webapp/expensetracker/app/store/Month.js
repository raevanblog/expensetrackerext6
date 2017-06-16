Ext.define('expensetracker.store.Month', {
	extend : 'Ext.data.Store',
	fields : [{
		name : 'month',
		type : 'int'		
	}, {
		name : 'name',
		type : 'string'
	}],
	proxy : {
		type : 'rest',
		batchActions : true,
		useDefaultXhrHeader : false,
		api : {			
			read : expensetracker.util.Url.getExpenseRange()			
		},
		actionMethods : {		
			read : 'GET'			
		},
		reader : {
			type : 'json',
			messageProperty : 'message',
			rootProperty : 'result.any',
			transform: function(data){
                data.result.any = data.result.any.map(function(value){					
                    return { month: value, name: expensetracker.util.Calendar.getName(value) };
                });
                return data;
            }
		}		
	},
	autoLoad : false
});