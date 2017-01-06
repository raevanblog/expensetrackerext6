Ext.define('expensetracker.view.dashboard.ExpenseDashboardController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensedashboardcontroller',
	listen : {
		controller : {
			'*' : {
				updatedashboard : 'updateDashBoard',
				updatesummary : 'updateDashBoardSummary',
				updatetopexpense : 'updateTopExpense'
			}
		}
	},
	onRender : function(dashboard) {
		var me = this;
		var topexpense = me.lookup('topexpense');

		var sheet = me.lookup('expsheetdash');

		var expSheetStore = Ext.create('expensetracker.store.ExpenseDock');
		expSheetStore.removeAll();
		expSheetStore.add({
			month : expensetracker.util.Calendar.getCurrentMonth(),
			monthNo : expensetracker.util.Calendar.getCurrentMonthNo()
		});

		sheet.bindStore(expSheetStore);

		var expenseStore = Ext.create('expensetracker.store.Expense');
		expenseStore.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				month : expensetracker.util.Calendar.getCurrentMonthNo(),
				year : expensetracker.util.Calendar.getCurrentYear(),
				fetchTopExpense : true
			}
		});

		topexpense.bindStore(expenseStore);

		me.updateDashBoardSummary();
	},

	onOpenExpenseSheet : function(thumbnailcont, record, item, index, e) {

		var me = this;
		var model = me.getView().getViewModel();
		var currentYear = expensetracker.util.Calendar.getCurrentYear();
		var currentMonth = expensetracker.util.Calendar.getCurrentMonthNo();
		var date = new Date(currentYear, currentMonth - 1);

		var expenseWindow = Ext.create('expensetracker.view.expense.ExpenseWindow', {
			height : Ext.Element.getViewportHeight(),
			width : Ext.Element.getViewportWidth(),
			resizable : false,
			modal : true
		});

		var model = expenseWindow.getViewModel();
		model.set('expenseStartDate', Ext.Date.getFirstDateOfMonth(date));
		model.set('expenseDate', Ext.Date.getFirstDateOfMonth(date));
		model.set('expenseEndDate', Ext.Date.getLastDateOfMonth(date));
		model.set('month', currentMonth);
		model.set('year', currentYear);
		model.set('title', record.get('month') + ' - ' + currentYear);

		expenseWindow.show();
	},
	onIncomeEditClick : function() {
		var me = this;
		var model = me.getView().getViewModel();
		var incomeWindow = Ext.create('expensetracker.view.income.IncomeWindow', {
			height : Ext.Element.getViewportHeight(),
			width : Ext.Element.getViewportWidth(),
			modal : true
		});
		var model = incomeWindow.getViewModel();
		model.set('source', me.getView());
		model.set('month', expensetracker.util.Calendar.getCurrentMonthNo());
		model.set('year', expensetracker.util.Calendar.getCurrentYear());
		model.set('title', expensetracker.util.Calendar.getCurrentMonth() + ' - ' + expensetracker.util.Calendar.getCurrentYear());
		incomeWindow.show();
	},
	updateDashBoardSummary : function() {
		var me = this;		
		var summaryPanel = me.lookup('summarypanel');		
		summaryPanel.setLoading('Loading...');
		Ext.Ajax.request({
			url : expensetracker.util.Url.getDashboardService(),
			method : 'GET',
			params : {
				username : expensetracker.util.Session.getUsername(),
				month : expensetracker.util.Calendar.getCurrentMonthNo(),
				year : expensetracker.util.Calendar.getCurrentYear()
			},
			success : function(response, opts) {
				summaryPanel.setLoading(false);
				var response = Ext.decode(response.responseText);
				var dashData = response.result.any[0];
				if (dashData != null || dashData != undefined) {
					var summary = dashData.summary;
					var data = [{
						category : 'Total Income',
						value : summary.totalIncome + ''
					},{
						category : 'Total Expense',
						value : summary.totalExpense + ''
					}, {
						category : 'Cash In Hand',
						value : summary.cashInHand + ''
					}];
					summaryPanel.down('#summary').down('#summary-component').setData(data);
				}
			},
			failure : function(response, opts) {
				summaryPanel.setLoading(false);
			}
		});

	},
	updateTopExpense : function() {
		var me = this;
		var topexpense = me.lookup('topexpense');
		topexpense.getStore().reload();
	},
	updateExpVsIncomeChart : function() {
		var me = this;
		var expvsincome = me.lookup('expensevsincome');
		var linechart = expvsincome.down('[itemId=linechart]');
		linechart.getStore().reload();
	},
	updateExpenseChart : function() {
		var me = this;
		var categoryexpense = me.lookup('categorychartpanel');
		var expensechart = categoryexpense.down('[itemId=expensechart]');
		expensechart.getStore().reload();
	},
	updateDashBoard : function() {
		var me = this;
		me.updateTopExpense();
		me.updateDashBoardSummary();
		me.updateExpVsIncomeChart();
		me.updateExpenseChart();
	},
	onRenderExpenseVsIncome : function(panel) {
		var me = this;
		var view = me.getView();
		var expvsincome = me.lookup('expensevsincome');
		var linechart = expvsincome.down('[itemId=linechart]');
		var graphStore = Ext.create('expensetracker.store.Graph');
		expvsincome.setLoading('Loading...');
		graphStore.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				year : expensetracker.util.Calendar.getCurrentYear(),
				type : 'EXPENSE_VS_INCOME_MONTHLY'
			},
			callback : function(records, operation, success) {
				expvsincome.setLoading(false);
				if (!success) {
					var response = Ext.JSON.decode(operation.getError().response.responseText);
					expensetracker.util.Message.toast(response.status_Message);
					if (401 === response.status_Code) {
						me.fireEvent('navigatelogin');
						if (view !== null) {
							view.close();
						}
					}
				}
			}
		});

		linechart.bindStore(graphStore);
	},

	onRenderCategoryExpense : function(panel) {
		var me = this;
		var view = me.getView();
		var categorychartpanel = me.lookup('categorychartpanel');
		var expensechart = categorychartpanel.down('[itemId=expensechart]');
		var graphStore = Ext.create('expensetracker.store.Graph');
		categorychartpanel.setLoading('Loading...');
		graphStore.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				year : expensetracker.util.Calendar.getCurrentYear(),
				month : expensetracker.util.Calendar.getCurrentMonthNo(),
				type : 'CATEGORY_EXPENSE_TOTAL'
			},
			callback : function(records, operation, success) {
				categorychartpanel.setLoading(false);
				if (!success) {
					var response = Ext.JSON.decode(operation.getError().response.responseText);
					expensetracker.util.Message.toast(response.status_Message);
					if (401 === response.status_Code) {
						me.fireEvent('navigatelogin');
						if (view !== null) {
							view.close();
						}
					}
				}
			}
		});

		expensechart.bindStore(graphStore);
	},
	onLCPreview : function(button) {
		var me = this;
		var expensevsincome = me.lookup('expensevsincome');
		var linechart = expensevsincome.down('[itemId=linechart]');
		linechart.preview();
	},
	onCCPreview : function(button) {
		var me = this;
		var categorychartpanel = me.lookup('categorychartpanel');
		var cchart = categorychartpanel.down('[itemId=expensechart]');
		cchart.preview();
	}
});