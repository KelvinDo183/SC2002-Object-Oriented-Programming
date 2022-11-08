package model_Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Movie implements Serializable {

	private int id;
	private String title;
	private MovieType type;
	private MovieStatus status;
	private String description;
	private String rating;
	private double duration;
	private LocalDate releaseDate;
	private LocalDate endDate;
	private String directorName;
	private ArrayList<String> castMembers;
	private ArrayList<Review> reviews;

	// Constructor for movie
	public Movie(
			int id, String title, MovieType type, String description, double duration, String rating,
			LocalDate releaseDate, LocalDate endDate, String directorName, ArrayList<String> castMembers) {
		this.id = id;
		this.title = title;
		this.type = type;
		// adjust movie status based on the dates provided by admin user OUTSIDE of constructor
		this.status = MovieStatus.NOW_SHOWING;
		this.description = description;
		this.duration = duration;
		this.rating = rating;
		this.releaseDate = releaseDate;
		this.endDate = endDate;
		this.directorName = directorName;
		this.castMembers = castMembers;
		this.reviews = new ArrayList<Review>();
	}

	public int getID() {
		return this.id;
	}

	// this setter method will be used upon successful update of movie listing
	public void setID(int ID) {
		this.id = ID;
	}

	public String getTitle() {
		return this.title;
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public MovieStatus getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(MovieStatus status) {
		this.status = status;
	}

	public MovieType getType() {
		return this.type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(MovieType type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param synopsis
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return this.rating;
	}

	/**
	 * 
	 * @param rating
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	public double getDuration() {
		return this.duration;
	}

	/**
	 * 
	 * @param duration
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	public LocalDate getReleaseDate() {
		return this.releaseDate;
	}

	/**
	 * 
	 * @param movieReleaseDate
	 */
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getMovieReleaseDateToString() {
		// TODO - implement Movie.getMovieReleaseDateToString
		throw new UnsupportedOperationException();
	}

	public String getMovieEndDateToString() {
		// TODO - implement Movie.getMovieEndDateToString
		throw new UnsupportedOperationException();
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	/**
	 * 
	 * @param movieEndDate
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDirectorName() {
		return this.directorName;
	}

	/**
	 * 
	 * @param director
	 */
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public ArrayList<String> getCastMembers() {
		return this.castMembers;
	}

	/**
	 * 
	 * @param cast
	 */
	public void setCastMembers(ArrayList<String> castMembers) {
		this.castMembers = castMembers;
	}

	public ArrayList<Review> getReviews() {
		return this.reviews;
	}

	/**
	 * 
	 * @param reviews
	 */
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public String toString() {
		// TODO - implement Movie.toString
//		throw new UnsupportedOperationException();
		String toReturn = "";
		toReturn += "ID: " + getID() + "\n"
				+ "Title: " + getTitle() + "\n"
				+ "Type: " + getType().toString() + "\n"
				+ "Status: " + getStatus() + "\n"
				+ "Description: " + getDescription() + "\n"
				+ "Duration: " + getDuration() + "\n"
				+ "Rating: " + getRating() + "\n"
				+ "Release Date: " + getReleaseDate() + "\n"
				+ "End Date: " + getEndDate() + "\n"
				+ "Director Name: " + getDirectorName() + "\n"
				+ "Cast Members: " + getCastMembers() + "\n"
				+ "Reviews: " + getReviews()
				;
		return toReturn;
		
	}
	
	
//	public String getOverallReviews() {
//		// TODO - implement Movie.getOverallReviews
//		throw new UnsupportedOperationException();
//	}

//	public MovieStatus getShowStatus() {
//		// TODO - implement Movie.getShowStatus
//		throw new UnsupportedOperationException();
//	}

	/**
	 * 
	 * @param movie
	 */
	public boolean equal(Object movie) {
		// TODO - implement Movie.equal
		throw new UnsupportedOperationException();
	}

}