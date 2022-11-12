package model_Classes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Transaction implements Serializable {

	private String TID;
	private String cinemaCode;
	private String name;
	private String email;
	private String mobileNumber;
	private Movie movie;
	
	public Transaction(String TID, String cinemaCode, String name, String email, String mobileNumber, Movie movie) {

		this.TID = TID;
		this.cinemaCode = cinemaCode;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.movie = movie;
		
	}
	
	public String getTID() {
		return this.TID;
	}
	
	public void setTID(String TID)
	{
		this.TID = TID;
	}
	
	public String getCinemaCode() {
		return this.cinemaCode;
	}
	
	public void setCinemaCode(String cinemaCode)
	{
		this.cinemaCode = cinemaCode;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getMobileNumber() {
		return this.mobileNumber;
	}
	
	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}
	
	
	
	
	public Movie getMovie() {
		return this.movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	

}