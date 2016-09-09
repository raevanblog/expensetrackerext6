package com.slabs.expense.tracker.core.services;

import java.util.List;
import java.util.Map;

public interface BaseService<T extends Object> {

	public List<T> select(Map<String, String> parameters) throws Exception;
}
