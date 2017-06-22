package com.teamcow.wheresmystuff;

/**
 * Created by arinb on 6/22/2017.
 */

public class User {
    private String userID;
    private String password;
    private UserType userType;

    /**
     * Constructor
     *
     * Creates new user with provided ID, password and user type
     *
     * @param id User ID of the new user
     * @param pass Password of the new user
     * @param userT User type of the new user
     */
    public User(String id, String pass, UserType userT) {
        this.userID = id;
        this.password = pass;
        this.userType = userT;
    }

    /**
     * Constructor
     *
     * If only ID and password is provided, regular user is
     * created by default.
     *
     * @param id User ID of the new user
     * @param pass Password of the new user
     */
    public User(String id, String pass) {
        this(id, pass, UserType.USER);
    }

    /**
     * Default Constructor
     *
     * If no parameter is passed in, a new anonymous user is
     * created. (Visitor account)
     */
    public User() {
        this("anonymous", "password", UserType.ANONYMOUS);
    }

    /**
     * Returns user ID of this user
     *
     * @return This user's user ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Checks if passed in string matches the password of this user
     *
     * @param pass String to be checked against this user's password
     * @return Whether string matches or not
     */
    public boolean matchPassword(String pass) {
        return (pass.equals(password));
    }

    /**
     * Checks if passed in string matches the user ID of this user
     *
     * @param id String to be checked against this user's ID
     * @return Whether string matches or not
     */
    public boolean matchUserID(String id) {
        return (id.equals(userID));
    }

    /**
     * Returns the user type of this user
     *
     * @return User type of this user
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Set new password for the user.
     * Does not check for password validity yet
     * (Does not check for empty string)
     *
     * @param newPass new password to be set
     */
    public void setPassword(String newPass) {
        password = newPass;
    }

    /**
     * Sets the user type for this user.
     * User object of the user attempting to change user type is
     * passed in as parameter, and checked if the said user is
     * admin. If not, method returns false, and if the user is
     * admin, sets the user type of this user and returns true.
     *
     * @param admin User attempting to set user type of this user
     * @param newUT UserType being set for this user
     * @return Whether user type was successfully set or not
     */
    public boolean setUserType(User admin, UserType newUT) {
        if (admin.getUserType().equals(UserType.ADMIN)) {
            userType = newUT;
            return true;
        } else {
            return false;
        }
    }
}
