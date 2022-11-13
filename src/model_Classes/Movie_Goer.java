package model_Classes;

/**
 * Represents an MovieGoer with access to the system
 * An MovieGoer has some rights (BUY and VIEW)
 */
public class Movie_Goer extends User {

	/**
	 * Creates a Movie_Goer with the given attributes
	 * 
	 * @param username This Admin's username (email)
	 * @param password This Admin's password (unencrypted)
	 */
	public Movie_Goer(String email, String password, int role) {
		super(email, password, role);
	}

	/**
	 * This Movie_Goer's name
	 */
	private String name;

	/**
	 * This Movie_Goer's mobile number
	 */
	private String mobileNumber;

	/**
	 * Get the name of this Movie_Goer
	 * 
	 * @return String this Movie_Goer's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Change the name of this Movie_Goer
	 * 
	 * @param name this Movie_Goer's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the mobile number of this Movie_Goer
	 * 
	 * @return String this Movie_Goer's mobile number
	 */
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	/**
	 * Change the mobile number of this Movie_Goer
	 * 
	 * @param mobileNumber this Movie_Goer's new mobile number
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * String to return when this Movie_Goer is being called
	 * 
	 * @return String
	 */
	public String toString() {
		String returnString = "\n";
		returnString += "Movie Goer has name "
				+ this.getName()
				+ " and mobile number "
				+ this.getMobileNumber()
				+ "\n";
		return returnString;
	}

}