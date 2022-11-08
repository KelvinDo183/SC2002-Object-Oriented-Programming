package Boundary;

import Controller.*;
import Model.*;

public class ReviewsController {
	
	private MoviesController movieController;
	public String FILENAME;
	
	public ReviewsController() {
		this.movieController = new MovieController;
		this.FILENAME = movieController.FILENAME;
	}
	
	public ReviewsController(MoviesController movieController) {
		this.movieController = movieController;
		this.FILENAME = movieController.FILENAME;
	}
	
	//Get/Set methods for MovieController
	public MovieController getMovieController() {
		return this.movieController;
	}
	public void setMovieController(MovieController movieController) {
		this.movieController = movieController;
	}
		
	
	public void create(Movie movie, String user, float rating, String comment) {
		Review review = new Review(movie, user, rating, comment);
		
		ArrayList<Movie> allData = this.movieController.read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();
        
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            if (m.equals(movie)){
                ArrayList<Review> reviews = m.getReviews();
                reviews.add(review);
                m.setReviews(reviews);
            }
            returnData.add(m);
        }
        this.movieController.replaceExistingFile(FILENAME, returnData);
	}
	
}
