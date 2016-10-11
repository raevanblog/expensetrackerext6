Ext.define('expensetracker.util.Calendar', {
	singleton : true,
	calendar: [],
	currentDate: new Date(),
	init: function() {
		var jan = new Object();
		jan.name = 'January';
		jan.shortName = 'Jan';
		jan.monthNo = 1;
		
		var feb = new Object();
		feb.name = 'February';
		feb.shortName = 'Feb';
		feb.monthNo = 2;
		
		var mar = new Object();
		mar.name = 'March';
		mar.shortName = 'Mar';
		mar.monthNo = 3;
		
		var apr = new Object();
		apr.name = 'April';
		apr.shortName = 'Apr';
		apr.monthNo = 4;
		
		var may = new Object();
		may.name = 'May';
		may.shortName = 'May';
		may.monthNo = 5;
		
		var jun = new Object();
		jun.name = 'June';
		jun.shortName = 'Jun';
		jun.monthNo = 6;
		
		var jul = new Object();
		jul.name = 'July';
		jul.shortName = 'Jul';
		jul.monthNo = 7;
		
		var aug = new Object();
		aug.name = 'August';
		aug.shortName = 'Aug';
		aug.monthNo = 8;
		
		var sep = new Object();
		sep.name = 'September';
		sep.shortName = 'Sep';
		sep.monthNo = 9;
		
		var oct = new Object();
		oct.name = 'October';
		oct.shortName = 'Oct';
		oct.monthNo = 10;
		
		var nov = new Object();
		nov.name = 'November';
		nov.shortName = 'Nov';
		nov.monthNo = 11;
		
		var dec = new Object();
		dec.name = 'December';
		dec.shortName = 'Dec';
		dec.monthNo = 12;
		
		this.calendar.push(jan);
		this.calendar.push(feb);
		this.calendar.push(mar);
		this.calendar.push(apr);
		this.calendar.push(may);
		this.calendar.push(jun);
		this.calendar.push(jul);
		this.calendar.push(aug);
		this.calendar.push(sep);
		this.calendar.push(oct);
		this.calendar.push(nov);
		this.calendar.push(dec);
			
	},
	getName : function(monthNo) {
		var length = this.calendar.length;
		for(var i=0; i<length; i++) {
			var cal = calendar[i];
			if(cal.monthNo === monthNo) {
				return cal.name;
			}
		}
	},
	getShortName : function(monthNo) {
		var length = this.calendar.length;
		for(var i=0; i<length; i++) {
			var cal = calendar[i];
			if(cal.monthNo === monthNo) {
				return cal.shortName;
			}
		}
	},	
	getCurrentMonth : function(isShortName) {	
		if(!isShortName) {
			return this.getName(this.currentDate.getMonth()+1);
		}else{
			return this.getShortName(this.currentDate.getMonth()+1);
		}		
	},	
	getCurrentMonthNo : function() {		
		return this.currentDate.getMonth+1;
	},
	getCurrentYear : function() {
		return this.currentDate.getFullYear();
	},
	getCurrentDate : function() {
		return this.currentDate;
	}
	
});