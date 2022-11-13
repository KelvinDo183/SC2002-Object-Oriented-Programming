package model_Classes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
//import model_Classes.SeatingPlan;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Session
 * Each session will be assigned to a particular cinema by the cinema's code
 */
public class Session implements Serializable {

	/**
	 * The movie that will be screened during this Session
	 */
	private Movie movie;

	/**
	 * The date and time in which the screening session will begin
	 */
	private LocalDateTime sessionDateTime;

	/**
	 * Seats Availability of this Session
	 */
	private SeatingPlan seatsAvailability;

	/**
	 * Session ID assigned to this Session
	 */
	private int sessionID;

	/**
	 * Creates a Session with the given attributes
	 * 
	 * @param movie           The movie that will be screened during this Session
	 * @param sessionID       Session ID assigned to this Session
	 * @param sessionDateTime The date and time in which the screening session will
	 * @param seatingPlan     Seats Availability of this Session
	 */
	public Session(Movie movie, int sessionID, LocalDateTime sessionDateTime, SeatingPlan seatingPlan) {
		this.movie = movie;
		this.sessionID = sessionID;
		this.sessionDateTime = sessionDateTime;
		this.seatsAvailability = seatingPlan;
	}

	/**
	 * Get the movie that will be screened during this Session
	 * 
	 * @return Movie Movie that will be screened
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * Set the movie that will be screened during this Session
	 * 
	 * @param movie Movie that will be screened
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * Get the date and time which the screening session will begin
	 * 
	 * @return LocalDateTime Date and timm of the screening session
	 */
	public LocalDateTime getSessionDateTime() {
		return sessionDateTime;
	}

	/**
	 * Change the date and time of this Session
	 * 
	 * @param sessionDateTime New date and time of this Session
	 */
	public void setSessionDateTime(LocalDateTime sessionDateTime) {
		this.sessionDateTime = sessionDateTime;
	}

	/**
	 * Get the Date and Time of this Session in another format
	 * 
	 * @return String Date and time of Session in the format of "EEEE, dd/MM/yyyy
	 *         HH:mm"
	 */
	public String getStringSessionDateTime() {
		return sessionDateTime.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy HH:mm"));
	}

	/**
	 * Get the seating plan of this Session
	 * 
	 * @return SeatingPlan This Session's seating plan
	 */
	public SeatingPlan getSeatsAvailability() {
		return this.seatsAvailability;
	}

	public void setSeatsAvailability(SeatingPlan seatsAvailability) {
		this.seatsAvailability = seatsAvailability;
	}

	/**
	 * Get the ID of this Session
	 * 
	 * @return int This Session's ID
	 */
	public int getID() {

		return this.sessionID;
	}

	/**
	 * Set the ID of this Session
	 * 
	 * @param sessionID This Session's ID
	 */
	public void setID(int sessionID) {
		this.sessionID = sessionID;

	}

	/**
	 * Check if the session is considered a weekend
	 * 
	 * @return boolean Return true if session is considered a weekend, else False
	 */
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

	/**
	 * Get the start time of this screening Session
	 * 
	 * @return LocalTime Start time of this Session
	 */
	public LocalTime getStartTime() {

		return getSessionDateTime().toLocalTime();

	}

	/**
	 * Get the end time of this screening session
	 * 
	 * @return LocalTime End time of this Session
	 */
	public LocalTime getEndTime() {

		return getStartTime().plusMinutes((long) (60.0 * getMovie().getDuration()));

	}

	/**
	 * String to return when this Movie_Goer is being called
	 * 
	 * @return String
	 */
	public String toString() {

		return "id: " + this.getID() + " Title: " + movie.getTitle() + ", DateTime: " + this.getSessionDateTime()
				+ "is Weekend: " + isWeekend();
	}

}