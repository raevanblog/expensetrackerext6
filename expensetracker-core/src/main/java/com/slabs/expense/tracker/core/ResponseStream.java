package com.slabs.expense.tracker.core;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

public class ReportResponse implements StreamingOutput {

	private static final Logger L = LoggerFactory.getLogger(ReportResponse.class);

	private JasperReportBuilder builder;

	public ReportResponse(JasperReportBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void write(OutputStream output) throws IOException, WebApplicationException {
		try {
			builder.toPdf(output);
			output.flush();
		} catch (DRException e) {
			L.error("Exception occurred while writing report, {}", e);
			throw new IOException(e);
		}

	}

}
