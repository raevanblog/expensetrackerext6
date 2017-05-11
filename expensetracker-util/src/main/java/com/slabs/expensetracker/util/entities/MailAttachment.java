package com.slabs.expensetracker.util.entities;

import java.util.Arrays;

/**
 * {@link MailAttachment} - entity used by Mailer utility to send attachments in
 * mail
 * 
 * @author Shyam Natarajan
 *
 */
public class MailAttachment {

	private String attachmentType;

	private String attachmentName;

	private byte[] attachment;

	private String description;

	public MailAttachment() {
		super();
	}

	public MailAttachment(String attachmentType, String attachmentName, byte[] attachment, String description) {
		super();
		this.attachmentType = attachmentType;
		this.attachmentName = attachmentName;
		this.attachment = attachment;
		this.description = description;
	}

	/**
	 * 
	 * @return {@link String}
	 */
	public String getAttachmentType() {
		return attachmentType;
	}

	/**
	 * Attachment type. For example, PDF - 'application/pdf'
	 * 
	 * @param attachmentType
	 */
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	/**
	 * 
	 * @return {@link byte[]}
	 */
	public byte[] getAttachment() {
		return attachment;
	}

	/**
	 * Attachment
	 * 
	 * @param attachment
	 */
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	/**
	 * Attachment name
	 * 
	 * @return {@link String}
	 */
	public String getAttachmentName() {
		return attachmentName;
	}

	/**
	 * Attachment name
	 * 
	 * @param attachmentName
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	/**
	 * Description of attachment
	 * 
	 * @return {@link String}
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Description of attachment
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(attachment);
		result = prime * result + ((attachmentName == null) ? 0 : attachmentName.hashCode());
		result = prime * result + ((attachmentType == null) ? 0 : attachmentType.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailAttachment other = (MailAttachment) obj;
		if (!Arrays.equals(attachment, other.attachment))
			return false;
		if (attachmentName == null) {
			if (other.attachmentName != null)
				return false;
		} else if (!attachmentName.equals(other.attachmentName))
			return false;
		if (attachmentType == null) {
			if (other.attachmentType != null)
				return false;
		} else if (!attachmentType.equals(other.attachmentType))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

}
