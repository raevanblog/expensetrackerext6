package com.slabs.expense.tracker.core.scheduler;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.common.database.mapper.UserDAO;
import com.slabs.expense.tracker.core.ServiceFactory;

@Service
public class MonthlyReportDispatcher implements Job {

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		UserDAO userDao = ServiceFactory.getInstance().getBean("UserDAO", UserDAO.class);
		List<UserInfo> users = userDao.getUser(null, Boolean.FALSE);
		System.out.println(users);

	}

}
