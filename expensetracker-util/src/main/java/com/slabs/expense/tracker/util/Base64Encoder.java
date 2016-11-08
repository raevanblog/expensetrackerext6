package com.slabs.expense.tracker.util;

import java.util.Base64;

/**
 * {@link Base64Encoder}
 * 
 * @author Shyam Natarajan
 *
 */
public class Base64Encoder {

	/**
	 * 
	 * @param value
	 *            {@link String} - value to encode
	 * @return {@link String} - Encoded String
	 */
	public static String encode(String value) {
		return Base64.getEncoder().encodeToString(value.getBytes());
	}

	/**
	 * 
	 * @param value
	 *            {@link String} - value to decode
	 * @return {@link String} - Decoded String
	 */
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

	/**
	 * 
	 * @param value
	 *            {@link String} - value to decode
	 * @param splitChar
	 *            {@link String} - Split char to split the decoded string
	 * @return {@link String} - Array of split string
	 */
	public static String[] decode(String value, String splitChar) {

		return decode(value).split(splitChar);
	}

}
