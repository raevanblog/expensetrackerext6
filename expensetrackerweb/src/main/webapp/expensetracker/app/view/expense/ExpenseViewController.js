Ext.define('expensetracker.view.expense.ExpenseViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expenseviewcontroller',
	onThumbnailClick : function(thumbnailcont, record, item, index, e) {
		var me = this;
		var refs = me.getReferences();
		var currentYear = new Date().getFullYear();
		var currentMonth = new Date().getMonth() + 1;
		var year = parseInt(refs.expensedock.getTitle());
		var monthNo = record.get('monthNo');
		var month = record.get('month');

		var date = new Date(year, monthNo - 1);

		if (year === currentYear && monthNo > currentMonth) {
			Ext.Msg.show({
				title : 'Expense Tracker',
				message : 'Cannot create expense for future date',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.INFO,
			});
		} else {

			var store = Ext.getStore('Expense');
			store.load({
				params : {
					username : 'shyamcse07',
					month : monthNo,
					year : year

				}
			});
			store.getProxy().setExtraParam({});

			var expenseGrid = Ext.create('expensetracker.view.expense.ExpenseGrid', {
				height : this.getView().getHeight() - 100,
				width : this.getView().getWidth() - 100,
				store : store
			});

			var window = Ext.create('Ext.window.Window', {
				layout : 'fit',
				modal : true,
				title : month + '-' + year,
				x : this.getView().getX(),
				y : this.getView().getY(),
				tools : [ {
					type : 'refresh',
					tooltip : 'Reload',
					handler : function(event) {
						expenseGrid.getStore().reload();
					}
				} ],
				listeners : {
					beforeclose : function(window) {
						var store = expenseGrid.getStore();
						if (store.getModifiedRecords().length > 0 || store.getRemovedRecords().length > 0) {
							Ext.Msg.show({
								title : 'Expense Tracker',
								message : 'You have unsaved changes. Do you want close and discard your changes?',
								buttons : Ext.Msg.YESNO,
								icon : Ext.Msg.QUESTION,
								fn : function(button) {
									if (button === 'yes') {
										window.purgeListeners();
										window.close();
									}
									if (button === 'no') {
										return false;
									}
								}
							});
						} else {
							return true;
						}
						return false;
					}
				}
			});

			window.add(expenseGrid);
			window.show();
		}
	},
	onYearSelection : function(slider, newValue, thumb, eOpts) {
		var me = this;
		var refs = me.getReferences();
		refs.expensedock.setTitle('' + newValue);
		refs.expviewselyear.update(newValue);
	},
	onRenderSlider : function(slider) {
		var me = this;
		var value = slider.getValue();
		var refs = me.getReferences();
		refs.expviewselyear.update(value);
	},
	onRenderExpenseDock : function(expensedock) {
		var me = this;
		expensedock.setTitle('' + new Date().getFullYear());		
	}
});