package Boundary;

import java.security.NoSuchAlgorithmException;
import model_Classes.Movie;;


public class DetailsMovieToAmendMenuUI {
	
	private Movie movie;

	// constructor requires a valid movie be passed in
	public DetailsMovieToAmendMenuUI(Movie selectedMovie) {
		this.movie = selectedMovie;
	}
	
	public void main() throws NoSuchAlgorithmException {
		
    	System.out.println("--------------------------------------------------");
        System.out.println("-- Details of movie you have selected to amend ---");
        System.out.println("--------------------------------------------------");
        System.out.println("Movie Title: " + movie.getTitle());
        System.out.println("Movie Type: " + movie.getType());
        System.out.println("Synopsis: " + movie.getDescription());
        System.out.println("Screening Duration: " + movie.getDuration());
        System.out.println("Rating: " + movie.getRating());
        System.out.println("Release Date: " + movie.getReleaseDate());
        System.out.println("End of Screening Date: " + movie.getEndDate());
        System.out.println("Director: " + movie.getDirectorName());
        System.out.println("Cast Members: " + movie.getCastMembers());
        System.out.println("");
		
		
	}

	

}
