Ext.define('expensetracker.util.Storage', {
	singleton: true,
	storage: [],
	getData: function(key) {
		var length = this.storage.length;
		for(var i=0; i< length; i++) {
			var data = this.storage[i];
			if(data.key === key) {
				return data;
			}
		}
	},
	get : function(key) {
		var data = this.getData(key);
		if(data != undefined) {
			return data.value;
		}
	},	
	put : function(key, value) {
		var data = this.getData(key);
		if(data === undefined) {
			data = new Object();
			data.key = key;
			data.value = value;
			this.storage.push(data);
		}else{
			data.value = value;
		}
	}
	
});