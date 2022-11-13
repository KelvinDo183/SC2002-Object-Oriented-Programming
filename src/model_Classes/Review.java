package model_Classes;

import java.io.Serializable;

/**
 * Represents a Review of a Movie in the system
 */
public class Review implements Serializable {

	/**
	 * this Review's title
	 */
	private String title;

	/**
	 * this Review's rating
	 */
	private float rating;

	/**
	 * this Review's comment
	 */
	private String comment;

	/**
	 * Get the title of this review
	 * 
	 * @return String Title of this review
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Get the rating of this review
	 * 
	 * @return float Rating of this review
	 */
	public float getRating() {
		return this.rating;
	}

	/**
	 * Get the comment of this review
	 * 
	 * @return String Comment of this review
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Get the string of this review when being displayed
	 * 
	 * @return String String of this review when being displayed
	 */
	public String toString() {
		String out = "";
		out += "        Movie title: " + getTitle() + "\n"
				+ "        Rating: " + String.valueOf(getRating()) + "\n"
				+ "        Comment: " + getComment();
		return out;
	}

	/**
	 * Set the rating of this review
	 * 
	 * @param rating Rating of this review
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}

	/**
	 * Set the comment of this review
	 * 
	 * @param comment Comment of this review
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Compare 2 Review Instances to check if they are identical
	 * 
	 * @param review Object to be compared to
	 * @return boolean Return true if both reviews are identical, else false
	 */
	public boolean equals(Review review) {
		if (this.getTitle() == review.getTitle() && this.getRating() == review.getRating()
				&& this.getComment() == review.getComment()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Creates a Review
	 * 
	 * @param title   This Review's title
	 * @param rating  This Review's rating
	 * @param comment This Review's comment
	 */
	public Review(String title, Float rating, String comment) {
		this.title = title;
		this.rating = rating;
		this.comment = comment;
	}

}