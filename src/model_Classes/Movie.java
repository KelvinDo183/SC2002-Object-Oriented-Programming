package model_Classes;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Represents a Movie
 */
@SuppressWarnings("serial")
public class Movie implements Serializable {

	/**
	 * This movie's unique ID
	 */
	private int id;

	/**
	 * This movie's title
	 */
	private String title;

	/**
	 * This movie's type
	 */
	private MovieType type;

	/**
	 * This movie's status
	 */
	private MovieStatus status;

	/**
	 * This movie's description
	 */
	private String description;

	/**
	 * This movie's rating
	 */
	private float rating;

	/**
	 * This movie's duration
	 */
	private double duration;

	/**
	 * This movie's release date
	 */
	private LocalDate releaseDate;

	/**
	 * This movie's end date
	 */
	private LocalDate endDate;

	/**
	 * This movie's directorName
	 */
	private String directorName;

	/**
	 * This movie's cast members
	 */
	private ArrayList<String> castMembers;

	/**
	 * This movie's reviews
	 */
	private ArrayList<Review> reviews;

	/**
	 * Creates a Movie with the given attribute
	 * 
	 * @param id           This Movie's ID
	 * @param title        This Movie's title
	 * @param type         This Movie's type
	 * @param description  This Movie's description
	 * @param duration     This Movie's duration (in hours) e.g. 1.5 == 1hours
	 * @param rating       This Movie's rating
	 * @param releaseDate  This Movie's release date
	 * @param endDate      This Movie's end date
	 * @param directorName This Movie's director
	 * @param castMembers  This Movie's list of casts
	 */
	public Movie(
			int id, String title, MovieType type, String description, double duration, float rating,
			LocalDate releaseDate, LocalDate endDate, String directorName, ArrayList<String> castMembers) {
		this.id = id;
		this.title = title;
		this.type = MovieType.BLOCKBUSTER;
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

	/**
	 * Get the ID of this Movie
	 * 
	 * @return ID of this Movie
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Set the ID of this Movie
	 * 
	 * @param ID ID of this Movie
	 */
	public void setID(int ID) {
		this.id = ID;
	}

	/**
	 * Get the title of this Movie
	 * 
	 * @return String Title of this Movie
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Set the title of this Movie
	 * 
	 * @param title Title of this Movie
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the status of this Movie
	 * 
	 * @return MovieStatus Status of this Movie
	 */
	public MovieStatus getStatus() {
		return this.status;
	}

	/**
	 * Set the status of this Movie
	 * 
	 * @param status Status of this Movie
	 */
	public void setStatus(MovieStatus status) {
		this.status = status;
	}

	/**
	 * Get the type of this Movie
	 * 
	 * @return MovieType Type of this Movie
	 */
	public MovieType getType() {
		return this.type;
	}

	/**
	 * Set the type of this Movie
	 * 
	 * @param type Type of this Movie
	 */
	public void setType(MovieType type) {
		this.type = type;
	}

	/**
	 * Get the description of this Movie
	 * 
	 * @return String Description of this Movie
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Set the description of this Movie
	 * 
	 * @param description Description of this Movie
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the rating of this Movie
	 * 
	 * @return float Rating of this Movie
	 */
	public float getRating() {
		return this.rating;
	}

	/**
	 * Set the rating of this Movie
	 * 
	 * @param rating Rating of this Movie
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}

	/**
	 * Get the duration of this Movie
	 * 
	 * @return double Duration of this Movie
	 */
	public double getDuration() {
		return this.duration;
	}

	/**
	 * Set the duration of this Movie
	 * 
	 * @param duration Duration of this Movie
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	/**
	 * Get the release date of this Movie
	 * 
	 * @return LocallDate release date of this Movie
	 */
	public LocalDate getReleaseDate() {
		return this.releaseDate;
	}

	/**
	 * Set the release date of this Movie
	 * 
	 * @param releaseDate Release date of this Movie
	 */
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Get the string of releasedate of this Movie
	 * 
	 * @return String String of releasedate of this Movie
	 */
	public String getMovieReleaseDateToString() {
		return releaseDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
	}

	/**
	 * Get the string of enddate of this Movie
	 * 
	 * @return String String of enddate of this Movie
	 */
	public String getMovieEndDateToString() {
		return endDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
	}

	/**
	 * Get the end date of this Movie
	 * 
	 * @return LocallDate end date of this Movie
	 */
	public LocalDate getEndDate() {
		return this.endDate;
	}

	/**
	 * Set the end date of this Movie
	 * 
	 * @param endDate end date of this Movie
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Get the director's name of this Movie
	 * 
	 * @return String Director's name of this Movie
	 */
	public String getDirectorName() {
		return this.directorName;
	}

	/**
	 * Set the director's name of this Movie
	 * 
	 * @param directorName Director's name of this Movie
	 */
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	/**
	 * Get the cast members of this Movie
	 * 
	 * @return ArrayList<String> Cast members of this Movie
	 */
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

	/**
	 * Get the cast members string of this Movie
	 * 
	 * @return String Cast members string of this Movie
	 */
	public String getCastMembersToString() {

		String toReturn = "\n";

		for (int i = 0; i < this.castMembers.size(); i++) {
			if (this.castMembers.get(i).compareTo("N") != 1) {
				toReturn += this.castMembers.get(i);
			}
		}

		toReturn += "\n";

		return toReturn;
	}

	/**
	 * Set the cast members of this Movie
	 * 
	 * @param castMembers Cast members of this Movie
	 */
	public void setCastMembers(ArrayList<String> castMembers) {
		this.castMembers = castMembers;
	}

	/**
	 * Get the reviews of this Movie
	 * 
	 * @return ArrayList<Review> Reviews of this Movie
	 */
	public ArrayList<Review> getReviews() {
		return this.reviews;
	}

	/**
	 * Set the reviews of this Movie
	 * 
	 * @param reviews Reviews of this Movie
	 */
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * String to return when this Movie is being called
	 * 
	 * @return String
	 */
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

	/**
	 * adjust the status of the movie by date
	 */
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
	 * Compare and check if object is identical to this Movie
	 * 
	 * @param movie item to be compared to
	 * @return boolean Return true if item is identical to this Movie, else false
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