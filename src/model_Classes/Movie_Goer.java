package model_Classes;

public class Movie_Goer extends User {

	public Movie_Goer(String email, String password, int role) {
		super(email, password, role);
	}

	private String name;
	private String mobileNumber;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String toString() {
		// TODO - implement Movie_Goer.toString
		throw new UnsupportedOperationException();
	}

}