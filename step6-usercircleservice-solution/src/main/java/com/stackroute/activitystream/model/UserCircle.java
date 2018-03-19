package com.stackroute.activitystream.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/*
 * The class "UserCircle" will be acting as the data model for the user_circle Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 *
 * Please note that you will have to use @Component annotation on this class if wish
 * to autowire the class from any other components of the application
 */
@Component
@Entity
@Table(name = "UserCircle")
public class UserCircle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userCircleId;
	@Column
	private String username;
	@Column
	private String circleName;

	/*
	 * This class should have three fields (userCircleId,username,circleName). Out
	 * of these three fields, the field userCircleId should be the primary key and
	 * should be generated. This class should also contain the getters and setters
	 * for the fields.
	 */
	public UserCircle() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public int getUserCircleId() {
		return userCircleId;
	}

	public UserCircle(String username, String circleName) {
		this.username = username;
		this.circleName = circleName;
	}

}
