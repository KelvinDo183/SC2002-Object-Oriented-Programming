package model_Classes;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Transaction implements Serializable {

	private String TID;
	private String cinemaCode;
	private String name;
	private String email;
	private String mobileNumber;
	private Session session;
	private ArrayList<Integer> seatsSelected;
	private Double totalPrice;
	
	public Transaction(String TID, String cinemaCode, String name, String email, String mobileNumber, Session session, ArrayList<Integer> seatsSelected, Double totalPrice) {

		this.TID = TID;
		this.cinemaCode = cinemaCode;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.session = session;
		this.seatsSelected = seatsSelected;
		this.totalPrice = totalPrice;
		
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
	
	
	public Session getSession() {
		return this.session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	
	public ArrayList<Integer> getSeatsSelected() {
		return this.seatsSelected;
	}
	
	public void setSeatsSelected(ArrayList<Integer> seatsSelected) {
		this.seatsSelected = seatsSelected;
	}
	
	public Double getTotalPrice() {
		return this.totalPrice;
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String toStringTransaction() {
        String returnString = "\n";
        returnString += "TransactionID = " + this.getTID()
        			+ "\nCustomer Name = " + this.getName()
        			+ "\nCustomer Mobile Number = " + this.getMobileNumber()
        			+ "\nCinemaCode = " + this.getCinemaCode()
        			+ "\nScreening Date Time = " + this.getSession().getStringSessionDateTime()
        			+ "\nMovie Duration (minutes) = " + this.getSession().getMovie().getDuration()
        			+ "\nMovie Title = " + this.getSession().getMovie().getTitle()
        			+ "\nTotal Price Paid (SGD) = " + this.getTotalPrice()
        			+ "\nSelected Seats: " ;
		
        for (int j = 0; j < this.getSeatsSelected().size(); j++)
        {
        	returnString += "\n--> Seat Number = " + this.getSeatsSelected().get(j);      	
        }
        
        returnString += "\n::::::::::::::::::::::::::::::";
        
//		System.out.println("TransactionID = " + this.getTID());
//        System.out.println("Customer Name = " + this.getName());
//        System.out.println("Customer Mobile Number = " + this.getMobileNumber());
//        System.out.println("CinemaCode = " + this.getCinemaCode());
//        System.out.println("Screening Date Time = " + this.getSession().getStringSessionDateTime());
//        System.out.println("Movie Duration (minutes) = " + this.getSession().getMovie().getDuration());
//        System.out.println("Movie Title = " + this.getSession().getMovie().getTitle());
//        System.out.println("Total Price Paid (SGD) = " + this.getTotalPrice());
//        System.out.println("Selected Seats: " );
//        for (int j = 0; j < this.getSeatsSelected().size(); j++)
//        {
//        	System.out.println("--> Seat Number = " + this.getSeatsSelected().get(j));      	
//        }
//        System.out.println("\n::::::::::::::::::::::::::::::");

        return returnString;
	}

	

}