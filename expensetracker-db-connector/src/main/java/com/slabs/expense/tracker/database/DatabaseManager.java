package com.slabs.expense.tracker.core.db;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseManager {

	private static final Logger L = LoggerFactory.getLogger(DatabaseManager.class);

	private static final String DB_CONFIG = "db-config.xml";

	private static SqlSessionFactory factory;

	public static void initialize() throws IOException {
		SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
		DatabaseManager.factory = factoryBuilder.build(Resources.getResourceAsStream(DB_CONFIG));
	}

	public static SqlSession openSession() {
		L.debug("Opening session...");
		return DatabaseManager.factory.openSession();
	}

}
