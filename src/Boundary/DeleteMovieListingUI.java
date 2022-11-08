package Boundary;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import Controller.MovieController;
import model_Classes.Movie;

public class DeleteMovieListingUI {
	
	private MovieController movieController;
	
	public DeleteMovieListingUI() {
		this.movieController = new MovieController();
	}
	
	public void main() throws NoSuchAlgorithmException, FileNotFoundException {
	
    	System.out.println("--------------------------------------------------");
		System.out.println("Enter ID of Movie to delete: ");
		
	    Scanner sc = new Scanner(System.in);
	    int movieID = sc.nextInt();

		Movie deletingMovie = (Movie) movieController.readSpecificID(movieID);
		System.out.print("Deleting movie title '" + deletingMovie.getTitle() + "' with ID = " + deletingMovie.getID());
		movieController.deleteById(movieID);
		System.out.println("\nMovie has been deleted.");
    	System.out.println("--------------------------------------------------\n");
	}

}
