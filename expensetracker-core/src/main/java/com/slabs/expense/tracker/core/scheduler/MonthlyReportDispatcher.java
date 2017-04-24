package com.slabs.expense.tracker.core.scheduler;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.common.constants.Constants;
import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.common.database.entity.UserSettings;
import com.slabs.expense.tracker.common.database.mapper.UserDAO;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.reports.Month;
import com.slabs.expense.tracker.reports.service.ReportingService;
import com.slabs.expense.tracker.util.Mailer;
import com.slabs.expense.tracker.util.entities.MailAttachment;
import com.slabs.expense.tracker.util.exception.UtilityException;

import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

public class MonthlyReportDispatcher implements Job {

	private static final Logger L = LoggerFactory.getLogger(MonthlyReportDispatcher.class);

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		try {
			UserDAO userDAO = ServiceFactory.getInstance().getBean("UserDAO", UserDAO.class);
			ReportingService service = ServiceFactory.getInstance().getService(Services.REPORTING_SERVICE, ReportingService.class);
			Calendar calendar = Calendar.getInstance();

			List<UserInfo> users = userDAO.getUser(null, Boolean.FALSE);

			for (UserInfo user : users) {
				String username = user.getUsername();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				String fileName = getAttachmentName(username, Month.getMonth(month).getName(), String.valueOf(year));

				UserSettings settings = userDAO.getUserSettings(username);

				JasperConcatenatedReportBuilder report = service.generateMonthlyExpenseReport(username, year, month,
						settings.getCurrency().getCurrtxt());				
				MailAttachment mailAttachment = new MailAttachment("application/pdf", fileName,  getAttachmentContent(report), "Monthly Expense Report");
				
				L.info("Dispacthing monthly report to {} : {}", user.getUsername(), user.getEmail());
				dispatchReport("Your Monthly Expense Report", "Monthly Expense Report", mailAttachment, user.getEmail());
			}

		} catch (Exception e) {

			L.error("Exception occurred while dispatching monthly report, {}", e);
			throw new JobExecutionException(e);

		}
	}

	private void dispatchReport(String mailContent, String subject, MailAttachment attachment, String toEmail) throws UtilityException, EmailException {
		Email email = Mailer.createMultiPartEmail(mailContent, subject, attachment,toEmail);
		email.send();
	}

	private String getAttachmentName(String username, String month, String year) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(username).append(Constants.UNDERSCORE);
		buffer.append(month).append(Constants.UNDERSCORE);
		buffer.append(year).append(".pdf");
		return buffer.toString();
	}

	private byte[] getAttachmentContent(JasperConcatenatedReportBuilder report) throws DRException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		report.toPdf(output);
		return output.toByteArray();
	}

}