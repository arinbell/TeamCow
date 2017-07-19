package com.teamcow.wheresmystuff;

import com.teamcow.wheresmystuff.model.User;
import com.teamcow.wheresmystuff.model.UserDatabase;
import com.teamcow.wheresmystuff.model.UserType;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Method Contract:
 * Signature: matchUser(String id, String password)
 *	- method name: matchuser
 *  - parameter: String id, String password
 *  - return type: User
 * Preconditions: User id and password are inputted as parameters
 * Postconditions: Either a user or null is returned. nothing is modified
 * Invariants: Database exists.
 * Framing Conditions: Doesn't modify anything.
 */
public class LianMatchUserTest {
	@Test
	public void testMatchUser() throws Exception {
		User testUser = new User("lian", "ilovecs", UserType.USER);
		UserDatabase uDB = new UserDatabase();
		uDB.addUser(testUser);

		//test if method returns testUser when user exists
		Assert.assertEquals(testUser, uDB.matchUser("lian", "ilovecs"));

		//test if method returns null when user does not exist
		Assert.assertEquals(null, uDB.matchUser("bob", "waters"));

		//test to see if id AND pass are checked
		Assert.assertEquals(null, uDB.matchUser("lian", "fakepassword"));
		Assert.assertEquals(null, uDB.matchUser("notlian", "ilovecs"));
	}
}


