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
	},
	getWindowHeight : function() {	
		if(!Ext.os.is.Windows) {
			return Ext.Element.getViewportHeight();
		}
		return Ext.Element.getViewportHeight() - this.getToolbarHeight();	
	},
	getWindowWidth : function() {
		if(!Ext.os.is.Windows) {
			return Ext.Element.getViewportWidth();
		}
		return Ext.Element.getViewportWidth() - this.getNavBarWidth();
	},
	getWindowY : function() {
		if(!Ext.os.is.Windows) {
			return 0;
		}
		return this.getToolbarHeight();	
	},
	getWindowX : function() {
		if(!Ext.os.is.Windows) {
			return 0;
		}
		return this.getNavBarWidth();	
	}
});