package Boundary;

import java.io.FileNotFoundException;
import java.util.Scanner;

import Controller.*;
import model_Classes.*;

/*
 * MovieReviewUI is called when a Movie-Goer selects the respective option to do so from MainMenuUI, they can leave a rating and an additional 
 * comment to be recorded as a MovieReview(controlled by ReviewsController and kept in reviews.txt)
 * */

public class MovieReviewUI {
	static Scanner sc = new Scanner(System.in);
	
	private MovieController moviesController = new MovieController();
	private SearchMovieUI searchMovieUI = new SearchMovieUI();
	private ReviewsController reviewsController = new ReviewsController();
	
	public void main() throws FileNotFoundException{
        
		System.out.println("Enter transaction ID: ");
        int tid = sc.nextInt();
        System.out.println("Enter email: ");
        String email = sc.nextLine();
        //TODO Implement check for validity of transaction ID and email
        
        /*
         * Lists all screening movies to the user, if any, and lets them name a movie to give a rating and comment for. Else "No movies found" is
         * printed and the user is sent back to MainMenuUI. Also sent back when movie title does not exist.
         * */
        if(searchMovieUI.listAvailableScreeningMovies()){
            System.out.print("Name the title of the movie to be reviewed(case sensitive): ");
            

            String title =sc.nextLine();          
            Movie movie = MovieController.findExistingMovie(title);
        

            if(movie==null){
                System.out.println("\nMovie "+ title +" doesn't exist!");
                System.out.println("Returning to menu...\n");
                return;
            }
            
            System.out.println("Input rating(from 0-5 stars):");
            float rating = sc.nextFloat();
            System.out.println("Input additional comment");
            String comment = sc.nextLine();
            
            reviewsController.create(title, rating, comment);
        }
    }
}
