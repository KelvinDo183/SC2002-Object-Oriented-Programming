package Boundary;

import java.util.Scanner;
import Controller.*;
import Model.*;

public class MovieRatingUI {
	static Scanner sc = new Scanner(System.in);
	
	private MoviesController moviesController = new MoviesController();
	private SearchMovieUI searchMovieUI = new SearchMovieUI();
	private ReviewsController reviewsController = new ReviewsController();
	
	public void main(){
        System.out.println("Enter username: ");
        String user = sc.nextLine();
        
        if(searchMovieUI.listAll()){
            System.out.print("Name the title of the movie to be rated(case sensitive): ");
            String title =sc.nextLine();
            Movie movie = moviesController.findExistingMovie;
            
            if(movie.isEmpty()){
                System.out.println("\nMovie "+ title +" doesn't exist!");
                System.out.println("Returning to menu...\n");
                return;
            }
            
            System.out.println("Input rating(from 0-5 stars):");
            float rating = sc.nextFloat();
            System.out.println("Input additional comment");
            String comment = sc.nextLine();
            
            //TODO ReviewsController class
            reviewsController.create(movie.get(0), user, rating, comment);
        }
    }
}
