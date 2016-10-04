package com.slabs.expense.tracker.util;

import java.util.Base64;

public class Base64Encoder {

	public static String encode(String value) {
		return Base64.getEncoder().encodeToString(value.getBytes());
	}

	public static String decode(String value) {

		if (value == null) {
			return null;
		}

		byte[] decodedBytes = Base64.getDecoder().decode(value.getBytes());

		if (decodedBytes == null || decodedBytes.length == 0) {
			return null;
		}
		return new String(decodedBytes);
	}

	public static String[] decode(String value, String splitChar) {

		return decode(value).split(splitChar);
	}

}
