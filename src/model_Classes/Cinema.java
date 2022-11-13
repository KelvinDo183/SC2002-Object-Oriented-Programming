package model_Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a Cinema
 * A cinema has its code, type, seating plan and list of sessions
 */
@SuppressWarnings("serial")
public class Cinema implements Serializable {
	/**
	 * This Cinema's code
	 */
	private String code;

	/**
	 * This Cinema's type
	 */
	private CinemaType cinemaType;

	/**
	 * This Cinema's seatingplan
	 */
	protected SeatingPlan seatingPlan;

	/**
	 * This Cinema's list of sessions
	 */
	private ArrayList<Session> sessions;

	/**
	 * Creates a Cinema with the given attributes
	 * 
	 * @param code        This Cinema's code
	 * @param cinemaType  This Cinema's type
	 * @param seatingPlan This Cinema's seating plan
	 */
	public Cinema(String code, CinemaType cinemaType, SeatingPlan seatingPlan) {
		this.code = code;
		this.cinemaType = cinemaType;
		this.seatingPlan = seatingPlan;
		this.sessions = new ArrayList<Session>();
	}

	/**
	 * Get the code of this Cinema
	 * 
	 * @return String this Cinema's code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Set the code of this Cinema
	 * 
	 * @param String this Cinema's code
	 */

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Get the cinema type of this Cinema
	 * 
	 * @return CinemaType this Cinema's type
	 */
	public CinemaType getCinemaType() {
		return cinemaType;
	}

	/**
	 * Set the cinema type of this Cinema
	 * 
	 * @param cinemaClass this Cinema's type
	 */
	public void setCinemaType(CinemaType cinemaClass) {
		this.cinemaType = cinemaClass;
	}

	/**
	 * Get the seatingplan of this Cinema
	 * 
	 * @return SeatingPlan this Cinema's seatingplan
	 */
	public SeatingPlan getSeatingPlan() {
		return seatingPlan;
	}

	/**
	 * Set the seatingplan of this Cinema
	 * 
	 * @param seatingPlan this Cinema's seatingplan
	 */
	public void setSeatingPlan(SeatingPlan seatingPlan) {
		this.seatingPlan = seatingPlan;
	}

	/**
	 * Get the list of sessions of this Cinema
	 * 
	 * @return ArrayList<Session> this Cinema's list of sessions
	 */

	public ArrayList<Session> getSessions() {
		return sessions;
	}

	/**
	 * Set the list of sessions of this Cinema
	 * 
	 * @param sessions this Cinema's list of sessions
	 */
	public void setSessions(ArrayList<Session> sessions) {
		this.sessions = sessions;
	}

	/**
	 * String to return when this Cinema is being called
	 * 
	 * @return String
	 */
	public String toString() {
		String sessionsString = "";
		for (int i = 0; i < sessions.size(); i++)
			sessionsString = sessionsString.concat("-----" + sessions.get(i) + "\n");
		sessionsString = sessionsString.substring(0, sessionsString.length());

		String details = "";
		details += "Cinema code: " + getCode() + "\n"
				+ "Cinema type: " + getCinemaType() + "\n"
				+ "Sessions: " + getSessions().size() + "\n"
				+ sessionsString;
		return details;
	}
}