package model_Classes;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Movie implements Serializable {

	private int id;
	private String title;
	private MovieType type;
	private MovieStatus status;
	private String description;
	private float rating;
	private double duration;
	private LocalDate releaseDate;
	private LocalDate endDate;
	private String directorName;
	private ArrayList<String> castMembers;
	private ArrayList<Review> reviews;

	// Constructor for movie
	public Movie(
			int id, String title, MovieType type, String description, double duration, float rating,
			LocalDate releaseDate, LocalDate endDate, String directorName, ArrayList<String> castMembers) {
		this.id = id;
		this.title = title;
		this.type = MovieType.BLOCKBUSTER;
		// adjust movie status based on the dates provided by admin user OUTSIDE of
		// constructor
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

	public float getRating() {
		return this.rating;
	}

	/**
	 * 
	 * @param rating
	 */
	public void setRating(float rating) {
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
		return releaseDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
	}

	public String getMovieEndDateToString() {
		return endDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
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
		ArrayList<String> returnListCastMembers = new ArrayList<String>();
		// do not include "N" as a CastMember
		for (int i = 0; i < this.castMembers.size(); i++) {
			if (this.castMembers.get(i).compareTo("N") != 1) {
				returnListCastMembers.add(this.castMembers.get(i));
			}
		}
		return returnListCastMembers;
	}

	public String getCastMembersToString() {

		String toReturn = "\n";

		for (int i = 0; i < this.castMembers.size(); i++) {
			if (this.castMembers.get(i).compareTo("N") != 1) {
				toReturn += this.castMembers.get(i);
			}
		}
		

		// add another line break for clarity when viewing
		toReturn += "\n";

		return toReturn;
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
		String castString = "";
		for (int i = 0; i < getCastMembers().size(); i++)
			castString = castString.concat(getCastMembers().get(i) + ",");
		castString = castString.substring(0, castString.length() - 1);

		String reviews = "";
		for (int i = 0; i < getReviews().size(); i++) {
			reviews += getReviews().get(i).toString() + "\n\n";
		}
		if (reviews.equals(""))
			reviews = "N/A";

		String toReturn = "\n";
		toReturn += "ID: " + getID() + "\n"
				+ "Title: " + getTitle() + "\n"
				+ "Type: " + this.type.toString() + "\n"
				+ "Status: " + getStatus() + "\n"
				+ "Description: " + getDescription() + "\n"
				+ "Duration: " + getDuration() + "\n"
				+ "Rating: " + getRating() + "\n"
				+ "Release Date: " + getReleaseDate() + "\n"
				+ "End Date: " + getEndDate() + "\n"
				+ "Director Name: " + getDirectorName() + "\n"
				+ "Cast Members: " + getCastMembersToString() + "\n"
				+ "Reviews: " + getReviews() + "\n";

		return toReturn;

	}

	public void adjustStatusByDates() {

		LocalDate todayDate = LocalDate.now();

		if (todayDate.isBefore(this.releaseDate)) {

			Long daysBetweenReleaseToday = this.releaseDate.toEpochDay() - todayDate.toEpochDay();

			// Case 1a: today is BEFORE the release date of movie (more than 7 days)
			if (daysBetweenReleaseToday > 7) {
				this.setStatus(MovieStatus.COMING_SOON);
			}

			// Case 1b: today is BEFORE the release date of movie (7 days or fewer)
			else {
				this.setStatus(MovieStatus.PREVIEW);
			}

		}

		// Case 2: today is BETWEEN release date of movie & end date of movie
		else if ((todayDate.isAfter(this.releaseDate) || todayDate.isEqual(this.releaseDate))
				&& (todayDate.isBefore(endDate) || todayDate.isEqual(this.endDate))) {
			this.setStatus(MovieStatus.NOW_SHOWING);
		}

		// Case 3: today is AFTER release date of movie
		else if (todayDate.isAfter(endDate)) {
			this.setStatus(MovieStatus.END_OF_SHOWING);
		}
	}

	// public String getOverallReviews() {
	// // TODO - implement Movie.getOverallReviews
	// throw new UnsupportedOperationException();
	// }

	// public MovieStatus getShowStatus() {
	// // TODO - implement Movie.getShowStatus
	// throw new UnsupportedOperationException();
	// }

	/**
	 * 
	 * @param movie
	 */
	public boolean equal(Object movie) {
		if (!(movie instanceof Movie)) {
			return false;
		}
		Movie other = (Movie) movie;
		return this.id == other.getID()
				&& this.title.equals(other.getTitle())
				&& this.type.equals(other.getType())
				&& this.description.equals(other.getDescription())
				&& this.releaseDate.equals(other.getReleaseDate())
				&& this.endDate.equals(other.getEndDate())
				&& this.directorName.equals(other.getDirectorName())
				&& this.castMembers.equals(other.getCastMembersToString())
				&& this.reviews.equals(other.getReviews());
	}

}