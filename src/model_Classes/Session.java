package model_Classes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
//import model_Classes.SeatingPlan;
import java.time.format.DateTimeFormatter;

public class Session implements Serializable {

	private Movie movie;
	private LocalDateTime sessionDateTime;
	private SeatingPlan seatsAvailability;
	private int sessionID;

	/**
	 * 
	 * @param movie
	 * @param session
	 * @param DateTime
	 * @param seatsAvailability
	 */
	public Session(Movie movie, int sessionID, LocalDateTime DateTime, SeatingPlan seatsAvailability) throws UnsupportedOperationException {
		this.movie = movie;
		this.sessionID = sessionID;
		this.sessionDateTime = DateTime;
		this.seatsAvailability = seatsAvailability;
//		System.out.printf("\nThe sessionID is =", sessionID);
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

	public String getStringSessionDateTime() throws UnsupportedOperationException {
		return sessionDateTime.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy HH:mm"));
	}

	public SeatingPlan getSeatsAvailability() {
		return this.seatsAvailability;
	}

	public void setSeatsAvailability(SeatingPlan seatsAvailability) {
		this.seatsAvailability = seatsAvailability;
	}

	public int getID() {

		return this.sessionID;
	}

	/**
	 * 
	 * @param id
	 */
	public void setID(int sessionID) throws UnsupportedOperationException{
		this.sessionID = sessionID;

	}

	public boolean isWeekend() {
		if (sessionDateTime.getDayOfWeek() == DayOfWeek.SATURDAY
				|| sessionDateTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
			return true;
		} else if (sessionDateTime.getDayOfWeek() == DayOfWeek.FRIDAY && sessionDateTime.getHour() > 18) {
			return true;
		} else {
			return false;
		}
	}

	public LocalTime getStartTime() {

		return getSessionDateTime().toLocalTime();

	}

	public LocalTime getEndTime() {

		return getStartTime().plusMinutes((long) (60.0 * getMovie().getDuration()));

	}

	public String toString() {

		return "id: " + this.getID() + " Title: " + movie.getTitle() + ", DateTime: " + this.getSessionDateTime()
				+ "is Weekend: " + isWeekend();
	}

}