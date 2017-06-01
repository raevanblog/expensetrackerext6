package com.slabs.expensetracker.core.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

public class ReportingScheduler {

	private static final Logger L = LoggerFactory.getLogger(ReportingScheduler.class);

	private volatile static ReportingScheduler scheduler;

	private Scheduler quartzScheduler;

	private ReportingScheduler() {

	}

	public static ReportingScheduler getInstance() {
		if (scheduler == null) {
			synchronized (ReportingScheduler.class) {
				if (scheduler == null) {
					scheduler = new ReportingScheduler();
				}
			}
		}
		return scheduler;
	}

	public ReportingScheduler initialize() throws ExpenseTrackerException {

		try {
			L.info("Initializing Reporting Scheduler...");
			quartzScheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			throw new ExpenseTrackerException(e);
		}
		return this;
	}

	public ReportingScheduler startScheduler() throws ExpenseTrackerException {
		try {

			if (quartzScheduler == null) {
				throw new ExpenseTrackerException("Reporting Schedular is not initializedF");
			}
			L.info("Starting Reporting scheduler...");
			quartzScheduler.start();
		} catch (SchedulerException e) {
			throw new ExpenseTrackerException(e);
		}
		return this;
	}

	public void scheduleMonthlyReportDispatcher() throws ExpenseTrackerException {
		try {
			L.info("Scheduling Monthly Report Dispatcher...");
			JobDetail job = JobBuilder.newJob(MonthlyReportDispatcher.class).withIdentity("MonthlyReport", "ReportingJobs").build();
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity("MonthlyReportTrigger", "ReportingJobs");
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 1 * ?");
			CronTrigger trigger = triggerBuilder.startNow().withSchedule(scheduleBuilder).build();
			quartzScheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			throw new ExpenseTrackerException(e);
		}
	}

	public ReportingScheduler stopScheduler() throws ExpenseTrackerException {
		try {

			if (quartzScheduler == null) {
				throw new ExpenseTrackerException("Reporting Schedular is not initializedF");
			}

			quartzScheduler.shutdown();
		} catch (SchedulerException e) {
			throw new ExpenseTrackerException(e);
		}
		return this;
	}

}
