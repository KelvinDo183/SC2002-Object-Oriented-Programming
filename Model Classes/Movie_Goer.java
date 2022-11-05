public class Movie_Goer extends User {

	private String name;
	private String mobileNumber;

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	/**
	 * 
	 * @param mobileNumber
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String toString() {
		// TODO - implement Movie_Goer.toString
		throw new UnsupportedOperationException();
	}

}