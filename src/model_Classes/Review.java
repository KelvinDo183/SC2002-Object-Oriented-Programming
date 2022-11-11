package model_Classes;

import java.io.Serializable;

/* Class for the Review entity, has an association with the Movie entity from the usage of each movie's title. Verification of validity will be done in 
 * ReviewsController and the user makes use of this via MovieReviewUI.
 * 
 * Parameters used in the creation are:
 * String title(of the movie)
 * float rating
 * String comment
 * */

public class Review implements Serializable {
    private String title;
	private float rating;
	private String comment;

	public String getTitle() {
		return this.title;
	}
	
	public float getRating() {
		return this.rating;
	}

	public String getComment() {
		return this.comment;
	}

	public String toString() {
		String out = "";
		out     += "        Movie title: " + getTitle() + "\n"
                +  "        Rating: " + String.valueOf(getRating()) + "\n"
                +  "        Comment: " + getComment();
		return out;
	}

	public void setRating(float rating) {
		// TODO - implement Review.setRating
		this.rating = rating;
	}

	public void setComment(String comment) {
		// TODO - implement Review.setComment
		this.comment = comment;
	}

	/**
	 * 
	 * @param review
	 */
	public boolean equals(Review review) {
		// TODO - implement Review.equals
		if(this.getTitle() == review.getTitle() && this.getRating() == review.getRating() && this.getComment() == review.getComment() ) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * 
	 * @param username
	 * @param rating
	 * @param comment
	 */
	public Review(String title, Float rating, String comment) {
		// TODO - implement Review.Review
		this.title = title;
		this.rating = rating;
		this.comment = comment;
	}

}