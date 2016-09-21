package com.slabs.expense.tracker.database.type;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedJdbcTypes(JdbcType.DATE)
public class XMLGregorianTypeHandler extends BaseTypeHandler<XMLGregorianCalendar> {

	private static final Logger L = LoggerFactory.getLogger(XMLGregorianTypeHandler.class);

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, XMLGregorianCalendar parameter, JdbcType jdbcType) throws SQLException {
		GregorianCalendar calendar = parameter.toGregorianCalendar();
		ps.setDate(i, new java.sql.Date(calendar.getTime().getTime()));
	}

	@Override
	public XMLGregorianCalendar getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Date date = rs.getDate(columnName);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		} catch (DatatypeConfigurationException e) {
			L.error("Exception Occurred, {}", e);
			throw new SQLException(e);
		}
	}

	@Override
	public XMLGregorianCalendar getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Date date = rs.getDate(columnIndex);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		} catch (DatatypeConfigurationException e) {
			L.error("Exception Occurred, {}", e);
			throw new SQLException(e);
		}
	}

	@Override
	public XMLGregorianCalendar getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Date date = cs.getDate(columnIndex);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		} catch (DatatypeConfigurationException e) {
			L.error("Exception Occurred, {}", e);
			throw new SQLException(e);
		}
	}

}
