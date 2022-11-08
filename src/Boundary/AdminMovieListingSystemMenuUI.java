package Boundary;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import Boundary.CreateMovieListingUI;
import Controller.MovieController;

import model_Classes.Movie;


public class AdminMovieListingSystemMenuUI {
	
	MovieController movControl = new MovieController();
	

	// constructor requires a valid movie be passed in
	public AdminMovieListingSystemMenuUI() {
		
	}
	
	public void main() throws NoSuchAlgorithmException, FileNotFoundException {
		
		boolean exitMenu = false;
		while (!exitMenu)
		{

	    	System.out.println("--------------------------------------------------");
		    System.out.println("------- Admin View: Movie Listings System --------");
		    System.out.println("--------------------------------------------------");
		    System.out.println("(1) Create New Movie Listing ");
		    System.out.println("(2) Update Existing Movie Listing ");
		    System.out.println("(3) Delete a Movie Listing ");
		    System.out.println("(4) Return to Menu");
		    System.out.println("");
		    System.out.print("Select choice: ");


		    Scanner sc = new Scanner(System.in);
		    int menuChoice = sc.nextInt();

		    System.out.println("");

		    switch (menuChoice) {
		    // create new movie listing
		    case 1:
		    	createMovieListingUIMenu();
		    	break;

		    // update existing movie listing
		    case 2:
			break;

		    // delete a movie listing
		    case 3:
			break;

		    // return to previous
		    case 4:
			exitMenu = true;
			System.out.println("Returning to menu ...");
			System.out.println("");
			break;

		    default:
			System.out.println("Please enter a correct number");
			System.out.println("");
		}
		} while (!exitMenu);
		
		
	}
	
    public void createMovieListingUIMenu() throws NoSuchAlgorithmException, FileNotFoundException {
        // Create new Movie Listing menu
    	CreateMovieListingUI createML_UI = new CreateMovieListingUI();
    	Movie newMovie = createML_UI.main();

    	movControl.create(newMovie.getTitle(), newMovie.getType(), newMovie.getDescription(), newMovie.getDuration(), newMovie.getRating(), 
    			newMovie.getReleaseDate(), newMovie.getEndDate(), newMovie.getDirectorName(), newMovie.getCastMembers());
    }

	

}
