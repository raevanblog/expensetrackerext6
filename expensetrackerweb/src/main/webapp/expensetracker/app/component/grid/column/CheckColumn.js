Ext.define('expensetracker.component.grid.column.CheckColumn', {
	extend: 'Ext.grid.column.CheckColumn',
	alias : 'widget.yncheckcolumn',
	xtype : 'yncheckcolumn',
	constructor : function(config) {
		var me = this;
		me.callParent([ config ]);
	},
	defaultRenderer : function(value){
		var me = this,
		cls = me.checkboxCls,
		tip = me.tooltip;

		if (me.invert) {
			value === 'Y' ? 'N' : 'Y';
		}
		if (me.disabled) {
			cellValues.tdCls += ' ' + me.disabledCls;
		}
		
		if (value === 'Y') {
			cls += ' ' + me.checkboxCheckedCls;
			tip = me.checkedTooltip || tip;
		}
		
		if (me.useAriaElements) {
			cellValues.tdAttr += ' aria-describedby="' + me.id + '-cell-description' +
								 (value === 'N' ? '-not' : '') + '-selected"';
		}
			
		me.updateHeaderState();
 
		return '<span ' + (tip || '') + ' class="' + cls + '" role="' + me.checkboxAriaRole + '"' +
				(!me.ariaStaticRoles[me.checkboxAriaRole] ? ' tabIndex="0"' : '') +
			   '></span>';
	},
	setRecordCheck: function (record, recordIndex, checked, cell) {
		var me = this,
		prop = me.property,
		result, currentVal;
			
		if(record.get(me.dataIndex) === 'Y') {
			currentVal = true;
		} else {
			currentVal = false;
		}
 
		if (prop ? record[prop] : currentVal != checked) {
			if (prop) {
				record[prop] = checked;
				me.updater(cell, checked);
			} else {
				record.set(me.dataIndex, checked ? 'Y' : 'N');
			}
		}
	},
	isRecordChecked: function (record) {
		var prop = this.property;
		if (prop) {
			return record[prop];
		}
		return record.get(this.dataIndex) === 'Y' ? true : false;
	},
	updater: function (cell, value) {
		var me = this,
			tip = me.tooltip;
 
		if (me.invert) {
			value === 'Y' ? 'N' : 'Y';
		}
		if (value === 'Y') {
			tip = me.checkedTooltip || tip;
		}
		if (tip) {
			cell.setAttribute('data-qtip', tip);
		} else {
			cell.removeAttribute('data-qtip');
		}
		cell = Ext.fly(cell);
 
		if (me.useAriaElements) {
			me.updateCellAriaDescription(null, value, cell);
		}
		
		cell[me.disabled ? 'addCls' : 'removeCls'](me.disabledCls);
		Ext.fly(cell.down(me.getView().innerSelector, true).firstChild)[value === 'Y' ? 'addCls' : 'removeCls'](Ext.baseCSSPrefix + 'grid-checkcolumn-checked');
			
		me.updateHeaderState();
	}
});