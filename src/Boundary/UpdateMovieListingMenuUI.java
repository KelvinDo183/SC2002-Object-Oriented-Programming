package Boundary;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import Boundary.DetailsMovieToAmendMenuUI;
import Boundary.SelectAttributeToAmendMenuUI;
import Controller.MovieController;
import model_Classes.Movie;

public class UpdateMovieListingMenuUI {
	
	private MovieController movieController;
	
	public UpdateMovieListingMenuUI() {
		this.movieController = new MovieController();
	}
	
	public ArrayList<Movie> main() throws NoSuchAlgorithmException, FileNotFoundException {
	
    	System.out.println("--------------------------------------------------");
		System.out.println("Enter ID of Movie to update: ");
		
	    Scanner sc = new Scanner(System.in);
	    int movieID = sc.nextInt();

		Movie updatingMovie = (Movie) movieController.readSpecificID(movieID);
		System.out.println("You have selected the following movie: ");
		
		detailsMovieToAmendUIMenu(updatingMovie);
		System.out.println("Note that you can only amend one attribute at a time. ");
		Movie updatedMovie = selectAttributeToAmendUIMenu(updatingMovie);
		
        ArrayList<Movie> allMovies = movieController.read();
        ArrayList<Movie> remainingMovies = new ArrayList<Movie>();
        
        for (int i=0; i<allMovies.size(); i++){
            Movie m = allMovies.get(i);
            if (m.getID() != movieID)
            {
            	remainingMovies.add(m);
            }
        }
        
        // add newly updated movie back to ArrayList
        remainingMovies.add(updatedMovie);
            
        return remainingMovies;

	}

	
    public void detailsMovieToAmendUIMenu(Movie m) throws NoSuchAlgorithmException {
    	DetailsMovieToAmendMenuUI detailsAmendMenu = new DetailsMovieToAmendMenuUI(m);
    	detailsAmendMenu.main();
    }
    
    public Movie selectAttributeToAmendUIMenu(Movie m) throws NoSuchAlgorithmException {
    	SelectAttributeToAmendMenuUI attributeAmendMenu = new SelectAttributeToAmendMenuUI(m);
    	return attributeAmendMenu.main();
    }

}
