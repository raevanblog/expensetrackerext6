Ext.define('expensetracker.view.dashboard.ExpenseDashboardController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensedashboardcontroller',
	listen : {
		controller : {
			'*' : {
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
		var currentYear = expensetracker.util.Calendar.getCurrentYear();
		var currentMonth = expensetracker.util.Calendar.getCurrentMonthNo();
		var date = new Date(currentYear, currentMonth - 1);

		var expenseWindow = Ext.create('expensetracker.view.expense.ExpenseWindow', {
			height : Ext.getBody().getViewSize().height - 70,
			width : Ext.getBody().getViewSize().width - 250,
			x : me.getView().getX(),
			y : me.getView().getY(),
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
		var incomeWindow = Ext.create('expensetracker.view.income.IncomeWindow', {
			height : 500,
			width : 400,
			x : me.getView().getX(),
			y : me.getView().getY(),
			modal : true
		});
		var model = incomeWindow.getViewModel();
		model.set('source', me.getView());
		model.set('month', expensetracker.util.Calendar.getCurrentMonthNo());
		model.set('year', expensetracker.util.Calendar.getCurrentYear());
		model.set('title', expensetracker.util.Calendar.getCurrentMonth() + ' - ' + expensetracker.util.Calendar.getCurrentYear());
		incomeWindow.show();
	},
	renderToolTip : function(tooltip, record, item) {
		var value = Ext.util.Format.number(record.get('value'), '0.00');
		tooltip.setHtml(value + ' %');
	},	
	updateDashBoardSummary : function() {
		var me = this;
		var totInc = me.lookup('totIncome');
		var totExp = me.lookup('totExpense');
		var summaryPanel = me.lookup('summarypanel');
		var cih = me.lookup('cashInHand');
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
					var piePanel = me.lookup('summarypiepanel');
					var pie = piePanel.down('[itemId=summaryPolar]');

					totInc.setValue(summary.totalIncome);
					totExp.setValue(summary.totalExpense);
					cih.setValue(summary.cashInHand);
					if (summary.totalIncome !== 0.0) {
						var pieStore = Ext.create('Ext.data.Store');
						var expPercentage = (summary.totalExpense / summary.totalIncome) * 100;
						var cihPercentage = (summary.cashInHand / summary.totalIncome) * 100;

						pieStore.add({
							item : 'CASH-IN-HAND',
							value : cihPercentage
						}, {
							item : 'EXPENSE',
							value : expPercentage
						});
						pie.bindStore(pieStore);
					} else {
						var pieStore = pie.getStore();
						if (pieStore !== null || pieStore !== undefined) {
							pieStore.removeAll();
						}
					}
				}				
			},
			failure : function(response, opts) {
				summaryPanel.setLoading(false);				
			}
		});

	},
	updateTopExpense: function() {
		var me = this;
		var topexpense = me.lookup('topexpense');
		topexpense.getStore().reload();		
	}
});