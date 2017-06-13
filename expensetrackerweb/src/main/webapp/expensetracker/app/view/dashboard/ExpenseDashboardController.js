Ext.define('expensetracker.view.dashboard.ExpenseDashboardController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.expensedashboardcontroller',
	listen : {
		controller : {
			'*' : {
				updatedashboard : 'updateDashBoard',
				updatesummary : 'updateDashBoardSummary'
			}
		}
	},
	onRender : function(dashboard) {
		var me = this;
		if (expensetracker.util.Session.isFirstLogin()) {
			var settingsWindow = Ext.create('expensetracker.view.main.Settings', {
				modal : true,
				closable : false
			});
			var model = settingsWindow.getViewModel();
			model.set('buttonText', 'Save & Close');
			settingsWindow.show();
		}
									
		me.renderThumbnails();
		me.updateDashBoardSummary();
	},	
	onLoadExpenseData : function() {
		var me = this;
		var expenseyear = me.lookup('expenseyear');
		var expensemonth = me.lookup('expensemonth');
		
		if(expensetracker.util.Calendar.isCurrentYear(expenseyear.getValue()) && expensemonth.getValue() > expensetracker.util.Calendar.getCurrentMonthNo()) {		
			Ext.Msg.show({
				title : 'Expense Tracker',
				message : 'Please select correct month',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.INFO
			});		
		} else  {
			expensetracker.util.Session.setExpenseMonth(expensemonth.getValue());
			expensetracker.util.Session.setExpenseYear(expenseyear.getValue());
			me.updateDashBoard();
		}				
	},
	onOpenExpenseSheet : function(thumbnailcont, record, item, index, e) {

		var me = this;
		var model = me.getView().getViewModel();
		var date = new Date(expensetracker.util.Session.getExpenseYear(), expensetracker.util.Session.getExpenseMonth() - 1);

		var expenseWindow = Ext.create('expensetracker.view.expense.ExpenseWindow', {
			height : expensetracker.util.Constants.getWindowHeight(),
			width : expensetracker.util.Constants.getWindowWidth(),
			x : expensetracker.util.Constants.getWindowX(),
			y : expensetracker.util.Constants.getWindowY(),
			resizable : false,
			constrain : true,
			modal : true
		});

		var model = expenseWindow.getViewModel();
		model.set('expenseStartDate', Ext.Date.getFirstDateOfMonth(date));
		model.set('expenseDate', Ext.Date.getFirstDateOfMonth(date));
		model.set('expenseEndDate', Ext.Date.getLastDateOfMonth(date));
		model.set('month', expensetracker.util.Session.getExpenseMonth());
		model.set('year', expensetracker.util.Session.getExpenseYear());
		model.set('origin', 'dashboard');
		model.set('title', expensetracker.util.Calendar.getName(expensetracker.util.Session.getExpenseMonth()) + ' - ' + expensetracker.util.Session.getExpenseYear());
		expenseWindow.show();
	},
	onOpenIncomeSheet : function() {
		var me = this;
		var incomeWindow = Ext.create('expensetracker.view.income.IncomeWindow', {
			height : expensetracker.util.Constants.getWindowHeight(),
			width : expensetracker.util.Constants.getWindowWidth(),
			x : expensetracker.util.Constants.getWindowX(),
			y : expensetracker.util.Constants.getWindowY(),
			modal : true
		});
		var model = incomeWindow.getViewModel();
		model.set('source', me.getView());
		model.set('month', expensetracker.util.Session.getExpenseMonth());
		model.set('year', expensetracker.util.Session.getExpenseYear());
		model.set('origin', 'dashboard');
		model.set('title', expensetracker.util.Calendar.getName(expensetracker.util.Session.getExpenseMonth()) + ' - ' + expensetracker.util.Session.getExpenseYear());

		incomeWindow.show();
	},
	onOpenInventory : function() {
		var me = this;
		var inventoryWindow = Ext.create('expensetracker.view.inventory.InventoryWindow', {
			height : expensetracker.util.Constants.getWindowHeight(),
			width : expensetracker.util.Constants.getWindowWidth(),
			x : expensetracker.util.Constants.getWindowX(),
			y : expensetracker.util.Constants.getWindowY(),
			modal : true
		});
		var model = inventoryWindow.getViewModel();
		model.set('source', me.getView());
		model.set('month', expensetracker.util.Session.getExpenseMonth());
		model.set('year', expensetracker.util.Session.getExpenseYear());
		model.set('title', expensetracker.util.Calendar.getName(expensetracker.util.Session.getExpenseMonth()) + ' - ' + expensetracker.util.Session.getExpenseYear());

		inventoryWindow.show();
	},
	onOpenReport : function() {
		var me = this;
		var pdfWindow = Ext.create('expensetracker.view.report.ReportWindow', {
			height : expensetracker.util.Constants.getWindowHeight(),
			width : expensetracker.util.Constants.getWindowWidth(),
			x : expensetracker.util.Constants.getWindowX(),
			y : expensetracker.util.Constants.getWindowY(),
			modal : true,
			reportUrl : expensetracker.util.Url.getReportingService() + '?username=' + expensetracker.util.Session.getUsername() + '&year=' + expensetracker.util.Session.getExpenseYear() + '&month='
					+ expensetracker.util.Session.getExpenseMonth()
		});
		pdfWindow.show();
	},
	updateDashBoardSummary : function() {
		var me = this;
		var summaryContainer = me.lookup('summary');
		summaryContainer.setLoading('Loading...');
		Ext.Ajax.request({
			url : expensetracker.util.Url.getDashboardService(),
			method : 'GET',
			params : {
				username : expensetracker.util.Session.getUsername(),
				month : expensetracker.util.Session.getExpenseMonth(),
				year : expensetracker.util.Session.getExpenseYear()
			},
			success : function(response, opts) {
				summaryContainer.setLoading(false);
				var response = Ext.decode(response.responseText);
				var dashData = response.result.any[0];
				if (dashData != null || dashData != undefined) {
					var summary = dashData.summary;
					var totalIncome = summary.totalIncome;
					var totalExpense = summary.totalExpense;
					var creditExpense = summary.creditExpense;
					var cashInHand = summary.cashInHand;
					var data = [ {
						category : 'Opening Balance',
						value : totalIncome.toString()
					}, {
						category : 'Total Expense',
						value : totalExpense.toString()
					}, {
						category : 'Credit Card Expense',
						value : creditExpense.toString()
					}, {
						category : 'Balance',
						value : cashInHand.toString()
					} ];
					summaryContainer.down('#summary-component').setData(data);
					if (totalIncome !== 0.0) {
						var summaryPie = me.lookup('summarypie');
						var polarChart = summaryPie.down('[itemId=summaryPolar]');
						
						if(polarChart.isHidden()) {
							polarChart.setHidden(false);
						}
						
						var pieStore = polarChart.getStore();
						pieStore.removeAll();

						var expPerc = (totalExpense / totalIncome) * 100;
						var cashPerc = (cashInHand / totalIncome) * 100;

						pieStore.add({
							item : 'Expense',
							value : expPerc
						}, {
							item : 'Cash in Hand',
							value : cashPerc
						});						
						polarChart.bindStore(pieStore);						
						polarChart.redraw();
					} else {
						var summaryPie = me.lookup('summarypie');
						var polarChart = summaryPie.down('[itemId=summaryPolar]');
						var pieStore = polarChart.getStore();
						pieStore.removeAll();
						polarChart.setHidden(true);
					}
				}
			},
			failure : function(response, opts) {
				summaryContainer.setLoading(false);
			}
		});

	},
	updateExpVsIncomeChart : function() {
		var me = this;
		var expvsincome = me.lookup('expensevsincome');
		var linechart = expvsincome.down('[itemId=linechart]');		
		linechart.getStore().load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				year : expensetracker.util.Session.getExpenseYear(),
				type : 'EXPENSE_VS_INCOME_MONTHLY'
			},
			callback : function(records, operation, success) {				
				if (!success) {
					var response = Ext.JSON.decode(operation.getError().response.responseText);
					expensetracker.util.Message.toast(response.message);
					if (401 === response.statusCode) {
						me.fireEvent('navigatelogin');
						if (view !== null) {
							view.close();
						}
					}
				}
			}			
		});
	},
	updateExpenseChart : function() {
		var me = this;
		var categoryexpense = me.lookup('categorychartpanel');
		var expensechart = categoryexpense.down('[itemId=expensechart]');		
		expensechart.getStore().load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				year : expensetracker.util.Session.getExpenseYear(),
				month : expensetracker.util.Session.getExpenseMonth(),
				type : 'CATEGORY_EXPENSE_TOTAL'
			},
			callback : function(records, operation, success) {				
				if (!success) {
					var response = Ext.JSON.decode(operation.getError().response.responseText);
					expensetracker.util.Message.toast(response.message);
					if (401 === response.statusCode) {
						me.fireEvent('navigatelogin');
						if (view !== null) {
							view.close();
						}
					}
				}
			}
		});
	},
	updateDashBoard : function() {
		var me = this;
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
				year : expensetracker.util.Session.getExpenseYear(),
				type : 'EXPENSE_VS_INCOME_MONTHLY'
			},
			callback : function(records, operation, success) {
				expvsincome.setLoading(false);
				if (!success) {
					var response = Ext.JSON.decode(operation.getError().response.responseText);
					expensetracker.util.Message.toast(response.message);
					if (401 === response.statusCode) {
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
				year : expensetracker.util.Session.getExpenseYear(),
				month : expensetracker.util.Session.getExpenseMonth(),
				type : 'CATEGORY_EXPENSE_TOTAL'
			},
			callback : function(records, operation, success) {
				categorychartpanel.setLoading(false);
				if (!success) {
					var response = Ext.JSON.decode(operation.getError().response.responseText);
					expensetracker.util.Message.toast(response.message);
					if (401 === response.statusCode) {
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
	renderThumbnails: function() {
		var me = this;
		var expenseSheet = me.lookup('expsheetdash');
		var incomeSheet = me.lookup('incomesheetdash');
		var inventorySheet = me.lookup('inventorydash');
		var report = me.lookup('reportdash');
		var expSheetStore, incSheetStore, invSheetStore, reportStore;
		
		expSheetStore = Ext.create('expensetracker.store.Month');
		incSheetStore = Ext.create('expensetracker.store.Month');
		invSheetStore = Ext.create('expensetracker.store.Month');
		reportStore = Ext.create('expensetracker.store.Month');
		

		expSheetStore.removeAll();
		incSheetStore.removeAll();
		invSheetStore.removeAll();
		reportStore.removeAll();

		expSheetStore.add({
			title : 'Expense',
			month : expensetracker.util.Calendar.getName(expensetracker.util.Session.getExpenseMonth()),
			monthNo : expensetracker.util.Session.getExpenseMonth()
		});

		incSheetStore.add({
			title : 'Opening Balance',
			month : expensetracker.util.Calendar.getName(expensetracker.util.Session.getExpenseMonth()),
			monthNo : expensetracker.util.Session.getExpenseMonth()
		});

		invSheetStore.add({
			title : 'Inventory',
			month : expensetracker.util.Calendar.getName(expensetracker.util.Session.getExpenseMonth()),
			monthNo : expensetracker.util.Session.getExpenseMonth()
		});

		reportStore.add({
			title : 'Report',
			month : expensetracker.util.Calendar.getName(expensetracker.util.Session.getExpenseMonth()),
			monthNo : expensetracker.util.Session.getExpenseMonth()
		});
	
		expenseSheet.bindStore(expSheetStore);
		incomeSheet.bindStore(incSheetStore);
		inventorySheet.bindStore(invSheetStore);
		report.bindStore(reportStore);
		
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