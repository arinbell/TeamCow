package com.teamcow.wheresmystuff;

import com.teamcow.wheresmystuff.model.LocalUser;
import com.teamcow.wheresmystuff.model.UserDatabase;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * addUser() tests.
 *
 * @author Tian Ze Qi
 * @version 1.0
 *
 * Method Contract:
 * Signature: The method name is addUser, parameter is an User object, and a boolean is
 *      returned stating whether the method added the user or not
 * Pre-conditions: The method can be called while registering
 * Post-conditions: The UserDatabase's private variable, userList, is modified if true is returned,
 *      otherwise nothing is modified.
 * Invariants: The UserDatabase's userList cannot hold duplicate User objects.
 * Framing Conditions: The UserDatabase's userList may be modified.
 */
public class addUserTests {
    private UserDatabase database;
    private ArrayList<LocalUser> expectedUserListNonDuplicate;
    private LocalUser user1;
    private LocalUser user2;
    private LocalUser user3;

    @Before
    public void setUp() {
        database = new UserDatabase();

        //user1 = new LocalUser("tony", "abc123");
        //user2 = new LocalUser("abdullah", "123abc");
        //user3 = new LocalUser("kashyap", "1a2b3c");
        expectedUserListNonDuplicate = new ArrayList<>();
        expectedUserListNonDuplicate.add(user1);
        expectedUserListNonDuplicate.add(user2);
        expectedUserListNonDuplicate.add(user3);
    }

    @Test
    public void testAddUser() {
        //Test for all users to be successfully added
        //assertTrue(database.addUser(user1));
        //assertTrue(database.addUser(user2));
        //assertTrue(database.addUser(user3));

        //Test for arrayList to be same as expected
        assertEquals(database.getUserList(), expectedUserListNonDuplicate);

        //Test for user3 to be unsuccessfully added
        //assertFalse(database.addUser(user3));
    }
}