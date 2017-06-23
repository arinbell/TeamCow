package com.teamcow.wheresmystuff.model;

/**
 * Created by arinb on 6/22/2017.
 */

public class User {
    private String userID;
    private String password;
    private UserType userType;
    private String zipCode;
    private String city;
    private String phoneNumber;
    private String hashToken;

    /**
     * Constructor
     *
     * Creates new user with provided user data
     * Hash token is a unique identifiable ID for each users
     * Not implemented yet, and is to be used for Firebase integration
     *
     * @param id User ID of the new user
     * @param pass Password of the new user
     * @param userT User type of the new user
     * @param zipCode Zip code for the new user
     * @param city City for the new user
     * @param phoneNumber Phone number for new user
     * @param hashToken Unique identifiable ID for user, used for Firebase
     */
    public User(String id, String pass, UserType userT, String zipCode,
                String city, String phoneNumber, String hashToken) {
        this.userID = id;
        this.password = pass;
        this.userType = userT;
        this.zipCode = zipCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.hashToken = hashToken;
    }

    /**
     * Constructor
     *
     * Creates new user with provided user data
     * Does not included hash token
     *
     * @param id User ID of the new user
     * @param pass Password of the new user
     * @param userT User type of the new user
     * @param zipCode Zip code for the new user
     * @param city City for the new user
     * @param phoneNumber Phone number for new user
     */
    public User(String id, String pass, UserType userT, String zipCode,
                String city, String phoneNumber) {
        this(id, pass, userT, zipCode, city, phoneNumber, "");
    }

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
        this(id, pass, userT, "", "", "");
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
     * Returns zip code for this user
     *
     * @return This user's zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets new zip code for this user
     *
     * @param newZip New zip code to be set for this user
     */
    public void setZipCode(String newZip) {
        zipCode = newZip;
    }

    /**
     * Returns this user's city
     *
     * @return This user's city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets new city for this user
     *
     * @param newCity New city to be set for this user
     */
    public void setCity(String newCity) {
        city = newCity;
    }

    /**
     * Returns this user's phone number
     *
     * @return This user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets new phone number for this user
     *
     * @param newNumber New phone number to be set for this user
     */
    public void setPhoneNumber(String newNumber) {
        phoneNumber = newNumber;
    }

    /**
     * Returns hash token for this user
     *
     * Not used at the moment
     *
     * @return This user's hash token
     */
    public String getHashToken() {
        return hashToken;
    }

    /**
     * Sets new hash token for this user
     *
     * Not used at the moment
     *
     * @param newHash New hash token to be set for this user
     */
    public void setHashToken(String newHash) {
        hashToken = newHash;
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

    /**
     * Upgrades anonymous user(visitor) to regular user with provided
     * user ID and password.
     *
     * @param newID ID to be set for this user
     * @param newPass Password to be set for this user
     */
    public void upgradeUser(String newID, String newPass) {
        userID = newID;
        password = newPass;
        userType = UserType.USER;
    }
}
