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
	getFirstName : function() {
		return this.getUser().firstName;
	},
	getLastName : function() {
		return this.getUser().lastName;
	},
	getName : function() {
		return this.getFirstName() + ' ' + this.getLastName();
	},
	getUserSex : function() {
		return this.getUser().sex;
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
	},
	reload : function(model) {
		var me = this;
		Ext.Ajax.request({
			url : expensetracker.util.Url.getSessionReload(),
			success : function(response, opts) {
				var response = Ext.decode(response.responseText);
				if (response.success) {
					me.setUser(response.user);
					model.set('usrname', me.getName());
				} else {					
					Ext.widget('login');
				}
			},
			failure : function(response, opts) {
				Ext.widget('app-main');
			}
		});
	}
});