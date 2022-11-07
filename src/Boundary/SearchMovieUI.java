package Boundary;

import Controller.*;
import model_Classes.*;
import java.util.Scanner;
import java.util.ArrayList;

public class SearchMovieUI {
	private String title;
	private String type;
	private int choice;
	static Scanner sc = new Scanner(System.in);

	SearchMovieUI() {

	}

	public void main() {

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

	public boolean searchTitle() {
		System.out.println("Enter movie title(Case sensitive): ");
		title = sc.nextLine();
		if (MovieController.isExistingMovie(title)) {
			System.out.println("Movie of title " + title + " found!");
			return true;
		} else {
			System.out.println("No movie of title " + title + " found.");
			return false;
		}

	}

	public boolean searchType() {
		System.out.println("Enter movie type(Case sensitive): ");
		type = sc.nextLine();

		ArrayList<Movie> movieArray;

		if (Movie.getType() == type) {
			movieArray.add(Movie);
		}

		if (movieArray.isEmpty()) {
			System.out.println("No movies of type " + type + " found.");
			return false;
		} else {
			System.out.println("Movie(s) of type " + type + " found!");
			for (int i = 0; i < movieArray.size(); i++) {
				System.out.println(movieArray.get(i).toString());
			}
			return true;
		}
	}

	// lists all screening movies
	public boolean listAll() {
		ArrayList<Movie> allMovies = MovieController.readAllScreeningMovies();

		if (allMovies.isEmpty()) {
			System.out.println("No movies found.");
			return false;
		}

		else {
			System.out.println("Movies found will be listed");
			for (int i = 0; i < allMovies.size(); i++) {
				System.out.println(allMovies.get(i).toString());
			}
		}

	}
}
