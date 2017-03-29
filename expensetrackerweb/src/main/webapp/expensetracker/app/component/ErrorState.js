Ext.define('expensetracker.component.ErrorState', {
	extend : 'Ext.Component',
	xtype : 'errorstate',
	alias : 'widget.errorstate',
	invalidCls: Ext.baseCSSPrefix + 'form-invalid-icon-default',
	validCls: Ext.baseCSSPrefix + 'dd-drop-icon',
	baseCls: 'form-error-state',
	validText: '',
	closable : false,
    invalidText: 'Exception',
	constructor : function(config) {
		var me = this;
		me.callParent([ config ]);
	},
	tipTpl: [
		'<ul class="' + Ext.baseCSSPrefix + 'list-plain">',
			'<tpl for=".">',
				'<li><span class="field-name">{name}</span>: ',
					'<span class="error">{error}</span>',
				'</li>',
			'</tpl>',
		'</ul>'
    ],
	setErrors: function(errors) {
		var me = this,
			tpl = me.tipTpl,
			tip = me.tip;

		if (!me.tipTpl.isTemplate) {
			tpl = me.tipTpl = new Ext.XTemplate(tpl);
		}

		if (!tip) {
			tip = me.tip = Ext.widget('tooltip', {
				target: me.el,                        
				minWidth: 200,
				autoHide: false,
				anchor: 'top',
				title : 'Details',
				mouseOffset: [-11, -2],
				closable: me.closable,
				constrainPosition: false,
				cls: 'errors-tip'
			});
		}

		errors = Ext.Array.from(errors);		
		if (errors.length) {
			me.addCls(me.invalidCls);
			me.removeCls(me.validCls);
			me.update(me.invalidText);
			tip.setDisabled(false);
			tip.update(tpl.apply(errors));
			tip.show();
		}
		else {
			me.addCls(me.validCls);
			me.removeCls(me.invalidCls);
			me.update(me.validText);
			tip.setDisabled(true);
			tip.hide();
		}
	}
});