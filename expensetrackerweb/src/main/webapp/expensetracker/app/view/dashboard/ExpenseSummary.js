Ext.define('expensetracker.view.dashboard.ExpenseSummary', {
	extend: 'Ext.view.View',
	xtype: 'expensesummary',
	alias: 'view.expensesummary',
	itemSelector: 'div.summary',
	border: false,
	scrollable: true,
	itemTpl: [
	   '<tpl for="."> ' +
	      '<div class="summary">'+
			   '<section class="category-section-left">'+
				  '<p><br>{category}'+
				  '<br>{expdate:this.formatDate(values)}</p>'+
			   '</section>'+
			   '<section class="category-section-right">'+
				 '<table class="content-wrap">'+
				   '<tr>'+
					 '<td>Item Name</td>'+
					 '<td>:</td>'+
					 '<td>{itemName}</td>'+
				   '<tr>'+
				   '<tr>'+
					 '<td>Price</td>'+
					 '<td>:</td>'+
					 '<td>{price}&nbsp{exptype:this.renderExpType(values)}</td>'+
				   '<tr>'+
				   '<tr>'+
					 '<td>Quantity</td>'+
					 '<td>:</td>'+
					 '<td>{qty}</td>'+
				   '<tr>'+
				 '</table>'+
			   '</section>'+
		  '</div>'
	   +'</tpl>',
	   {
		  formatDate: function(value) {			 
			  return Ext.Date.format(value,'d-M-Y');
		  } ,
		  renderExpType: function(value) {
			 if('CASH' === value) {
				 return '<span class="x-fa fa-money tooltip"><span class="tooltiptext">'+ value +'</span></span>';
			 }else if('CREDIT CARD' === value || 'DEBIT CARD' === value) {
				 return '<span class="x-fa fa-credit-card tooltip"><span class="tooltiptext">'+ value +'</span></span>';
			 }else{
				 return '<span class="x-fa fa-desktop tooltip"><span class="tooltiptext">'+ value +'</span></span>';
			 }
		  }	
	   }
	]	
});