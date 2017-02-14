package com.slabs.expense.tracker.common.services;

import com.slabs.expense.tracker.common.database.entity.UserInfo;

public interface EmailService {
	
	public void sendActivationEmail(UserInfo user) throws Exception;
	
	public void sendMail(String subject, String message) throws Exception;
	
	public void sendRegSuccessMail(UserInfo user) throws Exception;
}
