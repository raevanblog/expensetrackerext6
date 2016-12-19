package com.slabs.expense.tracker.core.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.constants.Constants;
import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.database.mapper.UserDAO;
import com.slabs.expense.tracker.util.Mailer;
import com.slabs.expense.tracker.util.MarkerEngine;
import com.slabs.expense.tracker.util.RandomGenerator;
import com.slabs.expense.tracker.util.URLUtil;

/**
 * {@link EmailService}
 * 
 * @author Shyam Natarajan
 *
 */
@Service(value = "EmailService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class EmailService {

	@Autowired
	private UserDAO userDao;

	public void sendActivationEmail(UserInfo user) throws Exception {

		String activationKey = RandomGenerator
				.generateRandomText(RandomGenerator.DEFAULT_RANDOM_TEXT, 25);
		userDao.setActivationKey(user.getUsername(), activationKey);

		String activationUrl = URLUtil.getAppUrl() + "#activate?vcode=" + activationKey;

		Map<String, String> model = new HashMap<String, String>();
		model.put("fname", user.getFirstName());
		model.put("lname", user.getLastName());
		model.put("activationurl", activationUrl);

		String message = MarkerEngine.process(Constants.EMAIL_ACTIVATION_TEMPLATE, model);
		Email email = Mailer.createHtmlEmail(message, Constants.EMAIL_ACTIVATION_SUBJECT,
				user.getEmail());
		email.send();

	}

	public void sendMail(String subject, String message) throws Exception {
		Email email = Mailer.createHtmlEmail(message, subject);
		email.send();
	}

	public void sendRegSuccessMail(UserInfo user) throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		model.put("fname", user.getFirstName());
		model.put("lname", user.getLastName());

		String message = MarkerEngine.process(Constants.REG_SUCCESS_TEMPLATE, model);
		Email email = Mailer.createHtmlEmail(message, Constants.EMAIL_ACTIVATION_SUCCESS_SUBJECT,
				user.getEmail());
		email.send();
	}

}
