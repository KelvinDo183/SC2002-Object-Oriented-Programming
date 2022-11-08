package Boundary;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.MovieController;
import model_Classes.Movie;
import model_Classes.MovieType;;

public class CreateMovieListingUI {
	
	private MovieController movieController;
	
	public CreateMovieListingUI() {
		this.movieController = new MovieController();
	}
	
	public Movie main() throws NoSuchAlgorithmException, FileNotFoundException {
	
	    Scanner sc = new Scanner(System.in);
	    Scanner string_scanner = new Scanner(System.in).useDelimiter("\n");
		
		System.out.println("--------------------------------------------------");
		System.out.println("----- Details of the movie listing to create -----");
		System.out.println("--------------------------------------------------");
		System.out.println("Enter Movie Title: ");
	//                String movieTitle = sc.nextLine();
		String movieTitle = string_scanner.next();
		System.out.println("Enter Movie Type: ");
		System.out.println("(1) Blockbuster ");
		System.out.println("(2) 2D ");
		System.out.println("(3) 3D ");
		MovieType movieType = MovieType.BLOCKBUSTER;
		System.out.println("The initial movie type is " + movieType);

		int movTypeInput = sc.nextInt();
			// convert user input to MOVIE TYPE

		switch(movTypeInput) {
			case 1:
				movieType = MovieType.BLOCKBUSTER;
				System.out.println("The movie type is " + movieType);
				break;
			case 2:
				movieType = MovieType.TWO_D;
				System.out.println("The movie type is " + movieType);
				break;
			case 3:
				movieType = MovieType.THREE_D;
				System.out.println("The movie type is " + movieType);
				break;
			default:
				movieType = MovieType.BLOCKBUSTER;
				System.out.println("The movie type is " + movieType);
		}


		System.out.println("Enter Synopsis: ");
//        String movieDescription = sc.nextLine();
		String movieDescription = string_scanner.next();
//		System.out.println("");

		System.out.println("Enter Screening Duration: ");
		String durationStringInput = string_scanner.next();
		Double movieDuration = Double.parseDouble(durationStringInput);
		System.out.println("Enter Rating: ");
		String movieRating = string_scanner.next();
		System.out.println("Enter Release Date (format yyyy-mm-dd): ");
		String releaseDateStringInput = sc.next();
	//            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-Mmm-yyyy");
	//                LocalDate movieReleaseDate = LocalDate.parse(releaseDateStringInput, formatter);
	//                LocalDate movieReleaseDate = LocalDate.parse("26-Oct-2022", formatter);
		LocalDate movieReleaseDate = LocalDate.parse(releaseDateStringInput);
		System.out.println("Enter End of Screening Date (format yyyy-mm-dd): ");

		String endDateStringInput = sc.next();
		LocalDate movieEndDate = LocalDate.parse(endDateStringInput);
	//                LocalDate movieEndDate = LocalDate.parse(endDateStringInput, formatter);
		System.out.println("Enter Director Name: ");
	//                String movieDirectorName = sc.nextLine();
		String movieDirectorName = string_scanner.next();
		System.out.println("Enter list of Cast Members (new line for each cast member, no spaces between first & last names): ");
		System.out.println("Type 'N' to end input of cast member names");

//		String newCastMember = sc.nextLine();
	//                String newCastMember = string_scanner.next();
		ArrayList<String> newCastMembersAL = new ArrayList<String>();

		Boolean continueLooping = true;

		do
		{
//			String additionalCast = sc.next();
			String additionalCast = string_scanner.next();
			newCastMembersAL.add(additionalCast);
//			if (additionalCast.toUpperCase().matches("N")) 
//				{
//					System.out.println("No more cast members to add.");
//					continueLooping = false;
//				}
			if (additionalCast.compareTo("N") == 1) 
			{
				System.out.println("No more cast members to add.");
				continueLooping = false;
			}			

		} while (continueLooping);


		System.out.println("");
		Movie movielisting = new Movie(MovieController.getLatestId()+1, movieTitle, movieType, movieDescription,
							movieDuration, movieRating, movieReleaseDate, movieEndDate, movieDirectorName, newCastMembersAL);
		
		switch(movTypeInput) {
		case 1:
			movielisting.setType(MovieType.BLOCKBUSTER);
			System.out.println("The movie type is " + movielisting.getType().toString());
			break;
		case 2:
			movielisting.setType(MovieType.TWO_D);
			System.out.println("The movie type is " + movielisting.getType().toString());

			break;
		case 3:
			movielisting.setType(MovieType.THREE_D);
			System.out.println("The movie type is " + movielisting.getType().toString());
			break;
		default:
			movielisting.setType(MovieType.BLOCKBUSTER);
			System.out.println("The movie type is " + movielisting.getType().toString());
	}


		System.out.println("Movie listing with ID #" + movielisting.getID() + " and movieType = " + movieType + " and title '" 
						+ movielisting.getTitle() + "' and cast members: " + newCastMembersAL + " -> has been created.");
		
		return movielisting;
		
	}

}
