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
		Review review = new Review(title, rating, comment);
		
		ArrayList<Review> existingReviews = movie.getReviews();
		existingReviews.add(review);
		// update reviews tied to movie
		movie.setReviews(existingReviews);
		
		ArrayList<Movie> allData = MovieController.read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();
        /*
         * Checks for if that movie named exists. And if yes, get the reviews stored for that movie then add the review
         */
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            if (m.getTitle() == title){
                ArrayList<Review> reviews = m.getReviews();
                reviews.add(review);
                m.setReviews(reviews);
            }
            returnData.add(m);
        }
        this.movieController.replaceExistingFile(FILENAME, returnData);
	}
	
}
