package com.stackroute.activitystream.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.activitystream.model.Message;
import com.stackroute.activitystream.model.UserTag;

/*
* This class is implementing the JpaRepository interface for Message.
* */

public interface MessageRepository extends CrudRepository<Message, Integer>{

	@Query("FROM Message where circleName = (?1)")
	public List<Message> getMessagesFromCircle(String circleName);

	/*
	 * This method will retrieve all messages in database which are sent between two
	 * specific users specified in the method parameters. The messages should come
	 * ordered by postedDate in descending order
	 * 
	 * Write a query to retrieve all messages from the database send between two
	 * specified users.
	 */
	@Query("FROM Message where receiverId = (?1) or senderName =(?1) or receiverId = (?2) or senderName =(?2)")
	public List<Message> getMessagesFromUser(String username, String otherUsername);

	/*
	 * This method will retrieve all distinct tags available in all messages and
	 * write a query for the same.
	 * 
	 */
	@Query("select tag from UserTag")
	public List<String> listAllTags();

	/*
	 * This method will retrieve all tags which are subscribed by a specific user
	 * and write a query for the same.
	 * 
	 */
	@Query("select tag from UserTag where username = (?1)")
	public List<String> listMyTags(String username);

	/*
	 * This method will retrieve all messages containing tag matching which is
	 * matching the tag in method parameter among all messages and write a query for
	 * the same.
	 * 
	 */
	@Query("FROM Message where tag = (?1)")
	public List<Message> showMessagesWithTag(String tag);

	/*
	 * This method will retrieve an UserTag from UserTag table which matches the
	 * username and tag in parameter, write a query for the same.
	 */
	@Query("select u from UserTag u where u.username = (?1) and u.tag = (?2)")
	public UserTag getUserTag(String username, String tag);

}
