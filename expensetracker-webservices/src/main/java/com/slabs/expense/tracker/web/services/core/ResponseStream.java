package com.slabs.expense.tracker.web.services.core;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * {@link ResponseStream}
 * 
 * @author Shyam Natarajan
 *
 */
public class ResponseStream implements StreamingOutput {

	private static final Logger L = LoggerFactory.getLogger(ResponseStream.class);

	private JasperReportBuilder builder;

	public ResponseStream(JasperReportBuilder builder) {
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
