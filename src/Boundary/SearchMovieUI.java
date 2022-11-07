package Boundary;
import Controller.*;
import Model.*;
import java.util.Scanner;
import java.util.ArrayList;

public class SearchMovieUI {
	private String title;
	private String type;
	private int choice;
	static Scanner sc = new Scanner(System.in);
	
	
	SearchMovieUI(){
		//controller
	}
	
	public void main() {
		do {
	        System.out.println("1. Search by title");
	        System.out.println("2. Search by type");
	        System.out.println("3. List all titles");
	        System.out.println("4. Exit");
	        switch (choice = sc.nextInt()) {
	        case 1:
	            searchTitle();
	            break;
	        case 2:
	            searchType();
	            break;
	        case 3:
	            listAll();
	            break;
	        case 4:
	            System.out.println("Exit from Search-Movie successful");
	            return;
	        default:
	            System.out.println("Invalid input! Please try again.");
	        }
		} while (choice != 4);
	}
	
	public boolean searchTitle() {
		System.out.println("Enter movie title(Case sensitive): ");
		title = sc.nextLine();
		//TODO check for the movie, controller implementation
		if(MovieController.isExistingMovie(title)) {
			System.out.println("Movie of title " + title + " found!");
			return true;
		}
		else {
			System.out.println("No movie of title " + title + " found.");
			return false;
		}
		
	}
	
	public boolean searchType() {
		System.out.println("Enter movie type(Case sensitive): ");
		type = sc.nextLine();
		
		//TODO check for any movies of given type, controller implementation
		ArrayList<Movie> movieArray;
		
		if(Movie.getType() == type) {
			 movieArray.add(Movie);
		}
			
		if(movieArray.isEmpty()) {  
			System.out.println("No movies of type " + type + " found.");
			return false;
		}
		else {
			System.out.println("Movie(s) of type " + type + " found!");
			for(int i=0; i<movieArray.size() ; i++) {
				System.out.println(movieArray.get(i).toString());
			}
			return true;
		}
	}
	
	//lists all screening movies
	public boolean listAll() {
		ArrayList<Movie> allMovies = MovieController.readAllScreeningMovies();
		
		if(allMovies.isEmpty()) {
			System.out.println("No movies found.");
			return false;
		}
		
		else {
			System.out.println("Movies found will be listed");
			for(int i=0; i<allMovies.size() ; i++) {
				System.out.println( allMovies.get(i).toString() );
			}
		}
		
	}
}
