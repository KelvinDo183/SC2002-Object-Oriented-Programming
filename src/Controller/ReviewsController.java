package Controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;

 
import java.io.FileNotFoundException;
import java.util.ArrayList;

import model_Classes.*;

import Controller.*;
import model_Classes.Movie;
import model_Classes.Review;

/* ReviewsController is a controller class that mainly makes changes to the rating and ArrayList of Reviews of Movies
 * within the movielisting.txt file in datastorage and the user uses most of the functions here in MovieReviewUI.
 * 
 * */

public class ReviewsController {

	/**
	 * The Movie Controller that this controller will reference
	 */
	private MovieController movieController;

	/**
	 * The Transaction Controller that this controller will refer
	 */
	private TransactionController txnController;

	/**
	 * The file name of the database file that this controller will access
	 */
	public String FILENAME;

	/**
	 * Default constructor
	 */
	public ReviewsController() {
		this.movieController = new MovieController();
		this.FILENAME = movieController.FILENAME;
	}

	/**
	 * Parameterized constructor with user-defined Movie Controller
	 * 
	 * @param movieController Non-default Movie Controller to be referenced instead
	 */
	public ReviewsController(MovieController movieController) {
		this.movieController = movieController;
		this.FILENAME = movieController.FILENAME;
	}

	/**
	 * Gets the Movie Controller that this controller is referencing
	 * 
	 * @return MoviesController This controller's Movie Controller
	 */
	public MovieController getMovieController() {
		return this.movieController;
	}

	/**
	 * Change the Movie Controller that this controller is referencing
	 * 
	 * @param movieController This controller's new Movie Controller
	 */
	public void setMovieController(MovieController movieController) {
		this.movieController = movieController;
	}

	/**
	 * Count the Review with title and transaction ID
	 * 
	 * @param title title of that review to be counted
	 * @param TID   transaction ID
	 */
	public int countReviewsWithTitleAndTID(String title, String TID) throws FileNotFoundException {
		// Finds the movie with title in database
		Movie movie = MovieController.findExistingMovie(title);
		ArrayList<Review> reviews = movie.getReviews();
		Review curReview;
		int count = 0;
		/*
		 * Checks for all reviews made under the TID passed into the function
		 */
		for (int i = 0; i < reviews.size(); i++) {
			curReview = reviews.get(i);
			if (curReview.getTID().trim().equalsIgnoreCase(TID)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * CREATE a new Review and add it into the database file
	 * Attributes are validated before creation
	 * If attributes are not allowed, throw error and do nothing
	 * If Database file exist, existing records are read and new Review object is
	 * aopended before saving
	 * If Database file does not exist, Review object will be written to a new file
	 * and saved
	 * 
	 * @param movie   Movie that this Review will be added to
	 * @param title   Title of that review
	 * @param rating  Number of stars given by reviewer, between 0 - 5
	 * @param comment comment of that review
	 * @param TID     transaction ID of that review
	 */
	public void create(Movie movie, String title, float rating, String comment, String TID)
			throws FileNotFoundException {
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
