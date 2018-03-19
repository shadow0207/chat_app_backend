package com.stackroute.activitystream.service;

import java.io.File;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.stackroute.activitystream.model.User;
import com.stackroute.activitystream.repository.UserRepository;
import com.stackroute.activitystream.utils.JavaSHA;
import com.stackroute.activitystream.utils.UIDGenerator;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn�t currently 
* provide any additional behavior over the @Component annotation, but it�s a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class UserServiceImpl implements UserService {

	

	@Autowired
	UserRepository userRepository;
	JavaSHA encryptProcessor = JavaSHA.getInstance();
	
	/*
	 * This method should be used to save a new user. Call the corresponding method
	 * of Respository interface.
	 * 
	 */
	public boolean save(User user) {
		
		try {
			/**
			 * SHA1 encryption
			 */
			user.setPassword(encryptProcessor.encryptPassword(user.getPassword()));
			
			userRepository.save(user);
			/**
			 * Need modification
			 */
			//user.setUid(uidGenerator.generateUID(user.getFname(), user.getLname()));
			System.err.println("DATA SAVED");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * This method should be used to update an existing user. Call the corresponding
	 * method of Respository interface.
	 * 
	 */
	public boolean update(User user) {
		try {
			userRepository.delete(userRepository.findOne(user.getUsername()));
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * This method should be used to delete an existing user. Call the corresponding
	 * method of Respository interface.
	 * 
	 */
	public boolean delete(User user) {
		try {
			userRepository.delete(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * This method should be used to list all users. Call the corresponding method
	 * of Respository interface.
	 * 
	 */
	public List<User> list() {
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}

	/*
	 * This method should be used to validate a user using password. Call the
	 * corresponding method of Respository interface.
	 * 
	 */
	public boolean validate(String username, String password) {
		password = encryptProcessor.encryptPassword(password);
		try {
			User user = userRepository.validate(username, password);
			if (user != null)
				return true;
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * This method should be used to get a user by username. Call the corresponding
	 * method of Respository interface.
	 */
	public User get(String username) {
		User user = userRepository.findOne(username);
		return user;
	}

	/*
	 * This method is used to check whether a user with a specific username exists.
	 * Call the corresponding method of Respository interface.
	 */
	public boolean exists(String username) {
		if (get(username) != null)
			return true;
		return false;
	}

//	@Override
//	public boolean mailSending(User user,JavaMailSender sender) {
//		// TODO Auto-generated method stub
//		MimeMessage message = sender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//
//		try {
//			helper.setTo(user.getEmail());
//			helper.setText("Hi, Your password is"+user.getPassword()+" this is a test mail. Please do not reply!!!");
//			helper.setSubject("OUR CHAT APPLICATION");
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		sender.send(message);
//		return false;
//	}

}
