Ext.define('expensetracker.util.Session', {
	singleton : true,
	requires : [ 'expensetracker.util.Storage' ],
	setUser : function(user) {
		expensetracker.util.Storage.put('user', user);
	},
	getUser : function() {
		return expensetracker.util.Storage.get('user');
	},
	getUsername : function() {
		return this.getUser().username;
	},
	setCurrency : function(currency) {
		expensetracker.util.Storage.put('currency', currency.name);
		expensetracker.util.Storage.put('currencysymbol', currency.symbol);
	},
	getCurrencyName : function() {
		return expensetracker.util.Storage.get('currency');
	},
	getCurrencySymbol : function() {
		return expensetracker.util.Storage.get('currencysymbol');
	}
});