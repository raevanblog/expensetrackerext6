package com.slabs.expensetracker.common.services;

import com.slabs.expensetracker.common.database.entity.UserInfo;

public interface EmailService {
	
	public void sendActivationEmail(UserInfo user) throws Exception;
	
	public void sendMail(String subject, String message) throws Exception;
	
	public void sendRegSuccessMail(UserInfo user) throws Exception;
}
