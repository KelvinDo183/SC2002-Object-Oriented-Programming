package Boundary;

import Controller.*;
import model_Classes.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SearchMovieUI {
	private String title;
	private String type;
	private int choice;
	static Scanner sc = new Scanner(System.in);

	public void main() throws FileNotFoundException {

		int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
			System.out.println("--------------------------------------------------");
			System.out.println("----------------- Search Movies ------------------");
			System.out.println("--------------------------------------------------");
			System.out.println("(1) Search by title");
			System.out.println("(2) Search by type");
			System.out.println("(3) List all titles");
			System.out.println("(4) Return to menu");
            System.out.println("");
            System.out.print("Select choice: ");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
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
                    exitMenu = true;
                    System.out.println("Returning to menu...");
                    System.out.println("");
                    break;

                default:
                    System.out.println("Please enter a correct number");
                    System.out.println("");
            }
        }

        while (!exitMenu);

	}
	
	public void mainMovieGoer() throws FileNotFoundException {

		int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
			System.out.println("--------------------------------------------------");
			System.out.println("----------------- Search Movies ------------------");
			System.out.println("--------------------------------------------------");
			System.out.println("(1) Search by title");
			System.out.println("(2) Search by type");
			System.out.println("(3) List available titles");
			System.out.println("(4) Return to menu");
            System.out.println("");
            System.out.print("Select choice: ");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
				case 1:
					searchTitle();
					break;

				case 2:
					searchType();
					break;

				case 3:
					listAvailableScreeningMovies();
					break;

                case 4:
                    exitMenu = true;
                    System.out.println("Returning to menu...");
                    System.out.println("");
                    break;

                default:
                    System.out.println("Please enter a correct number");
                    System.out.println("");
            }
        }

        while (!exitMenu);

	}
	

	public boolean searchTitle() throws FileNotFoundException {
		System.out.println("Enter movie title: ");
		title = InputController.getStringFromUser();
		
		Movie movie = MovieController.findExistingMovie(title);
		
		if (movie != null)
		{
            System.out.println("**********************");
			System.out.println("Movie found!");
            System.out.println("Movie is: " + movie.toString());
            System.out.println("**********************");
            return true;
		}
		
		else {
			System.out.println("Movie with title " + title + " was not found.");
			return false;
		}
		
	}

	public boolean searchType() throws FileNotFoundException {
		System.out.println("Enter movie type(Case sensitive): ");
		type = InputController.getStringFromUser();
		
		// update movieTypeArray value if readAllMoviesOfType function returns array of movies
		ArrayList<Movie> movieTypeArray = MovieController.readAllMoviesOfType(type);		

		if (movieTypeArray.isEmpty()) {
			System.out.println("No movies of type " + type + " found.");
			return false;
		} else {
			System.out.println("Movie(s) of type " + type + " found!");
			for (int i = 0; i < movieTypeArray.size(); i++) {
				System.out.println(movieTypeArray.get(i).toString());
			}
			return true;
		}
	}


	// lists all movies (Admin view)
	public boolean listAll() throws FileNotFoundException {
		ArrayList<Movie> allMovies = MovieController.read();

		if (allMovies.isEmpty()) {
			System.out.println("No movies found.");
			return false;
		}

		else {
			System.out.println("--------------------------------------------------");
			System.out.println("[Admin View] All movies are listed below: ");
			System.out.println("--------------------------------------------------");
			for (int i = 0; i < allMovies.size(); i++) {
				System.out.println(allMovies.get(i).toString());
			}
			return true;
		}

	}
	
	// lists all screening movies (for movie-goer)
	public boolean listAvailableScreeningMovies() throws FileNotFoundException {
		ArrayList<Movie> allMovies = MovieController.readAllScreeningMovies();

		if (allMovies.isEmpty()) {
			System.out.println("No movies found.");
			return false;
		}

		else {
			System.out.println("--------------------------------------------------");
			System.out.println("The following movies are currently screening: ");
			System.out.println("--------------------------------------------------");
			for (int i = 0; i < allMovies.size(); i++) {
				System.out.println(allMovies.get(i).toString());
			}
			return true;
		}
	}
}
