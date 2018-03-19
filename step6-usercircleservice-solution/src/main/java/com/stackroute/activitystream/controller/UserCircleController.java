package com.stackroute.activitystream.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.model.UserCircle;
import com.stackroute.activitystream.service.UserCircleService;
import com.stackroute.activitystream.service.UserCircleServiceImpl;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
public class UserCircleController {

	@Autowired
	UserCircleService userCircleDAO;

	@Autowired
	UserCircle userCircle;
	/*
	 * Autowiring should be implemented for the UserCircleService, UserCircle.
	 * Please note that we should not create any object using the new keyword
	 */

	@PutMapping("/api/usercircle/addToCircle/{username}/{circleName}")
	public ResponseEntity<UserCircle> createUser(HttpSession session, @PathVariable("username") String username,
			@PathVariable("circleName") String circleName) {

		if (userCircleDAO.get(username, circleName) != null) {
			return new ResponseEntity<UserCircle>(HttpStatus.CONFLICT);
		}
		try {
			boolean result = userCircleDAO.addUser(username, circleName);
			if (!result)
				throw new Exception();
			UserCircle userCircle1 = new UserCircle();
			userCircle1.setCircleName(circleName);
			userCircle1.setUsername(username);
			return new ResponseEntity<UserCircle>(userCircle1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UserCircle>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/api/usercircle/removeFromCircle/{username}/{circleName}")
	public ResponseEntity<UserCircle> removeCircle(HttpSession session, @PathVariable("username") String username,
			@PathVariable("circleName") String circleName) {
		try {

			boolean result = userCircleDAO.removeUser(username, circleName);
			if (!result)
				throw new Exception();
			return new ResponseEntity<UserCircle>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UserCircle>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Define a handler method which will get us the subscribed circles by a user.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the user is added to the circle 2.
	 * 401(UNAUTHORIZED) - If the user is not logged in
	 * 
	 * This handler method should map to the URL
	 * "/api/usercircle/searchByUser/{username}" using HTTP GET method where
	 * "username" should be replaced by a valid username without {}
	 */
	@GetMapping("/api/usercircle/searchByUser/{username}")
	public ResponseEntity<List<String>> getAllUserCircle(HttpSession session,
			@PathVariable("username") String username) {

		List<String> userCircles = userCircleDAO.getMyCircles(username);

		return new ResponseEntity<List<String>>(userCircles, HttpStatus.OK);
	}

	/*
	 * Define a handler method which will add a user to a circle.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the user is added to the circle 2.
	 * 500(INTERNAL SERVER ERROR) - If there are any errors 3. 401(UNAUTHORIZED) -
	 * If the user is not logged in 4. 404(NOT FOUND) - if the username or the
	 * circle does not exist 5. 409(CONFLICT) - if the user is already added to the
	 * circle
	 * 
	 * This handler method should map to the URL
	 * "/api/usercircle/addToCircle/{username}/{circleName}" using HTTP PUT method"
	 * where "username" should be replaced by a valid username without {} and
	 * "circleName" should be replaced by a valid circle name without {}
	 */

	/*
	 * Define a handler method which will remove a user from a circle.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the user is remove from the circle 2.
	 * 500(INTERNAL SERVER ERROR) - If there are any errors 3. 401(UNAUTHORIZED) -
	 * If the user is not logged in
	 * 
	 * This handler method should map to the URL
	 * "/api/usercircle/removeFromCircle/{username}/{circleName}" using HTTP PUT
	 * method" where "username" should be replaced by a valid username without {}
	 * and "circleName" should be replaced by a valid circle name without {}
	 */

	/*
	 * Define a handler method which will get us the subscribed circles by a user.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the user is added to the circle 2.
	 * 401(UNAUTHORIZED) - If the user is not logged in
	 * 
	 * This handler method should map to the URL
	 * "/api/usercircle/searchByUser/{username}" using HTTP GET method where
	 * "username" should be replaced by a valid username without {}
	 */

}