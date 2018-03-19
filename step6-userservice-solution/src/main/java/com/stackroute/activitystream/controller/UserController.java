package com.stackroute.activitystream.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.stackroute.activitystream.model.User;
import com.stackroute.activitystream.model.UserLogin;
import com.stackroute.activitystream.repository.UserRepository;
import com.stackroute.activitystream.service.UserService;
import com.stackroute.activitystream.service.UserServiceImpl;
import com.stackroute.activitystream.utils.JavaSHA;

/**
 * USER controller, contains all the functionalities related to user
 * 
 * @author SA356771
 *
 */
 @CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	/*
	 * Autowiring should be implemented for the UserService. Please note that we
	 * should not create any object using the new keyword
	 */
	// @Autowired
	// private JavaMailSender sender;
	@Autowired
	UserService userService;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	User user;

	/*
	 * @Autowired UserImage image;
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public HttpStatus exampleFileUpload(@RequestParam("image") MultipartFile file, HttpSession session) {
		if (session.getAttribute("username") != null) {
			if (!file.isEmpty()) {
				try {
					byte[] fileBytes = file.getBytes();
					System.err.println(fileBytes);
					// Persist those bytes using JPA here

					user.setImage(fileBytes);
					userService.save(user);
				} catch (IOException e) {
					// Exception handling here
				}
			}
			return HttpStatus.OK;
		}
		return HttpStatus.UNAUTHORIZED;

	}

	/*
	 * Define a handler method which will list all the available users. This handler
	 * method should return any one of the status messages basis on different
	 * situations: 1. 200(OK) - If login is successful
	 * 
	 * This handler method should map to the URL "/api/user" using HTTP GET method
	 */@PostMapping(value = "/api/user")
	public ResponseEntity<User> createUser(@RequestBody User user) throws IOException {
		File f = ResourceUtils.getFile("src/main/resources/download.jpg");
		BufferedImage image = ImageIO.read(new File("src/main/resources/download.jpg"));
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		ImageIO.write(image, "png", baos);
		byte[] res=baos.toByteArray();
		String encoded = Base64.getEncoder().encodeToString(res);
		System.out.println("The encoded image byte array is shown below.Please use this in your webpage image tag.\n"+encoded);  


		//System.err.println(Arrays.toString(res));
		if(f.exists()) { 
		    System.err.println("FILE FOUND");
		}
		System.err.println("THIS IS CALLED");
		
		if (userService.get(user.getUsername()) != null) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		
		//userService.mailSending(user, sender);
		user.setImage(res);
		userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@GetMapping(value = "/api/user")
	public ResponseEntity<List<User>> getAllUser(HttpSession session) {
	
		List<User> users = userService.list();
		System.err.println(session.getAttribute("username") + "----");
		// HttpSession session = request.getSession(false);

		/*
		 * if (session.getAttribute("username") == null) { return new
		 * ResponseEntity<List<User>>(HttpStatus.UNAUTHORIZED); }
		 */
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@GetMapping(value = "/api/user/{username}")
	public ResponseEntity<User> getUser(@PathVariable("username") String username, HttpSession session) {
		user = userService.get(username);
		/*
		 * if (session.getAttribute("username") == null) { return new
		 * ResponseEntity<User>(HttpStatus.UNAUTHORIZED); }
		 */
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping("/api/user/{username}")
	ResponseEntity<User> updateUser(@PathVariable("username") String username, @RequestBody User user,
			HttpSession session) {

		User user1 = userService.get(username);
		if (user1 == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		/*
		 * if (session.getAttribute("username") == null) { return new
		 * ResponseEntity<User>(HttpStatus.UNAUTHORIZED); }
		 */
		userService.update(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping("/api/user/{username}")
	ResponseEntity<User> deleteUser(@PathVariable("username") String username, HttpSession session) {
		User user = userService.get(username);
		if (user == null) {
			System.out.println("User with id " + username + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		if (session.getAttribute("username") == null) {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
		userService.delete(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/*
	 * Define a handler method which will show details of a specific user. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If login is successful 2. 404(NOT FOUND) -
	 * If the user with the searched for is not found This handler method should map
	 * to the URL "/api/user/{username}" using HTTP GET method where "username"
	 * should be replaced by a username without {}
	 */

	/*
	 * Define a handler method which will create a specific user by reading the
	 * Serialized object from request body and save the user details in user table
	 * in database. This handler method should return any one of the status messages
	 * basis on different situations: 1. 201 (CREATED) - If the user is successfully
	 * created 2. 409 (CONFLICT) - If the username conflicts with any existing user
	 * 
	 * Note: ------ This method can be called without being logged in as well as
	 * when a new user will use the app, he will register himself first before
	 * login. This handler method should map to the URL "/api/user" using HTTP POST
	 * method
	 */

	/*
	 * Define a handler method which will update an specific user by reading the
	 * Serialized object from request body and save the updated user details in user
	 * table in database. This handler method should return any one of the status
	 * messages basis on different situations: 1. 200(OK) - If the user is
	 * successfully updated 2. 404(NOT FOUND) - If the user with specified username
	 * is not found
	 * 
	 * This handler method should map to the URL "/api/user/{username}" using HTTP
	 * PUT method
	 */
	@PostMapping("/api/user/login")
	public void getCheck(@RequestBody User user, HttpSession session) {
		System.err.println("THIS API IS GETTING CALLED");
		System.err.println(session.getAttribute("username") + "----");
		if (userService.validate(user.getUsername(), user.getPassword())) {

			session.setAttribute("username", user.getUsername());
			System.err.println("LOGIN SUCCESSFUL!!!!" + user.getUsername());

		} else {
			session.invalidate();
			System.err.println("UNAUTH");
		}

	}

}
