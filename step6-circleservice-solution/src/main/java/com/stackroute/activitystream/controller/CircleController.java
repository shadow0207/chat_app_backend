package com.stackroute.activitystream.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.model.Circle;
import com.stackroute.activitystream.service.CircleService;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */

@RestController("/api/circle")
public class CircleController {
	@Autowired
	CircleService circleServices;
	@Autowired
	Circle circle;
	
	@PostMapping
	public ResponseEntity<Circle> createUser(@RequestBody Circle circle, HttpSession session) {

		/*if (session.getAttribute("username") == null) {
			return new ResponseEntity<Circle>(HttpStatus.UNAUTHORIZED);
		}*/
		if (circleServices.get(circle.getCircleName()) != null) {
			return new ResponseEntity<Circle>(HttpStatus.CONFLICT);
		}
	//	circle.setCreatorId(((User) session.getAttribute("username")).getUsername());
		circle.setCreatedDate();
		System.out.println(circleServices.save(circle));

		return new ResponseEntity<Circle>(circle, HttpStatus.CREATED);
	}

	@GetMapping("/api/circle/search")
	public ResponseEntity<Circle> getCircle(HttpSession session) {

		Circle circles = circleServices.get("spring");

		return new ResponseEntity<Circle>(circles, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Circle>> getAllCircle(HttpSession session) {

	/*	if (session.getAttribute("username") == null) {
			return new ResponseEntity<List<Circle>>(HttpStatus.UNAUTHORIZED);
		}*/
		List<Circle> circles = circleServices.getAllCircles();

		return new ResponseEntity<List<Circle>>(circles, HttpStatus.OK);
	}

	@GetMapping("/api/circle/search/{searchString}")
	public ResponseEntity<List<Circle>> getCircleByName(@PathVariable("searchString") String searchString,
			HttpSession session) {
	/*	if (session.getAttribute("username") == null) {
			return new ResponseEntity<List<Circle>>(HttpStatus.UNAUTHORIZED);
		}*/
		List<Circle> circles = circleServices.getAllCircles(searchString);
		System.out.println(circles.size());
		return new ResponseEntity<List<Circle>>(circles, HttpStatus.OK);
	}
	/*
	 * From the problem statement, we can understand that the application	
	 * requires us to implement two functionalities regarding circles. They are as following:
	 * 
	 * 1. Create a circle 
	 * 2. Get all circles
	 * 3. Get all circles which is matching a keyword
	 * 
	 * we must also ensure that only a user who is logged in should be able to perform the
	 * functionalities mentioned above.
	 * 
	 */
	
	
	
	/*
	 * Autowiring should be implemented for the CircleService. Please note that 
	 * we should not create any object using the new keyword
	 * */
	
	
	
	/* Define a handler method which will create a circle by reading the Serialized circle
	 * object from request body and save the circle in message table in database. Please 
	 * note that the circleName has to be unique and the loggedIn userID should be taken as
	 * the creatorId for the circle. 
	 * This handler method should return any one of the status messages basis on different
	 * situations:
	 * 1. 201(CREATED - In case of successful creation of the circle
	 * 2. 209(CONFLICT) - In case of duplicate circle ID
	 * 
	 * This handler method should map to the URL "/api/circle" using HTTP POST method". 
	*/
	

	
	/* Define a handler method which will retrieve all the available circles.  
	 * This handler method should return any one of the status messages basis on different
	 * situations:
	 * 1. 200(OK) - In case of success
	 * 
	 * This handler method should map to the URL "/api/circle" using HTTP GET method". 
	*/
	
	

	/* Define a handler method which will retrieve all the available circles matching a search keyword.  
	 * This handler method should return any one of the status messages basis on different
	 * situations:
	 * 1. 200(OK) - In case of success
	 * 
	 * This handler method should map to the URL "/api/circle/search/{searchString}" using HTTP GET method" where 
	 * "searchString" should be replaced with the actual search keyword without the {}
	*/
	

}
