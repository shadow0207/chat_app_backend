package com.stackroute.activitystream.model;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;



@Component
@Entity
@Table(name = "User")
public class User {
	@Id
	@GenericGenerator(name = "seq", strategy = "com.stackroute.activitystream.utils.UserIDGenerator")
	@GeneratedValue(generator = "seq")  
	private String uid;
	@Column
	private String username;
	@Column
	private String fname;
	@Column
	private String lname;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String gender;
	@Column
	private Timestamp dob;
	@Column
	@Lob
	private byte[] image;
	@Column
	private Timestamp lastseen;
	@Column
	private String status;
	@Column
	private String description;
	@Column
	private String address;
	

	public User() {
	}


	public User(String uid, String username, String fname, String lname, String password, String email, String gender,
			Timestamp dob, byte[] image, Timestamp lastseen, String status, String description, String address) {
		super();
		this.uid = uid;
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
		this.image = image;
		this.lastseen = lastseen;
		this.status = status;
		this.description = description;
		this.address = address;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Timestamp getDob() {
		return dob;
	}


	public void setDob(Timestamp dob) {
		this.dob = dob;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public Timestamp getLastseen() {
		return lastseen;
	}


	public void setLastseen(Timestamp lastseen) {
		this.lastseen = lastseen;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	

}
