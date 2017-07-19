package com.teamcow.wheresmystuff;

import com.teamcow.wheresmystuff.model.User;
import com.teamcow.wheresmystuff.model.UserDatabase;
import com.teamcow.wheresmystuff.model.UserType;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Method Contract:
 * Signature: Method name is checkDuplicate, parameter is a String userID, a boolean is returned
 * Preconditions: Method may be called at any time
 * Postconditions: Nothing is modified by the calling of this method
 * Invariants: None
 * Framing Conditions: Nothing is modified
 */
public class checkDuplicateUser {
    
    @Test
    public void checkDuplicate() throws Exception {
        User user = new User("user", "pass", UserType.USER);
        User seconds = new User("bob", "puff", UserType.ADMIN);
        UserDatabase dup = new UserDatabase();
        boolean output = dup.checkDuplicate("user");

        //Test for when the UserDatabase is empty
        Assert.assertEquals(false, output);

        //Test for when there is already a user with the same name(duplicate)
        dup.addUser(user);
        output = dup.checkDuplicate("user");
        Assert.assertEquals(true, output);

        //Test for when there are no duplicates
        dup.addUser(seconds);
        output = dup.checkDuplicate("chris");
        Assert.assertEquals(false, output);
    }
}
