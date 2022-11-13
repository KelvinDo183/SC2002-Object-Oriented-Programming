package Controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/* ReviewsController is a controller class for communicating between the reviews.txt file in datastorage and 
 * the user who is running the code in MovieReviewUI.
 * 
 * */
import java.io.FileNotFoundException;
import java.util.ArrayList;

import model_Classes.*;

import Controller.*;
import model_Classes.Movie;
import model_Classes.Review;

public class ReviewsController {

	private MovieController movieController;
	private TransactionController txnController;
	public String FILENAME;

	public ReviewsController() {
		this.movieController = new MovieController();
		this.FILENAME = movieController.FILENAME;
	}

	public ReviewsController(MovieController movieController) {
		this.movieController = movieController;
		this.FILENAME = movieController.FILENAME;
	}

	/* Get/Set methods for MovieController */
	public MovieController getMovieController() {
		return this.movieController;
	}

	public void setMovieController(MovieController movieController) {
		this.movieController = movieController;
	}
	
	public int countReviewsWithTitleAndTID(String title, String TID) throws FileNotFoundException {
		// Finds the movie with title in database
		Movie movie = MovieController.findExistingMovie(title);
		ArrayList<Review> reviews = movie.getReviews();
		Review curReview;
		int count=0;
		/*
		 * Checks for all reviews made under the TID passed into the function
		 */
		for (int i = 0; i < reviews.size(); i++) {
			curReview = reviews.get(i);
			if (curReview.getTID() == TID) {
				count++;
			}
		}
		return count;
	}
	

	public void create(Movie movie, String title, float rating, String comment, String TID) throws FileNotFoundException {
		// Call the constructor for Review entity
		Review review = new Review(title, rating, comment, TID);

		// Reads the current ArrayList of movies stored in movielisting.txt
		ArrayList<Movie> allData = MovieController.read();

		// Initializes an ArrayList of Movie object to be returned, stores only the
		// modified Movie object with the newly added review
		ArrayList<Movie> returnData = new ArrayList<Movie>();
		/*
		 * Checks for if the movie passed into the function exists. And if yes, get the
		 * reviews stored for that movie then add the new review
		 * to the movie, then add the modified movie to the returned data.
		 */
		for (int i = 0; i < allData.size(); i++) {
			Movie m = allData.get(i);
			if (m.getTitle().trim().equalsIgnoreCase(title)) {
				// Appends new review into the ArrayList
				ArrayList<Review> reviews = m.getReviews();
				reviews.add(review);
				m.setReviews(reviews);

				// Updates the rating average
				int reviewsCount = reviews.size();
				float newRating = ((m.getRating() * (reviewsCount - 1) + rating) / reviewsCount);
				m.setRating(newRating);
				System.out.println("Rating updated adding "+ rating +" to get " + newRating);
			}
			returnData.add(m);
		}
		
		// Finds the matching movie within movielisting.txt and replaces it with the
		// modified Movie stored in returnData
		this.movieController.replaceExistingFile(FILENAME, returnData);
	}

}
