package model_Classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Movie implements Serializable {

	private int id;
	private String title;
	private MovieType type;
	private String synopsis;
	private String rating;
	private double duration;
	private LocalDate movieReleaseDate;
	private LocalDate movieEndDate;
	private String director;
	private ArrayList<String> cast;
	private ArrayList<Review> reviews;

	public int getID() {
		// TODO - implement Movie.getID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void setID(int id) {
		// TODO - implement Movie.setID
		throw new UnsupportedOperationException();
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

	public String getSynopsis() {
		return this.synopsis;
	}

	/**
	 * 
	 * @param synopsis
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
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

	public LocalDate getMovieReleaseDate() {
		return this.movieReleaseDate;
	}

	/**
	 * 
	 * @param movieReleaseDate
	 */
	public void setMovieReleaseDate(LocalDate movieReleaseDate) {
		this.movieReleaseDate = movieReleaseDate;
	}

	public String getMovieReleaseDateToString() {
		// TODO - implement Movie.getMovieReleaseDateToString
		throw new UnsupportedOperationException();
	}

	public String getMovieEndDateToString() {
		// TODO - implement Movie.getMovieEndDateToString
		throw new UnsupportedOperationException();
	}

	public LocalDate getMovieEndDate() {
		return this.movieEndDate;
	}

	/**
	 * 
	 * @param movieEndDate
	 */
	public void setMovieEndDate(LocalDate movieEndDate) {
		this.movieEndDate = movieEndDate;
	}

	public String getDirector() {
		return this.director;
	}

	/**
	 * 
	 * @param director
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	public ArrayList<String> getCast() {
		return this.cast;
	}

	/**
	 * 
	 * @param cast
	 */
	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
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
		throw new UnsupportedOperationException();
	}

	public String getOverallReviews() {
		// TODO - implement Movie.getOverallReviews
		throw new UnsupportedOperationException();
	}

	public MovieStatus getShowStatus() {
		// TODO - implement Movie.getShowStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movie
	 */
	public boolean equal(Object movie) {
		// TODO - implement Movie.equal
		throw new UnsupportedOperationException();
	}

}