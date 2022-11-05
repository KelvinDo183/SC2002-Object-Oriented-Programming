package model_Classes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import model_Classes.SeatingPlan;

public class Session implements Serializable {

	private Movie movie;
	private LocalDateTime sessionDateTime;
	private SeatingPlan seatsAvailability;
	private int id;

	/**
	 * 
	 * @param movie
	 * @param session
	 * @param DateTime
	 * @param seatsAvailability
	 */
	public Session(Movie movie, int session, LocalDateTime DateTime, SeatingPlan seatsAvailability) {
		// TODO - implement Session.Session
		throw new UnsupportedOperationException();
	}

	public Movie getMovie() {
		return this.movie;
	}

	/**
	 * 
	 * @param movie
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public LocalDateTime getSessionDateTime() {
		return this.sessionDateTime;
	}

	/**
	 * 
	 * @param sessionDateTime
	 */
	public void setSessionDateTime(LocalDateTime sessionDateTime) {
		this.sessionDateTime = sessionDateTime;
	}

	public String getStringSessionDateTime() {
		// TODO - implement Session.getStringSessionDateTime
		throw new UnsupportedOperationException();
	}

	public SeatingPlan getSeatsAvailability() {
		return this.seatsAvailability;
	}

	/**
	 * 
	 * @param seatsAvailability
	 */
	public void setSeatsAvailability(SeatingPlan seatsAvailability) {
		this.seatsAvailability = seatsAvailability;
	}

	public int getID() {
		// TODO - implement Session.getID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void setID(int id) {
		// TODO - implement Session.setID
		throw new UnsupportedOperationException();
	}

	public boolean isWeekend() {
		// TODO - implement Session.isWeekend
		throw new UnsupportedOperationException();
	}

	public LocalTime getStartTime() {
		// TODO - implement Session.getStartTime
		throw new UnsupportedOperationException();
	}

	public LocalTime getEndTime() {
		// TODO - implement Session.getEndTime
		throw new UnsupportedOperationException();
	}

	public String toString() {
		// TODO - implement Session.toString
		throw new UnsupportedOperationException();
	}

}