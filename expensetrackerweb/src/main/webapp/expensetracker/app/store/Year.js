Ext.define('expensetracker.store.Year', {
	extend : 'Ext.data.Store',
	fields : [{
		name : 'year',
		type : 'int'		
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
                    return { year: value };
                });
                return data;
            }
		}		
	},
	autoLoad : false
});