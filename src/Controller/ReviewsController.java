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

	public void create(Movie movie, String title, float rating, String comment) throws FileNotFoundException {
		// Call the constructor for Review entity
		Review review = new Review(title, rating, comment);

		// Reads the current ArrayList of movies stored in movielisting.txt
		ArrayList<Movie> allData = MovieController.read();

		// Initialises an ArrayList of Movie object to be returned, stores only the
		// modified Movie object with the newly added review
		ArrayList<Movie> returnData = new ArrayList<Movie>();
		/*
		 * Checks for if the movie passed into the function exists. And if yes, get the
		 * reviews stored for that movie then add the new review
		 * to the movie, then add the modified movie to the returned data.
		 */
		for (int i = 0; i < allData.size(); i++) {
			Movie m = allData.get(i);
			if (m.getTitle() == title) {

				// Appends new review into the ArrayList
				ArrayList<Review> reviews = m.getReviews();
				reviews.add(review);
				m.setReviews(reviews);

				// Updates the rating average
				int reviewsCount = reviews.size();
				float newRating = ((m.getRating() * (reviewsCount - 1) + rating) / reviewsCount);
				m.setRating(newRating);
			}
			returnData.add(m);
		}
		// Finds the matching movie within movielisting.txt and replaces it with the
		// modified Movie stored in returnData
		this.movieController.replaceExistingFile(FILENAME, returnData);
	}

}
