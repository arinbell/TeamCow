package com.teamcow.wheresmystuff;

import com.teamcow.wheresmystuff.model.User;
import com.teamcow.wheresmystuff.model.UserDatabase;
import com.teamcow.wheresmystuff.model.UserType;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Method Contract:
 * Signature: setUserType(User admin, UserType newUT)
 *	- method name: setUserType
 *  - parameter: User admin, UserType newUT
 *  - return type: boolean
 * Preconditions: User object and UserType is inputted through parameters
 * Postconditions: Boolean whether operation was successful or not was returned
 *                 Current User is updated to UserType passed if User object
 *                 passed is an Admin User
 * Invariants: User exists.
 * Framing Conditions: Only modify if passed User is admin.
 */
public class choiTests {
    @Test
    public void testSetUserType() throws Exception {
        //Set up users for test
        User user1 = new User("hello", "password", UserType.ADMIN);
        User user2 = new User("mod", "password", UserType.USER);

        //Attempt to run method
        user2.setUserType(user1, UserType.MOD);

        //Check if output is correct
        Assert.assertEquals(user2.getUserType().toString(), UserType.MOD.toString());

        //Attempt a failure case
        user1.setUserType(user2, UserType.MOD);
        Assert.assertEquals(user1.getUserType().toString(), UserType.ADMIN.toString());
    }
}


