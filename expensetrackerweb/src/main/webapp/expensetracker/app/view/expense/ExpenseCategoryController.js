Ext.define('expensetracker.view.expense.ExpenseCategoryController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensecategorycontroller',	
	onCategorySaveOrUpdate : function(saveCategoryBtn) {
		var me = this;
		var categorygrid = me.getView();
		var store = categorygrid.getStore();
		if (store.getModifiedRecords().length > 0 || store.getRemovedRecords().length > 0) {
			categorygrid.setLoading('Saving...');
			store.sync({
				success : function(batch) {
					categorygrid.setLoading(false);
					expensetracker.util.Grid.reload(categorygrid)
				},
				failure : function(batch) {
					categorygrid.setLoading(false);
					expensetracker.util.Grid.refresh(categorygrid);
				}
			});
		}

	},
	onRenderCategoryGrid : function(categorygrid) {
		var me = this;
		me.keyMap = Ext.create('Ext.util.KeyMap', categorygrid.el, [{
			key : 'n',
			fn : function() {
				me.onAddCategory();
			},
			ctrl : true,
			alt : true,
			scope :  me
		}, {
			key : 'r',
			fn : function() {
				expensetracker.util.Grid.refresh(categorygrid);
			},
			ctrl : true,
			alt : true,
			scope :  me
		}, {
			key : 's',
			fn : function() {
				me.onCategorySaveOrUpdate();
			},
			ctrl : true,
			alt : true,
			scope : me
		}, {
			key : 46,
			ctrl : true,
			fn : function() {
				me.onDeleteCategoryBySelection();
			},
			scope : me
		}]);
	},
	filterGrid : function(gridsearchtext, newValue, oldValue, options) {
		var me = this;
		var grid = me.getView();
		var store = grid.getStore();
		if (gridsearchtext.reference === 'categorygridsearch') {
			store.filter('category', newValue);
		}
	},
	onBackCategory : function(backCategBtn) {
		var me = this;
		var categorygrid = me.getView();
		var store = categorygrid.getStore();
		var expensewindow = categorygrid.up('window');
		if (store.getModifiedRecords().length > 0 || store.getRemovedRecords().length > 0) {
			Ext.Msg.show({
				title : 'Expense Tracker',
				message : 'Do you want to save your changes?',
				buttons : Ext.Msg.YESNO,
				icon : Ext.Msg.QUESTION,
				fn : function(button) {
					if (button === 'yes') {
						categorygrid.setLoading("Saving...");
						store.sync({
							success : function(batch) {
								categorygrid.setLoading(false);
								if (store.isFiltered()) {
									store.clearFilter();
								}
								expensewindow.getLayout().prev();
							},
							failure : function(batch) {
								categorygrid.setLoading(false);
								store.rejectChanges();
								expensetracker.util.Grid.refresh(categorygrid);
							}
						});
					}
					if (button === 'no') {
						if (store.isFiltered()) {
							store.clearFilter();
						}
						store.rejectChanges();
						expensewindow.getLayout().prev();
					}
				}
			});
		} else {
			if (store.isFiltered()) {
				store.clearFilter();
			}
			expensewindow.getLayout().prev();
		}
	},
	onAddCategory : function(addCategBtn) {
		var me = this;
		var categorygrid = me.getView();
		var store = categorygrid.getStore();
		var model = new expensetracker.model.ExpenseCategory({
			category : 'New Category',
			description : '',
			username : expensetracker.util.Session.getUsername()
		});
		store.insert(0, model);
		expensetracker.util.Grid.refresh(categorygrid);
	},
	onDeleteCategory : function(view, rowIndex, colIndex, item, e, record, row) {
		var me = this;
		var grid = me.getView();
		var store = grid.getStore();
		store.remove(record);
		expensetracker.util.Grid.refresh(grid);
	},
	onDeleteCategoryBySelection : function() {
		var me = this;
		var categorygrid = me.getView();
		var view = categorygrid.getView();
		var selectedRecords = view.getSelectionModel().getSelection();
		
		if(selectedRecords != null && selectedRecords.length > 0) {
			var store = categorygrid.getStore();
			store.remove(selectedRecords);
			expensetracker.util.Grid.refresh(categorygrid);
		}
	}
});