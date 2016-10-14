Ext.define('expensetracker.util.Session', {
	singleton : true,
	requires : [ 'expensetracker.util.Storage' ],
	setUser : function(user) {
		expensetracker.util.Storage.put("user", user);
	},
	getUser : function() {
		return expensetracker.util.Storage.get("user");
	},
	getUsername: function() {
		return this.getUser().username;
	}
});