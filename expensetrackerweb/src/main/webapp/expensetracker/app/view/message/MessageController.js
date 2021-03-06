Ext.define('expensetracker.view.message.MessageController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.message',
	onRender : function() {
		var me = this;
		me.setCurrentView('inbox');
	},
	onEmailMenuClick : function(menu, item, e, obj) {
		var me = this;
		me.setCurrentView(item.routeId, item.params);
	},
	onDetailsBack : function(backBtn) {
		var me = this;
		me.setCurrentView('inbox');
	},
	onDetailsReply : function(replyBtn) {
		var me = this;
		var details = replyBtn.up('panel');
		var replywin = Ext.apply({
			xtype : 'emailcompose',
			reference : 'compose'
		}, {
			modal : true,
			title : 'Reply',
			type : 'REPLY',
			constrain : true,
			height: '85%',
			width : '80%',
			record : details.record
		});
		Ext.create(replywin);
	},
	onGridCellItemClick : function(view, td, cellIndex, record) {
		var me = this;			
		if('Y' === record.get('isNew')) {
			view.setLoading('Loading...');		
			var params = [];		
			params.push({
					id: record.get('id'),
					isNew : 'N',
					msgto : record.get('msgto')
			});
			Ext.Ajax.request({
				url : expensetracker.util.Url.getMessageService(),
				method : 'PUT',
				jsonData : Ext.JSON.encode(params),
				success : function(response, opts) {				
					view.setLoading(false);
					me.setCurrentView('messagedetails', {record: record});					
				},
				failure : function(response, opts) {
					view.setLoading(false);
					var response = Ext.decode(response.responseText);
					if (401 === response.statusCode) {
						me.fireEvent('navigatelogin');						
					}else{
						expensetracker.util.Message.toast('* Server Error');
					}
				} 			
			});
		} else {
			me.setCurrentView('messagedetails', {record: record});
		}		
	},
	setCurrentView : function(view, params) {
		var me = this;
		var viewer = me.getView().down('#viewer');

		if (!viewer || view === '' || (viewer.down() && viewer.down().xtype === view)) {
			return false;
		}

		if(params && params.isWindow) {
			var win = Ext.apply({
				xtype : view,
				reference : params.reference
			},params);
			Ext.create(win);			
		}else {
			Ext.suspendLayouts();

			viewer.removeAll();

			viewer.add(Ext.apply({
				xtype : view
			}, params));

			Ext.resumeLayouts(true);
		}

	},
	onRenderInbox : function(inbox) {
		var me = this;
		var view = me.getView();
		var store = Ext.create('expensetracker.store.Message');
		inbox.setLoading('Loading...');
		store.load({
			params : {
				username : expensetracker.util.Session.getUsername(),
				isNew : false
			},
			callback : function(records, operation, success) {
				inbox.setLoading(false);
				if (!success) {
					var response = Ext.JSON.decode(operation.getError().response.responseText);
					expensetracker.util.Message.toast(response.message);
					if (401 === response.statusCode) {
						me.fireEvent('navigatelogin');
						if (view != null) {
							view.close();
						}
					}
				}
			}
		});
		inbox.bindStore(store);
	},
	onRenderDetails : function(details) {
		var record = details.record ? details.record : {};		
        details.down('#mailbody').setHtml(record.get('message'));        
        details.down('#mailsubject').setData(record);
        details.down('#senderpic').setSrc(record.get('senderpic'));
	},
	onRenderCompose : function(compose) {
		var record = compose.record ? compose.record : {};
		compose.down('#profileimg').setSrc(expensetracker.util.Session.getProfilePicture());
		if("REPLY" === compose.type) {			
			var message = '<br><br><br><br><br><br><hr>' + '<div style="font-size=16px">From : '+record.get('senderfname')+' '+ record.get('senderlname')+'</div>'+'<div style="font-size=16px">Subject : '+record.get('subject')+'</div>'+'<div>'+ Ext.Date.format(record.get('msgdate'), 'D M d,Y H:i:s A')+'</div>'+record.get('message');
			
			compose.down('#messagesubject').setValue('Reply: ' + record.get('subject'));
			compose.down('#messageeditor').setValue(message);
		}
	},
	onDiscardMessage : function(button) {
		var me = this;
		var compose = button.up('#compose');
		if(null != compose) {
			compose.close();
		}
	}
});