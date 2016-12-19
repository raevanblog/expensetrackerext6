package com.slabs.expense.tracker.core;

import javax.ws.rs.core.MediaType;

/**
 * {@link ContentType}
 * 
 * @author Shyam Natarajan
 *
 */
public class ContentType extends MediaType {

	public static final String APPLICATION_WORD = "application/msword";

	public static final ContentType APPLICATION_WORD_TYPE = new ContentType("application",
			"msword");

	public static final String APPLICATION_PDF = "application/pdf";

	public static final ContentType APPLICATION_PDF_TYPE = new ContentType("application", "pdf");

	public static final String APPLICATION_GZIP = "application/x-gzip";

	public static final ContentType APPLICATION_GZIP_TYPE = new ContentType("application",
			"x-gzip");

	public static final String APPLICATION_JAVASCRIPT = "application/javascript";

	public static final ContentType APPLICATION_JAVASCRIPT_TYPE = new ContentType("application",
			"javascript");

	public static final String IMAGE_BMP = "image/bmp";

	public static final ContentType IMAGE_BMP_TYPE = new ContentType("image", "bmp");

	public static final String IMAGE_GIF = "image/gif";

	public static final ContentType IMAGE_GIF_TYPE = new ContentType("image", "gif");

	public static final String IMAGE_JPEG = "image/jpeg";

	public static final ContentType IMAGE_JPEG_TYPE = new ContentType("image", "jpeg");

	public static final String IMAGE_PNG = "image/png";

	public static final ContentType IMAGE_PNG_TYPE = new ContentType("image", "png");

	public static final String IMAGE_TIFF = "image/tiff";

	public static final ContentType IMAGE_TIFF_TYPE = new ContentType("image", "tiff");

	public ContentType(String type, String subtype) {
		super(type, subtype);
	}

}
