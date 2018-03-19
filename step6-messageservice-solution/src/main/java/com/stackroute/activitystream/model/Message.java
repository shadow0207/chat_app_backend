package com.stackroute.activitystream.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 * The class "Message" will be acting as the data model for the message Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 * 
 * Please note that you will have to use @Component annotation on this class if wish
 * to autowire the class from any other components of the application
 */
@Component
@Entity
@Table(name = "Message")

public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int messageId;

	@Column
	private String senderName;
	@Column
	private String receiverId;
	@Column
	private String circleName;
	@Column
	private Timestamp postedDate;
	@Column
	private String streamType;
	@Column
	private String message;
	@Column
	private String tag;

	public Message(String senderName, String receiverId, String circleName, Timestamp postedDate, String streamType,
			String message, String tag) {
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.circleName = circleName;
		this.postedDate = postedDate;
		this.streamType = streamType;
		this.message = message;
		this.tag = tag;
		Date date = new java.util.Date();
		this.postedDate = new Timestamp(date.getTime());

	}

	public Message() {
		super();
	}

	public void setMessage(String string) {
		this.message = string;

	}

	public void setStreamType(String string) {
		this.streamType = string;

	}

	public void setSenderName(String string) {
		this.senderName = string;

	}

	public void setTag(String string) {
		this.tag = string;

	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public String getSenderName() {
		return senderName;
	}

	public Timestamp getPostedDate() {
		return postedDate;
	}

	public void setPostedDate() {
		Date date = new java.util.Date();
		this.postedDate = new Timestamp(date.getTime());

		
	}

	public String getStreamType() {
		return streamType;
	}

	public String getMessage() {
		return message;
	}

	public String getTag() {
		return tag;
	}

}
