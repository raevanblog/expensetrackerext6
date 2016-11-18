Ext.define('expensetracker.util.Constants', {
	singleton : true,
	config : {
		toolbarHeight : 65,
		navBarWidth : 64,
		navBarExpandedWidth : 250
	},
	constructor : function(config) {
		var me = this;
		this.initConfig(config);
		return me;
	}
});