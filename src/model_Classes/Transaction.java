package model_Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a Transaction (or Booking)
 */
@SuppressWarnings("serial")
public class Transaction implements Serializable {

	/**
	 * Transaction ID of the Transaction
	 * In the format of XXXYYYYMMDDhhmm
	 * XXX is the cinema code
	 * YYYYDDMMhhmm is datetime
	 */
	private String TID;

	/**
	 * Cinema Code of MovieGoer who made the Transaction
	 */
	private String cinemaCode;

	/**
	 * Name of MovieGoer who made the Transaction
	 */
	private String name;

	/**
	 * Email of MovieGoer who made the Transaction
	 */
	private String email;

	/**
	 * Mobile number of MovieGoer who made the Transaction
	 */
	private String mobileNumber;

	/**
	 * Session of MovieGoer who made the Transaction
	 */
	private Session session;

	/**
	 * seat selected for the Transaction
	 */
	private ArrayList<Integer> seatsSelected;

	/**
	 * Price of the transaction
	 */
	private Double totalPrice;

	/**
	 * Creates a Transaction with the given attributes
	 * 
	 * @param TID           transaction ID
	 * @param cinemaCode    Cinema Code of the transaction
	 * @param name          Name of MovieGoer who made the Transaction
	 * @param email         Email of MovieGoer who made the Transaction
	 * @param mobileNumber  Mobile number of MovieGoer who made the Transaction
	 * @param session       Session in which the Transaction is made for
	 * @param seatsSelected Seat selected in which the Transaction is made for
	 * @param totalPrice    Price of this transaction
	 */
	public Transaction(String TID, String cinemaCode, String name, String email, String mobileNumber, Session session,
			ArrayList<Integer> seatsSelected, Double totalPrice) {

		this.TID = TID;
		this.cinemaCode = cinemaCode;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.session = session;
		this.seatsSelected = seatsSelected;
		this.totalPrice = totalPrice;

	}

	/**
	 * Get the Transaction ID of the Transaction
	 * 
	 * @return String Transaction ID of this Transaction
	 */
	public String getTID() {
		return this.TID;
	}

	/**
	 * Set the Transaction ID of the Transaction
	 * 
	 * @param TID ID of this Transaction
	 */
	public void setTID(String TID) {
		this.TID = TID;
	}

	/**
	 * Get the cinema code of this transaction
	 * 
	 * @return String cinema code
	 */
	public String getCinemaCode() {
		return this.cinemaCode;
	}

	/**
	 * Set the cinema code of this transaction
	 * 
	 * @param cinemaCode cinema code
	 */
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	/**
	 * Get the name of the MovieGoer who made this Transaction
	 * 
	 * @return String Name of MovieGoer
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name of the MovieGoer who made this Transaction
	 * 
	 * @param name Name of MovieGoer
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the email of the MovieGoer who made this Transaction
	 * 
	 * @return String Email of MovieGoer
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Set the email of the MovieGoer who made this Transaction
	 * 
	 * @param email Email of MovieGoer
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the mobile number of the MovieGoer who made this Transaction
	 * 
	 * @return String Mobile Number of MovieGoer
	 */
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	/**
	 * Set the mobile number of the MovieGoer who made this Transaction
	 * 
	 * @param mobileNumber Mobile Number of MovieGoer
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * Get the session of the transaction
	 * 
	 * @return Session Session of the transaction
	 */
	public Session getSession() {
		return this.session;
	}

	/**
	 * Set the session of the transaction
	 * 
	 * @param session Session of the transaction
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * Get the seats selected for this transaction
	 * 
	 * @return ArrayList<Integer> Seats selected for this transaction
	 */
	public ArrayList<Integer> getSeatsSelected() {
		return this.seatsSelected;
	}

	/**
	 * Get the seats selected for this transaction
	 * 
	 * @param seatesSelected Seats selected for this transaction
	 */
	public void setSeatsSelected(ArrayList<Integer> seatsSelected) {
		this.seatsSelected = seatsSelected;
	}

	/**
	 * Get the total price for this transaction
	 * 
	 * @return Double Price for this transaction
	 */
	public Double getTotalPrice() {
		return this.totalPrice;
	}

	/**
	 * Set the total price for this transaction
	 * 
	 * @param totalPrice Price for this transaction
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Get the string of this transacton when being displayed
	 * 
	 * @return String Displated Transaction
	 */
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
				+ "\nSelected Seats: ";

		for (int j = 0; j < this.getSeatsSelected().size(); j++) {
			returnString += "\n--> Seat Number = " + this.getSeatsSelected().get(j);
		}

		returnString += "\n::::::::::::::::::::::::::::::";

		// System.out.println("TransactionID = " + this.getTID());
		// System.out.println("Customer Name = " + this.getName());
		// System.out.println("Customer Mobile Number = " + this.getMobileNumber());
		// System.out.println("CinemaCode = " + this.getCinemaCode());
		// System.out.println("Screening Date Time = " +
		// this.getSession().getStringSessionDateTime());
		// System.out.println("Movie Duration (minutes) = " +
		// this.getSession().getMovie().getDuration());
		// System.out.println("Movie Title = " +
		// this.getSession().getMovie().getTitle());
		// System.out.println("Total Price Paid (SGD) = " + this.getTotalPrice());
		// System.out.println("Selected Seats: " );
		// for (int j = 0; j < this.getSeatsSelected().size(); j++)
		// {
		// System.out.println("--> Seat Number = " + this.getSeatsSelected().get(j));
		// }
		// System.out.println("\n::::::::::::::::::::::::::::::");

		return returnString;
	}

}