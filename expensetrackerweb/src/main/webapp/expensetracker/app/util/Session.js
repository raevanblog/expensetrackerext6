Ext.define('expensetracker.util.Session', {
	singleton : true,
	requires : [ 'expensetracker.util.Storage' ],
	setUser : function(user) {
		var me = this;
		expensetracker.util.Storage.put('user', user);
		if(user.settings !== undefined && user.settings !== null) {
			me.setCurrency(user.settings.currency);
		}
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
	getProfilePicture : function() {
		return this.getUser().profilePic;
	},
	setPreferences : function(settings) {
		expensetracker.util.Storage.put('preferences', settings);
	},
	getPreferences : function() {
		return expensetracker.util.Storage.get('preferences');
	},
	setCurrency : function(currency) {
		if(currency !== undefined || currency !== null) {
			expensetracker.util.Storage.put('currency', currency.currtxt);
			expensetracker.util.Storage.put('currencysymbol', currency.currsymb);
		}
	},
	getCurrencyName : function() {
		return expensetracker.util.Storage.get('currency');
	},
	getCurrencySymbol : function() {
		return expensetracker.util.Storage.get('currencysymbol');
	},
	reload : function(controller) {
		var me = this;
		Ext.Ajax.request({
			url : expensetracker.util.Url.getSession(),
			success : function(response, opts) {
				var response = Ext.decode(response.responseText);
				if (response.success) {
					me.setUser(response.user);
					controller.fireEvent('updateprofile');
				} else {
					Ext.widget('login');
				}
			},
			failure : function(response, opts) {
				Ext.widget('app-main');
			}
		});
	},
	getCookie : function() {
		var me = this;
		var cookie = Ext.util.Cookies.get('expensetracker');
		if (cookie === null) {
			return cookie;
		} else {
			return Ext.decode(cookie);
		}
	},
	setCookie : function(object) {
		var cookie = Ext.encode(object);
		Ext.util.Cookies.set('expensetracker', cookie);
	}
});