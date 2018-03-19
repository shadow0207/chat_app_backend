package com.stackroute.activitystream.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
/*
 * The class "Circle" will be acting as the data model for the circle Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 * 
 * Please note that you will have to use @Component annotation on this class if wish
 * to autowire the class from any other components of the application
 */
@Component
@Entity
@Table(name = "Circle")
public class Circle {
	@Id
	private String circleName;

	@Column
	private String creatorId;
	@Column
	private Timestamp createdDate;

	/*
	 * This class should have three fields (circleName,creatorId,createdDate). Out
	 * of these three fields, the field circleName should be the primary key. This
	 * class should also contain the getters and setters for the fields. The value
	 * of createdDate should not be accepted from the user but should be always
	 * initialized with the system date
	 */
	public Circle() {

	}

	public Circle(String circleName, String creatorId, Timestamp createdDate) {

		this.circleName = circleName;
		this.creatorId = creatorId;
		Date date = new java.util.Date();
		this.createdDate = new Timestamp(date.getTime());

	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate() {
		Date date = new java.util.Date();
		this.createdDate = new Timestamp(date.getTime());
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public String getCircleName() {
		return circleName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getCircleName() + " " + getCreatorId();
	}
}