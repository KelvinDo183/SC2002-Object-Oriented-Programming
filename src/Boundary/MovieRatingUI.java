package Boundary;

import java.io.FileNotFoundException;
import java.util.Scanner;
import Controller.*;
import model_Classes.Movie;

public class MovieRatingUI {
	static Scanner sc = new Scanner(System.in);
	
	private MovieController moviesController = new MovieController();
	private SearchMovieUI searchMovieUI = new SearchMovieUI();
	private ReviewsController reviewsController = new ReviewsController();
	
	public void main() throws FileNotFoundException{
        System.out.println("Enter username: ");
        String user = sc.nextLine();
        
        if(searchMovieUI.listAll()){
            System.out.print("Name the title of the movie to be rated(case sensitive): ");
            String title =sc.nextLine();
            Movie movie = moviesController.findExistingMovie(title);
            
            if(movie == null){
                System.out.println("\nMovie "+ title +" doesn't exist!");
                System.out.println("Returning to menu...\n");
                return;
            }
            
            System.out.println("Input rating(from 0-5 stars):");
            float rating = sc.nextFloat();
            System.out.println("Input additional comment");
            String comment = sc.nextLine();
            
            //TODO ReviewsController class
            reviewsController.create(movie, user, rating, comment);
        }
    }
}
